package com.novelerp.appcontext.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.ContextDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.core.exception.CustomException;
import com.novelerp.core.util.ObjectSerializer;
import com.novelerp.eat.dto.TAHDRDto;

@Service("jwtUserContext")
public class JWTContextServiceImpl extends AbstractUserConextService implements ContextService {

	public static final Logger log = LoggerFactory.getLogger(JWTContextServiceImpl.class);


	@Autowired
	private ObjectSerializer serializer;
	
	@Autowired
	JedisConnectionFactory jedisFactory;

	@Autowired
	private RedisTemplate<String, Object> template;

	@Override
	public void contextInitializer(UserDto userDto) {

		log.info("Loading user context");
		try {
			ContextDto context = new ContextDto(userDto);
			final Map<String, Object> properties = new HashMap<String, Object>();
			properties.put("user", serializer.toString(context));
			template.opsForHash().putAll(userDto.getAuthToken(), properties);
			Boolean jwtExpire=template.expire(userDto.getAuthToken(), AppBaseConstant.JWT_EXPIRATION, TimeUnit.MILLISECONDS);
			System.out.println("In Context JWT"+jwtExpire);
		} catch (IOException e) {
			log.error(e.getMessage());
			throw new CustomException(e.getMessage());
		}

	}

	@Override
	public ContextDto getContext() {
		try {
			String authToken = getJwtFromRequest();
			if(authToken==null){
				return null;
			}
			final String userString = (String) template.opsForHash().get(authToken, "user");
			if(userString==null){
				return null;
			}
			ContextDto context = (ContextDto) serializer.fromString(userString);
			return context;

		} catch (ClassNotFoundException | IOException e) {
			log.error(e.getMessage());
		}
		return null;
	}

	private String getJwtFromRequest() {
		HttpServletRequest request = getRequest();
		if(request == null){
			return null;
		}
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

	@Override
	public void setSFTPRequiredInfo(BPartnerDto dto, String docType, TAHDRDto tahdr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRoles(Set<RoleDto> roleSet) {
		
		ContextDto context = getContext();
		UserDto userDto=context.getUserDto();
		
		if(userDto != null){
			
			userDto.setRoles(roleSet);
			contextInitializer(userDto);
		}
		
	}
}
