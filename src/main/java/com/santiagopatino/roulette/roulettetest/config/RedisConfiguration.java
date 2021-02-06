package com.santiagopatino.roulette.roulettetest.config;

import com.santiagopatino.roulette.roulettetest.Bet;
import com.santiagopatino.roulette.roulettetest.domain.Roulette;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfiguration {
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    RedisTemplate<String, Roulette> redisTemplateRoulette(){
        final RedisTemplate<String, Roulette> redisTemplateRoulette = new RedisTemplate<>();
        redisTemplateRoulette.setConnectionFactory(jedisConnectionFactory());
        return redisTemplateRoulette;
    }

    @Bean
    RedisTemplate<String, Bet> redisTemplateBet(){
        final RedisTemplate<String, Bet> redisTemplateBet = new RedisTemplate<>();
        redisTemplateBet.setConnectionFactory(jedisConnectionFactory());
        return redisTemplateBet;
    }
}
