package Dominio;


import javax.persistence.*;

@Entity
@Table(name="Flight")
public class Flight {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="Id")
    private Integer id;

    @Column(name="Number")
    private String flight_number;

    @Column(name="Iata")
    private String flight_iata;

    @Column(name="Icao")
    private String flight_icao;

    @Column(name="Codeshared")
    private String flight_codeshared;

    public Integer getId(){return id;}
    public String getFlight_number(){return flight_number;}
    public String getFlight_iata(){return flight_iata;}
    public String getFlight_icao(){return flight_icao;}
    public String getFlight_codeshared(){return flight_codeshared;}

    public void setId(Integer id){this.id = id;}
    public void setFlight_number(String a){this.flight_number = a;}
    public void setFlight_iata(String a){this.flight_iata = a;}
    public void setFlight_icao(String a){this.flight_icao = a;}
    public void setFlight_codeshared(String a){this.flight_codeshared = a;}
}
