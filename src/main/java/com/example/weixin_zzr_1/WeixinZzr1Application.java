package com.example.weixin_zzr_1;

import com.example.weixin_zzr_1.domain.InMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class WeixinZzr1Application {
	
	// 相当于Spring的XML配置方式中的<bean>元素
	@Bean
	public RedisTemplate<String, InMessage> inMessageTemplate(//
			@Autowired RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, InMessage> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(WeixinZzr1Application.class, args);
	}

}
