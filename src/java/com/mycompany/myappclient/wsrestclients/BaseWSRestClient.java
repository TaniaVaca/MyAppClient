package com.mycompany.myappclient.wsrestclients;

import com.mycompany.myappclient.models.BaseModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexandra
 */
public class BaseWSRestClient<T extends BaseModel> implements Serializable {

    private static final long serialVersionUID = -1917338477098434596L;

    private WebTarget webTarget;
    private Client client;
    private String mediaType;
    private T classType;
    private Class responseType;
    private Gson gson;
    private JsonArray jsonArray;
    private JsonParser jsonParser;
    private String castObject;              
    private static final String BASE_URI = "http://localhost:8080/MyAppServerMaven/rest/";


    /**
     * @param pathWS
     * @param classType
     */
    public BaseWSRestClient(String pathWS, T classType) {
        client = ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path(pathWS);
        gson = new Gson();
        mediaType = MediaType.APPLICATION_JSON;
        this.classType = classType;
        this.responseType = String.class;
    }

    /**
     * @param pathWS
     * @param classType
     */
    public BaseWSRestClient(String pathWS, T classType, String castObject) {
        client = ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path(pathWS);
        gson = new Gson();
        mediaType = MediaType.APPLICATION_JSON;
        this.classType = classType;
        this.responseType = String.class;
        this.castObject = castObject;
    }

    /**
     * @param pathWS
     * @param classType
     * @param responseType
     */
    public BaseWSRestClient(String pathWS, T classType, Class responseType) {
        client = ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path(pathWS);
        gson = new Gson();
        mediaType = MediaType.APPLICATION_JSON;
        this.classType = classType;
        this.responseType = responseType.getClass();
    }

    /**
     * @param pathWS
     * @param classType
     * @param mediaType
     */
    public BaseWSRestClient(String pathWS, T classType, MediaType mediaType) {
        client = ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path(pathWS);
        gson = new Gson();
        this.mediaType = mediaType.getType();
        this.classType = classType;
        this.responseType = String.class;
    }

    /**
     * @return
     * @throws ClientErrorException
     */
    public List<T> readEntityList() throws ClientErrorException {
        jsonParser = new JsonParser();
        String json = (String) webTarget.request(mediaType).get(responseType);
        System.out.println("JSON: "+json);
        jsonArray = jsonParser.parse(json).getAsJsonArray();
        //gson.fromJson(json, classType.getClass());
        List<T> modelList = new ArrayList<>();
        for (JsonElement jsonElement : jsonArray)
            modelList.add((T) gson.fromJson(jsonElement, classType.getClass()));
        return modelList;
    }

    /**
     * @return
     * @throws ClientErrorException
     */
    public List<T> readEntityListByParams(String... params) throws ClientErrorException {
        jsonParser = new JsonParser();
        String json = (String) webTarget.request(mediaType).get(responseType);
        String type = classType.getClass().getSimpleName();

        jsonArray = jsonParser.parse(json).getAsJsonObject().getAsJsonArray(castObject);
        gson.fromJson(json, classType.getClass());
        List<T> modelList = new ArrayList<>();
        for (JsonElement jsonElement : jsonArray)
            modelList.add((T) gson.fromJson(jsonElement, classType.getClass()));
        return modelList;
    }

    /**
     * @param id
     * @return
     * @throws ClientErrorException
     */
    public T readEntityById(String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        return (T) gson.fromJson((String) resource.request(mediaType).get(responseType), classType.getClass());
    }

    /**
     * @param entity
     * @throws ClientErrorException
     */
    public Response createEntity(T entity) throws ClientErrorException {
        return webTarget.request(mediaType).post(Entity.entity(gson.toJson(entity), mediaType));
    }

    /**
     * @param entity
     * @return
     * @throws ClientErrorException
     */
    public T updateEntity(T entity) throws ClientErrorException {
        return (T) gson.fromJson((String) webTarget.request(mediaType).put(Entity.entity(gson.toJson(entity), mediaType), responseType), classType.getClass());
    }

    /**
     * @param id
     * @return
     * @throws ClientErrorException
     */
    public Response deleteEntityById(String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        return gson.fromJson((String) resource.request(mediaType).delete(responseType), Response.class);
    }

    public void close() {
        client.close();
    }

}