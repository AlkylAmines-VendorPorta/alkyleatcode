package com.novelerp.alkyl.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.novelerp.alkyl.dto.PurchaseOrderDto;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.security.JwtTokenProvider;
import com.novelerp.appcontext.security.payload.JwtAuthenticationResponse;
import com.novelerp.appcontext.security.payload.LoginRequest;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.LoginService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.service.UserSessionService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.exception.CustomException;
import com.novelerp.core.util.CoreReferenceConstants;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider tokenProvider;

	@Autowired
	private LoginService loginService;

	@Autowired
	@Qualifier("jwtUserContext") private ContextService contextService;
	@Autowired
	private UserSessionService userSessionService;
	
	@Autowired
	private UserService userService;

	
	private Logger log = LoggerFactory.getLogger(getClass());
	@GetMapping("/loginurl")
	public String loginurl() {
		return "return";
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));
		System.out.println("authentication " +authentication);
		UserDto userDto = loginService.getUserByUserName(loginRequest.getUsernameOrEmail());
//		if(userDto == null){
//			return (ResponseEntity<?>) ResponseEntity.status(401);
//			
//		}
		/*SecurityContextHolder.getContext().setAuthentication(authentication);*/
		String jwt = tokenProvider.generateToken(authentication);
		System.out.println("authentication token" +jwt);
		userDto.setAuthToken(jwt);
		
		contextService.contextInitializer(userDto);
		List<Object> tilesUrl=userService.getTilesUrl(userService.getDefaultRole(userDto.getRoles()).getValue());
		System.out.println("Tile List" +tilesUrl);
	
		try {
			if(request != null){
				userSessionService.saveUserSession(request, userDto);
				System.out.println("User Session Created");
			}
			
		} catch (Exception e) {
			log.info("Error in Saving User Session",e);
			System.out.println("Error in Saving User Session" + e.getMessage());
			throw new CustomException(e.getMessage());
		}
	
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt,tilesUrl));
		
		// ContextDto context = contextService.getContext(jwt);
		

}		
	
}
