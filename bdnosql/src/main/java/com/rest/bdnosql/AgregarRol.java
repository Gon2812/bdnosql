package com.rest.bdnosql;

import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.Jedis;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
public class AgregarRol {
    @RequestMapping("/newRol")
    @ResponseBody
    public String agregarRol(@RequestBody Usuario usuario){
        try (Jedis jedis = Pool.getResource()) {
            if(!jedis.hgetAll(usuario.getCorreo()).isEmpty()){
               if(usuario.getPass().equals(jedis.hget(usuario.getCorreo(), "Pass"))){
                   for(String str: usuario.getRol()){
                    jedis.sadd(usuario.getCorreo()+ ":tags", str );
                   }
               }
               else{
                System.out.println(jedis.get("Error 104"));
                return jedis.get("Error 104");
               }
                String usu = jedis.hgetAll(usuario.getCorreo()).toString();
                System.out.println("Agregado con éxito!");
                System.out.println(usu);
                System.out.println(jedis.smembers(usuario.getCorreo()+ ":tags"));
                return "Agregados los Roles: " + jedis.smembers(usuario.getCorreo()+ ":tags");
            }
            else{
                System.out.println(jedis.get("Error 102"));
                return jedis.get("Error 102");
            }
 
         } catch (Exception e){
             e.printStackTrace();
         }
         return "Error raro";
    }
}