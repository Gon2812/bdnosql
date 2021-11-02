package com.rest.bdnosql;

import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
public class EliminarRol {
    @RequestMapping("/delRol")
    @ResponseBody
    public String eliminarRol(@RequestBody Usuario usuario){
        List<String> errores = new ArrayList<String>();
        try (Jedis jedis = Pool.getResource()) {
            if(!jedis.hgetAll(usuario.getCorreo()).isEmpty()){
               if(usuario.getPass().equals(jedis.hget(usuario.getCorreo(), "Pass"))){
                   for(String str: usuario.getRol()){
                       System.out.println("LLegue aca!");
                        Long exito = jedis.srem(usuario.getCorreo() + ":tags", str);
                        System.out.println("Pase esto");
                        if(exito == 0){
                            errores.add(str);
                            System.out.println(jedis.get("Error 103"));
                        }
                   }
               }
               else{
                System.out.println(jedis.get("Error 104"));
                return jedis.get("Error 104");
               }
               if(errores.isEmpty()){
                String usu = jedis.hgetAll(usuario.getCorreo()).toString();
                System.out.println("Agregado con éxito!");
                System.out.println(usu);
                System.out.println(jedis.smembers(usuario.getCorreo()+ ":tags"));
                return usu;
               }
               else{
                   System.out.println("error" + errores);
                   return jedis.get("Error 103") +"\nError en los Roles: " + errores;
               }
                
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