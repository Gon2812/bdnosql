package com.rest.bdnosql;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Pool {
    private static JedisPool pool = new JedisPool("localhost",6379);

    public static Jedis getResource(){
        return pool.getResource();
    }
}