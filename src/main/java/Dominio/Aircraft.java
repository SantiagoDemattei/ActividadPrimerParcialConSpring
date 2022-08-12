package Dominio;

public class Aircraft {
    private String aircraft_registration;
    private String aircraft_iata;
    private String aircraft_icao;
    private String aircraft_icao24;

    public String getAircraft_registration(){return aircraft_registration;}
    public String getAircraft_iata(){return aircraft_iata;}
    public String getAircraft_icao(){return aircraft_icao;}
    public String getAircraft_icao24(){return aircraft_icao24;}

    public void setAircraft_registration(String a){this.aircraft_registration = a;}
    public void setAircraft_iata(String a){this.aircraft_iata = a;}
    public void setAircraft_icao(String a){this.aircraft_icao = a;}
    public void setAircraft_icao24(String a){this.aircraft_icao24 = a;}
}
