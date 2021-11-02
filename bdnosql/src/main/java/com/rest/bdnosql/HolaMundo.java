package com.rest.bdnosql;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.Jedis;

@RestController
public class HolaMundo {
    @RequestMapping("/hello")
    public String hola(){
        try (Jedis jedis = Pool.getResource()) {
            jedis.set("events/city/rome", "32,15,223,828");
            String cachedResponse = jedis.get("events/city/rome");
            System.out.println("Agregado con éxito!"); 
            return cachedResponse;
 
         } catch (Exception e){
             e.printStackTrace();
         }
        return "error 101";
    }
}