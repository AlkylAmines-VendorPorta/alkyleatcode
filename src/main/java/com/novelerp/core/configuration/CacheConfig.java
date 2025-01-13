package com.novelerp.core.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {
	
	
	 @Bean
	  public JedisConnectionFactory redisConnectionFactory() {
	    JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
	    // Defaults
	    redisConnectionFactory.setHostName("127.0.0.1");
	    /*redisConnectionFactory.setHostName("10.0.2.201");
	    redisConnectionFactory.setHostName("172.20.20.125");*/
	    redisConnectionFactory.setPort(6379);
	    redisConnectionFactory.setUsePool(true);
	    return redisConnectionFactory;
	  }

	 @Bean
	 RedisTemplate< String, Object > redisTemplate() {
	  final RedisTemplate< String, Object > template =  new RedisTemplate< String, Object >();
	  template.setConnectionFactory( redisConnectionFactory() );
	  template.setKeySerializer( new StringRedisSerializer() );
	  template.setHashValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
	  template.setValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
	  return template;
	 }

	  @Bean
	  public CacheManager cacheManager(RedisTemplate redisTemplate) {
	    RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);

	    // Number of seconds before expiration. Defaults to unlimited (0)
	    cacheManager.setDefaultExpiration(300);
	    return cacheManager;
	  }

}
