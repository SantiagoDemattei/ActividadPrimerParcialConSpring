package Dominio;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Clima {
    private String coord;
    private String weather;
    private String base;
    private String main;
    private String visibility;
    private String wind;
    private String clouds;
    private String dt;
    private String sys;
    private String timezone;
    private String id;
    private String name;
    private String cod;


    public Clima(JsonNode json) {
        this.coord = json.get("coord").toString();
        this.weather = json.get("weather").toString();
        this.base = json.get("base").toString();
        this.main = json.get("main").toString();
        this.visibility = json.get("visibility").toString();
        this.wind = json.get("wind").toString();
        this.clouds = json.get("clouds").toString();
        this.dt = json.get("dt").toString();
        this.sys = json.get("sys").toString();
        this.timezone = json.get("timezone").toString();
        this.id = json.get("id").toString();
        this.name = json.get("name").toString();
        this.cod = json.get("cod").toString();
    }

    public String getMain(){return main;}

    public Float getTemp(JsonNode json){
        String temp;
        temp = json.get("temp").toString();
        return Float.parseFloat(temp);
    }

    public Float conversorKelvinCelsius(Float temp){
        return temp - 273.15f;
    }
}
