package io.github.kambasdojava.angolaconnectionhub.configs;

import io.github.kambasdojava.angolaconnectionhub.dto.TaxData;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.cache.autoconfigure.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

import java.time.Duration;

import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

@Configuration
public class RedisConfig {
  private final int weekDaysLength = 7;

  @Bean
  public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer(JsonMapper objectMapper) {

    return (builder) -> builder
        .withCacheConfiguration("taxes",
            RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofDays(weekDaysLength))
                .disableCachingNullValues()
                .serializeValuesWith(
                    fromSerializer(new JacksonJsonRedisSerializer<@NonNull TaxData>(objectMapper, TaxData.class))
                )
        );
  }

}
