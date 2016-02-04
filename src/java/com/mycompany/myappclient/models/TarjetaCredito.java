/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myappclient.models;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Alexandra
 */
public class TarjetaCredito extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer bin;
    
    private Integer producto;
    
   // private Date fechaExpiracion;
    
    private String tcClaro;
    
    private Integer cvv;
    
    private User user;

    public TarjetaCredito() {
    }

    public TarjetaCredito(Integer bin) {
        this.bin = bin;
    }

    public Integer getBin() {
        return bin;
    }

    public void setBin(Integer bin) {
        this.bin = bin;
    }

    public Integer getProducto() {
        return producto;
    }

    public void setProducto(Integer producto) {
        this.producto = producto;
    }

    /*public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }*/

    public String getTcClaro() {
        return tcClaro;
    }

    public void setTcClaro(String tcClaro) {
        this.tcClaro = tcClaro;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bin != null ? bin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TarjetaCredito)) {
            return false;
        }
        TarjetaCredito other = (TarjetaCredito) object;
        if ((this.bin == null && other.bin != null) || (this.bin != null && !this.bin.equals(other.bin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.iatai.entities.TarjetaCredito[ bin=" + bin + " ]";
    }
    
}
