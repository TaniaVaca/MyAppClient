package com.mycompany.myappclient.controllers;

import com.mycompany.myappclient.models.TarjetaCredito;
import com.mycompany.myappclient.security.EAS;
import com.mycompany.myappclient.utils.JsfUtil;
import com.mycompany.myappclient.utils.JsfUtil.PersistAction;
import com.mycompany.myappclient.wsrestclients.TarjetaCreditoWSRest;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
/**
 *
 * @author Alexandra
 */
@Named("tarjetaCreditoController")
@SessionScoped
public class TarjetaCreditoController implements Serializable {

    
    private com.mycompany.myappclient.wsrestclients.TarjetaCreditoWSRest tarjetaCreditoWSRest = new TarjetaCreditoWSRest();
    private List<TarjetaCredito> listTarjetaCredito = null;
    private TarjetaCredito tarjetaCredito;

    public TarjetaCreditoController() {
    }

    public TarjetaCredito getTarjetaCredito() {
        return tarjetaCredito;
    }

    public void setTarjetaCredito(TarjetaCredito tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TarjetaCreditoWSRest getTarjetaCreditoDao() {
        return tarjetaCreditoWSRest;
    }

    public TarjetaCredito prepareCreate() {
        tarjetaCredito = new TarjetaCredito();
        initializeEmbeddableKey();
        return tarjetaCredito;
    }

    public void create() {
        createTCClaro();
        tarjetaCreditoWSRest.createEntity(tarjetaCredito);
        if (!JsfUtil.isValidationFailed()) {
            listTarjetaCredito = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        tarjetaCreditoWSRest.updateEntity(tarjetaCredito);
    }

    public void destroy() {
        tarjetaCreditoWSRest.deleteEntityById(tarjetaCredito.getBin().toString());
        if (!JsfUtil.isValidationFailed()) {
            tarjetaCredito = null; // Remove selection
            listTarjetaCredito = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TarjetaCredito> getListTarjetaCredito() {
        if (listTarjetaCredito == null) {
            listTarjetaCredito = getTarjetaCreditoDao().readEntityList();
        }
        return listTarjetaCredito;
    }

    public TarjetaCredito getTarjetaCredito(java.lang.Integer id) {
        return getTarjetaCreditoDao().readEntityById(id.toString());
    }

    public List<TarjetaCredito> getItemsAvailableSelectMany() {
        return getTarjetaCreditoDao().readEntityList();
    }

    public List<TarjetaCredito> getItemsAvailableSelectOne() {
        return getTarjetaCreditoDao().readEntityList();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TarjetaCreditoController other = (TarjetaCreditoController) obj;
        if (!Objects.equals(this.tarjetaCredito, other.tarjetaCredito)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TarjetaCreditoDaoImplemenst{" + "tarjetaCredito=" + tarjetaCredito + '}';
    }
    
    public void createTCClaro(){
        Random gerador = new Random();
        int bin = gerador.nextInt(90000) + 10000;
        int cvv = gerador.nextInt(90000) + 10000;
        this.tarjetaCredito.setBin(bin);
        this.tarjetaCredito.setCvv(cvv);
        int tcClaroInt = gerador.nextInt(900000000) + 100000000;
        String tcClaro = ""+this.tarjetaCredito.getBin()+""+tcClaroInt+""+tarjetaCredito.getProducto();
        encriptTcClaro(tcClaro);
    }
    public void encriptTcClaro(String tcClaro){
        EAS eas = new EAS();
        eas.encrypt(tcClaro.trim());
        tarjetaCredito.setTcClaro(eas.getEncryptedString());
    }

}
