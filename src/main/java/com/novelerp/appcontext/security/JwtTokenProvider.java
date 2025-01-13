package com.novelerp.appcontext.security;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.UserDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

	private String jwtSecret = "jwtSecret";
	/*private int jwtExpirationInMs = 30000;*/

	@Autowired
	private RedisTemplate<String, Object> template;
	
	 public String generateToken(Authentication authentication) {

	        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

	        /*Date now = new Date();
	        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);*/

	        return Jwts.builder()
	                .setSubject(Long.toString(userPrincipal.getId()))   // .claim("user", userPrincipal.getUserDto())
	                .setIssuedAt(new Date())
	                /*.setExpiration(expiryDate)*/
	                .signWith(SignatureAlgorithm.HS512, jwtSecret)
	                .compact();
	    }

	    public Long getUserIdFromJWT(String token) {
	        Claims claims = Jwts.parser()
	                .setSigningKey(jwtSecret)
	                .parseClaimsJws(token)
	                .getBody();
	        return Long.parseLong(claims.getSubject());
	    }
	    
	    public UserDto getUserFromJWT(String token){
	    	Claims claims = Jwts.parser()
	                .setSigningKey(jwtSecret)
	                .parseClaimsJws(token)
	                .getBody();
	        return (UserDto) claims.get("user");
	    }

	    public boolean validateToken(String authToken) {
	        try {
	            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
	            Boolean expireJwt=template.expire(authToken, AppBaseConstant.JWT_EXPIRATION, TimeUnit.MILLISECONDS);
	            System.out.println("JWT Time Added "+expireJwt);
	            return expireJwt;
	        } catch (MalformedJwtException ex) {
	        	System.out.println("Invalid JWT token");
	        } catch (ExpiredJwtException ex) {
	        	System.out.println("Expired JWT token");
	        } catch (UnsupportedJwtException ex) {
	        	System.out.println("Unsupported JWT token");
	        } catch (IllegalArgumentException ex) {
	        	System.out.println("JWT claims string is empty.");
	        }
	        return false;
	    }
}
