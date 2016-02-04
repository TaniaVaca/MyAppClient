/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myappclient.wsrestclients;

import com.mycompany.myappclient.models.TarjetaCredito;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alexandra
 */
public class TarjetaCreditoWSRest extends BaseWSRestClient<TarjetaCredito> {

    public TarjetaCreditoWSRest() {
        super("tarjeta-credito", new TarjetaCredito(), "tarjetaCreditoEntity");
    }
    
    
    public static void main (String[] argsv){
        TarjetaCreditoWSRest creditoWSRest = new TarjetaCreditoWSRest();
        List<TarjetaCredito> tarjetaCreditoList = new ArrayList<TarjetaCredito>();
        
        for(TarjetaCredito tarjetaCredito:creditoWSRest.readEntityList())
            System.out.println("Tarjeta "+tarjetaCredito.getUser().getNombre());
        
       // System.out.println("UNIDAD: "+creditoWSRest.readEntityById("123").getApellido());
           
    }
}
