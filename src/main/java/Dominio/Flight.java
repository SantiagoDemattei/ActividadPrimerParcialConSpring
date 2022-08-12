package Dominio;

public class Flight {
    private String flight_number;
    private String flight_iata;
    private String flight_icao;
    private String flight_codeshared;

    public String getFlight_number(){return flight_number;}
    public String getFlight_iata(){return flight_iata;}
    public String getFlight_icao(){return flight_icao;}
    public String getFlight_codeshared(){return flight_codeshared;}

    public void setFlight_number(String a){this.flight_number = a;}
    public void setFlight_iata(String a){this.flight_iata = a;}
    public void setFlight_icao(String a){this.flight_icao = a;}
    public void setFlight_codeshared(String a){this.flight_codeshared = a;}
}
