package Dominio;


import javax.persistence.*;

@Entity
@Table(name="Arrival")
public class Arrival {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="Id")
    private Integer id;

    @Column(name="Airport")
    private String arrival_airport;

    @Column(name="Timezone")
    private String arrival_timezone;

    @Column(name="Iata")
    private String arrival_iata;

    @Column(name="Icao")
    private String arrival_iaco;

    @Column(name="Terminal")
    private String arrival_terminal;

    @Column(name="Gate")
    private String arrival_gate;

    @Column(name="Baggage")
    private String arrival_baggage;

    @Column(name="Delay")
    private String arrival_delay;

    @Column(name="Scheduled")
    private String arrival_scheduled;

    @Column(name="Estimated")
    private String arrival_estimated;

    @Column(name="Actual")
    private String arrival_actual;

    @Column(name="Estimated_runway")
    private String arrival_estimated_runway;

    @Column(name="Actual_runway")
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
