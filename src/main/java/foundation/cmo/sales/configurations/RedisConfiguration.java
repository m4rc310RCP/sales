package foundation.cmo.sales.configurations;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import io.lettuce.core.RedisURI;

@Configuration
public class RedisConfiguration {
//	@Bean
    public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<?, ?> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(template.getStringSerializer());
        template.setHashKeySerializer(template.getStringSerializer());
        template.setHashValueSerializer(template.getStringSerializer());

        return template;
    }

//    @Bean
//    @Primary
    RedisProperties RedisProperties(RedisProperties properties) {
        RedisURI redisURI = RedisURI.create(properties.getUrl());
        properties.setHost(redisURI.getHost());
        properties.setPort(redisURI.getPort());

        if (redisURI.getPassword() != null) {
            properties.setPassword(String.valueOf(redisURI.getPassword()));
        }

        return properties;
    }
}
