/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myappclient.wsrestclients;

import com.mycompany.myappclient.models.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alexandra
 */
public class UserWSRest extends BaseWSRestClient<User> implements Serializable{

    public UserWSRest() {
        super("user", new User(), "userEntity");
    }
    
    public static void main (String[] argsv){
        UserWSRest userWSRest = new UserWSRest();
        List<User> userList = new ArrayList<User>();
        
        for(User user:userWSRest.readEntityList())
            System.out.println(user.getApellido());
        
        System.out.println("UNIDAD: "+userWSRest.readEntityById("123").getApellido());
        User user = new User();
        user.setCedula(34567);
        user.setNombre("Cleo");
        user.setApellido("Perez");
        userWSRest.createEntity(user);
           
    }
    
}
