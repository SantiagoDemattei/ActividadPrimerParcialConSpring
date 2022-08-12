package Dominio;

public class Departure {
    private String departure_airport;
    private String departure_timezone;
    private String departure_iata;
    private String departure_iaco;
    private String departure_terminal;
    private String departure_gate;
    private String departure_delay;
    private String departure_scheduled;
    private String departure_estimated;
    private String departure_actual;
    private String departure_estimated_runway;
    private String departure_actual_runway;


    public String getDeparture_airport(){return departure_airport;}
    public String getDeparture_timezone(){return departure_timezone;}
    public String getDeparture_iata(){return departure_iata;}
    public String getDeparture_iaco(){return departure_iaco;}
    public String getDeparture_terminal(){return departure_terminal;}
    public String getDeparture_gate(){return departure_gate;}
    public String getDeparture_delay(){return departure_delay;}
    public String getDeparture_scheduled(){return departure_scheduled;}
    public String getDeparture_estimated(){return departure_estimated;}
    public String getDeparture_actual(){return departure_actual;}
    public String getDeparture_estimated_runway(){return departure_estimated_runway;}
    public String getDeparture_actual_runway(){return departure_actual_runway;}

    public void setDeparture_airport(String d){this.departure_airport = d;}
    public void setDeparture_timezone(String d){this.departure_timezone = d;}
    public void setDeparture_iata(String d){this.departure_iata = d;}
    public void setDeparture_iaco(String d){this.departure_iaco = d;}
    public void setDeparture_terminal(String d){this.departure_terminal = d;}
    public void setDeparture_gate(String d){this.departure_gate = d;}
    public void setDeparture_delay(String d){this.departure_delay = d;}
    public void setDeparture_scheduled(String d){this.departure_scheduled = d;}
    public void setDeparture_estimated(String d){this.departure_estimated = d;}
    public void setDeparture_actual(String d){this.departure_actual = d;}
    public void setDeparture_estimated_runway(String d){this.departure_estimated_runway = d;}
    public void setDeparture_actual_runway(String d){this.departure_actual_runway = d;}

}
