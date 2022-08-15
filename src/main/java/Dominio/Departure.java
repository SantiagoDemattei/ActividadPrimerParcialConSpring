package Dominio;

import javax.persistence.*;

@Entity
@Table(name="Departure")
public class Departure {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="Id")
    private Integer id;

    @Column(name="Airport")
    private String departure_airport;

    @Column(name="Timezone")
    private String departure_timezone;

    @Column(name="Iata")
    private String departure_iata;

    @Column(name="Icao")
    private String departure_iaco;

    @Column(name="Terminal")
    private String departure_terminal;

    @Column(name="Gate")
    private String departure_gate;

    @Column(name="Delay")
    private String departure_delay;

    @Column(name="Scheduled")
    private String departure_scheduled;

    @Column(name="Estimated")
    private String departure_estimated;

    @Column(name="Actual")
    private String departure_actual;

    @Column(name="Estimated_runway")
    private String departure_estimated_runway;

    @Column(name="Actual_runway")
    private String departure_actual_runway;

    public Integer getId(){return id;}
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

    public void setId(Integer a){this.id = a;}
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
