package com.rest.bdnosql;

import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.Jedis;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
public class Autenticacion {
    @RequestMapping("/autUsu")
    @ResponseBody
    public boolean autenticacion(@RequestBody Usuario usuario){
        try (Jedis jedis = Pool.getResource()) {
            if(!jedis.hgetAll(usuario.getCorreo()).isEmpty()){
               if(usuario.getPass().equals(jedis.hget(usuario.getCorreo(), "Pass"))){
                   System.out.println("El Usuario está en el Sistema.");
                   return true;
               }
               else{
                System.out.println("Contrasenia incorrecta.");
               return false;
               }
            }
            else{
                System.out.println("El Correo no existe.");
                return false;
            }
 
         } catch (Exception e){
             e.printStackTrace();
         }
         return false;
    }
}