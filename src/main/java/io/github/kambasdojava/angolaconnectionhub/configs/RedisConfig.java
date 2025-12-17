package io.github.kambasdojava.angolaconnectionhub.configs;

import org.springframework.boot.cache.autoconfigure.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import tools.jackson.databind.ObjectMapper;

import java.time.Duration;

import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

@Configuration
public class RedisConfig {
  @Bean
  public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer(ObjectMapper objectMapper) {
    return (builder) -> builder
        .withCacheConfiguration("taxes",
            RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(30))
                .serializeValuesWith(fromSerializer(new GenericJacksonJsonRedisSerializer(objectMapper))));
  }
}
