package Dominio;

public class Arrival {
    private String arrival_airport;
    private String arrival_timezone;
    private String arrival_iata;
    private String arrival_iaco;
    private String arrival_terminal;
    private String arrival_gate;
    private String arrival_baggage;
    private String arrival_delay;
    private String arrival_scheduled;
    private String arrival_estimated;
    private String arrival_actual;
    private String arrival_estimated_runway;
    private String arrival_actual_runway;

    public String getArrival_airport(){return arrival_airport;}
    public String getArrival_timezone(){return arrival_timezone;}
    public String getArrival_iata(){return arrival_iata;}
    public String getArrival_iaco(){return arrival_iaco;}
    public String getArrival_terminal(){return arrival_terminal;}
    public String getArrival_gate(){return arrival_gate;}
    public String getArrival_baggage(){return arrival_baggage;}
    public String getArrival_delay(){return arrival_delay;}
    public String getArrival_scheduled(){return arrival_scheduled;}
    public String getArrival_estimated(){return arrival_estimated;}
    public String getArrival_actual(){return arrival_actual;}
    public String getArrival_estimated_runway(){return arrival_estimated_runway;}
    public String getArrival_actual_runway(){return arrival_actual_runway;}

    public void setArrival_airport(String a){this.arrival_airport = a;}
    public void setArrival_timezone(String a){this.arrival_timezone = a;}
    public void setArrival_iata(String a){this.arrival_iata = a;}
    public void setArrival_iaco(String a){this.arrival_iaco = a;}
    public void setArrival_terminal(String a){this.arrival_terminal = a;}
    public void setArrival_gate(String a){this.arrival_gate = a;}
    public void setArrival_baggage(String a){this.arrival_baggage = a;}
    public void setArrival_delay(String a){this.arrival_delay = a;}
    public void setArrival_scheduled(String a){this.arrival_scheduled = a;}
    public void setArrival_estimated(String a){this.arrival_estimated = a;}
    public void setArrival_actual(String a){this.arrival_actual = a;}
    public void setArrival_estimated_runway(String a){this.arrival_estimated_runway = a;}
    public void setArrival_actual_runway(String a){this.arrival_actual_runway = a;}
}
