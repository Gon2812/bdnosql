package com.rest.bdnosql;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.Jedis;

@RestController
public class AgregarUsuario {
    @RequestMapping("/newUser")
    public String crearUsuario(@RequestBody Usuario usuario){
        Map<String, String> user = new HashMap<>();
        user.put("Pass", usuario.getPass());
        user.put("Nombre", usuario.getNombre());
        user.put("Apellido", usuario.getApellido());
        try (Jedis jedis = Pool.getResource()) {
            if(jedis.hget(usuario.getCorreo(), "Pass") == null){

                jedis.hmset(usuario.getCorreo(), user);
                String usu = jedis.hgetAll(usuario.getCorreo()).toString();
                System.out.println("Agregado con éxito!");
                System.out.println(usu);
                return "Usuario agregado con éxito!";
            }
            else{
                System.out.println(jedis.get("Error 101"));
                return "Error 101 - " + jedis.get("Error 101");
            }
 
         } catch (Exception e){
             e.printStackTrace();
         }
         return "Faltan datos.";
    }
}
