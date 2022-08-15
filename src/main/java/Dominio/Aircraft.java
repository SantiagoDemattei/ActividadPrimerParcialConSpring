package Dominio;

import javax.persistence.*;

@Entity
@Table(name="Aircraft")
public class Aircraft {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="Id")
    private Integer id;

    @Column(name="Registration")
    private String aircraft_registration;

    @Column(name="Iata")
    private String aircraft_iata;

    @Column(name="Icao")
    private String aircraft_icao;

    @Column(name="Icao24")
    private String aircraft_icao24;

    public Integer getId(){return id;}
    public String getAircraft_registration(){return aircraft_registration;}
    public String getAircraft_iata(){return aircraft_iata;}
    public String getAircraft_icao(){return aircraft_icao;}
    public String getAircraft_icao24(){return aircraft_icao24;}

    public void setId(Integer id){this.id = id;}
    public void setAircraft_registration(String a){this.aircraft_registration = a;}
    public void setAircraft_iata(String a){this.aircraft_iata = a;}
    public void setAircraft_icao(String a){this.aircraft_icao = a;}
    public void setAircraft_icao24(String a){this.aircraft_icao24 = a;}
}
