/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myappclient.models;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alexandra
 */
public class User extends BaseModel implements Serializable {

    
    private static final long serialVersionUID = 1L;
    
    private Integer cedula;
    
    private String nombre;
    
    private String apellido;
    
    private List<TarjetaCredito> tarjetaCreditoList;
    
    
    public User() {
    }

    public User(Integer cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cedula != null ? cedula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.cedula == null && other.cedula != null) || (this.cedula != null && !this.cedula.equals(other.cedula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.iatai.entities.User[ cedula=" + cedula + " ]";
    }

    @XmlTransient
    public List<TarjetaCredito> getTarjetaCreditoList() {
        return tarjetaCreditoList;
    }

    public void setTarjetaCreditoList(List<TarjetaCredito> tarjetaCreditoList) {
        this.tarjetaCreditoList = tarjetaCreditoList;
    }
    
}
