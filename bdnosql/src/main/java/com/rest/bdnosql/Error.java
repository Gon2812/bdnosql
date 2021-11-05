package com.rest.bdnosql;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.Jedis;

@RestController
public class Error {
    @RequestMapping("/")
    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST})
    public String error(){
        System.out.println("Error 101 - El Ususario ya existe. \nError 102 - El Usuario no existe. \nError 103 - El Rol no existe.\nError 104 - Contraseña incorrecta.");
        try (Jedis jedis = Pool.getResource()) {
            jedis.set("Error 101", "El Ususario ya existe. ");
            String error1 = jedis.get("Error 101");
            jedis.set("Error 102", "El Usuario no existe. ");
            String error2 = jedis.get("Error 102");
            jedis.set("Error 103", "El Rol no existe. ");
            String error3 = jedis.get("Error 103");
            jedis.set("Error 104", "Contraseña incorrecta. ");
            String error4 = jedis.get("Error 104");
            return "Error 101 - " + error1 + "\n" + "Error 102 - " + error2 + "\n" + "Error 103 - " + error3 + "\n" + "Error 104 - " + error4;
         } catch (Exception e){
             e.printStackTrace();
         }
         return "Error en la base de datos.";
    }
}
