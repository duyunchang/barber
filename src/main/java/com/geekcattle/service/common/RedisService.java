
package com.geekcattle.service.common;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 * author 
 */
@Service
public interface  RedisService {

    public Jedis getResource();

    public void returnResource(Jedis jedis);

    public void set(String key, String value);

    public String get(String key);

}
