package com.mycompany.myappclient.controllers;

import com.mycompany.myappclient.models.User;
import com.mycompany.myappclient.utils.JsfUtil;
import com.mycompany.myappclient.utils.JsfUtil.PersistAction;
import com.mycompany.myappclient.wsrestclients.UserWSRest;

import java.io.Serializable;
import java.util.List;
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
@Named("userController")
@SessionScoped
public class UserController implements Serializable {

    
    private com.mycompany.myappclient.wsrestclients.UserWSRest userWSRest = new UserWSRest();
    private List<User> listUsers = null;
    private User user;

    public UserController() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UserWSRest getUserDao() {
        return userWSRest;
    }

    public User prepareCreate() {
        user = new User();
        initializeEmbeddableKey();
        return user;
    }

    public void create() {
        userWSRest.createEntity(user);
        if (!JsfUtil.isValidationFailed()) {
            listUsers = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        userWSRest.updateEntity(user);
    }

    public void destroy() {
        userWSRest.deleteEntityById(user.getCedula().toString());
        if (!JsfUtil.isValidationFailed()) {
            user = null; // Remove selection
            listUsers = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<User> getListUsers() {
        if (listUsers == null) {
            listUsers = getUserDao().readEntityList();
        }
        return listUsers;
    }

    public User getUser(java.lang.Integer id) {
        return getUserDao().readEntityById(id.toString());
    }

    public List<User> getItemsAvailableSelectMany() {
        return getUserDao().readEntityList();
    }

    public List<User> getItemsAvailableSelectOne() {
        return getUserDao().readEntityList();
    }

}
