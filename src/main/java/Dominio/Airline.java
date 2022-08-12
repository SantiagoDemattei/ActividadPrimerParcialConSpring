package Dominio;

public class Airline {
    private String airline_name;
    private String airline_iata;
    private String airline_icao;

    public String getAirline_name(){return airline_name;}
    public String getAirline_iata(){return airline_iata;}
    public String getAirline_icao(){return airline_icao;}

    public void setAirline_name(String a){this.airline_name = a;}
    public void setAirline_iata(String a){this.airline_iata = a;}
    public void setAirline_icao(String a){this.airline_icao = a;}
}
