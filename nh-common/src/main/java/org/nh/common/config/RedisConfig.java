package org.nh.common.config;

import org.nh.common.cache.Cache;
import org.nh.common.cache.RedisCache;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author yindanqing
 * @date 2019/5/14 2:17
 * @description 统一注入redis
 */
@Component
public class RedisConfig {

    /**
     * 注入Redis单体实例
     * @return
     */
    @Bean
    public Cache redisCache(){
        return new RedisCache(getPool());
    }

    private JedisPool getPool(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(500);
        poolConfig.setMaxIdle(5000);
        poolConfig.setMaxWaitMillis(10000);
        poolConfig.setTestOnBorrow(true);
        return new JedisPool(poolConfig, "192.168.165.168", 6379, 60000, "cp5aDkRH");

    }

}
