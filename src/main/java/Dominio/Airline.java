package Dominio;

import javax.persistence.*;

@Entity
@Table(name="Airline")
public class Airline {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="Id")
    private Integer id;

    @Column(name="Name")
    private String airline_name;

    @Column(name="Iata")
    private String airline_iata;

    @Column(name="Icao")
    private String airline_icao;

    public Integer getId(){return id;}
    public String getAirline_name(){return airline_name;}
    public String getAirline_iata(){return airline_iata;}
    public String getAirline_icao(){return airline_icao;}

    public void setId(Integer id){this.id = id;}
    public void setAirline_name(String a){this.airline_name = a;}
    public void setAirline_iata(String a){this.airline_iata = a;}
    public void setAirline_icao(String a){this.airline_icao = a;}
}
