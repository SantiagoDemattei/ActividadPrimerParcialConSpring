package Dominio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import Carga.*;
import java.util.Locale;

public class Vuelo implements Cloneable {
    private String flight_date;
    private String flight_status;
    Departure departure;
    JsonNode departureNode;
    Arrival arrival;
    JsonNode arrivalNode;
    Airline airline;
    JsonNode airlineNode;
    Flight flight;
    JsonNode flightNode;
    Aircraft aircraft;
    JsonNode aircraftNode;
    Live live;
    JsonNode liveNode;
    private Integer tanque;
    private Estado estado;
    private String comida;


    //CONSTRUCTOR VUELO
    public Vuelo(){
        this.departure = new Departure();
        this.arrival = new Arrival();
        this.airline = new Airline();
        this.flight = new Flight();
        this.aircraft = new Aircraft();
        this.live = new Live();
        this.tanque = 1000;
        this.estado = new AptoParaDespegar();
        this.estado.setVuelo(this);
        this.comida = "Arroz con pollo";
    }
    public Vuelo clonar(){
        Vuelo vuelo = null;
        try {
            vuelo = (Vuelo) clone();
            vuelo.setEstado(new NoAptoParaDespegar());
            vuelo.setComida(null);
            vuelo.setTanque(0);
        } catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return vuelo;
    }
    //GETTERS
    public String getFlight_date(){return flight_date;}
    public String getFlight_status(){return flight_status;}
    public Departure getDeparture(){return departure;}
    public Arrival getArrival(){return arrival;}
    public Airline getAirline(){return airline;}
    public Flight getFlight(){return flight;}
    public Aircraft getAircraft(){return aircraft;}
    public Live getLive(){return live;}
    public Integer getTanque(){return tanque;}
    public Estado getEstado(){return estado;}
    public String getComida(){return comida;}

    //SETTERS
    public void setFlight_date(String f){this.flight_date = f;}
    public void setFlight_status(String f){this.flight_status = f;}
    public void setDeparture(JsonNode n) throws JsonProcessingException {
        if(!n.isNull()){
            this.departure = new Departure();
            departureNode = n;
            departure.setDeparture_airport(departureNode.get("airport").asText());
            departure.setDeparture_timezone(departureNode.get("timezone").asText());
            departure.setDeparture_iata(departureNode.get("iata").asText());
            departure.setDeparture_iaco(departureNode.get("icao").asText());
            departure.setDeparture_terminal(departureNode.get("terminal").asText());
            departure.setDeparture_gate(departureNode.get("gate").asText());
            departure.setDeparture_delay(departureNode.get("delay").asText());
            departure.setDeparture_scheduled(departureNode.get("scheduled").asText());
            departure.setDeparture_estimated(departureNode.get("estimated").asText());
            departure.setDeparture_actual(departureNode.get("actual").asText());
            departure.setDeparture_estimated_runway(departureNode.get("estimated_runway").asText());
            departure.setDeparture_actual_runway(departureNode.get("actual_runway").asText());
        } else {
            this.departure = null;
            this.departureNode = null;
        }
    }
    public void setArrival(JsonNode n) throws JsonProcessingException {
        if(!n.isNull()){
            this.arrival = new Arrival();
            arrivalNode = n;
            arrival.setArrival_airport(arrivalNode.get("airport").asText());
            arrival.setArrival_timezone(arrivalNode.get("timezone").asText());
            arrival.setArrival_iata(arrivalNode.get("iata").asText());
            arrival.setArrival_iaco(arrivalNode.get("icao").asText());
            arrival.setArrival_terminal(arrivalNode.get("terminal").asText());
            arrival.setArrival_gate(arrivalNode.get("gate").asText());
            arrival.setArrival_baggage(arrivalNode.get("baggage").asText());
            arrival.setArrival_delay(arrivalNode.get("delay").asText());
            arrival.setArrival_scheduled(arrivalNode.get("scheduled").asText());
            arrival.setArrival_estimated(arrivalNode.get("estimated").asText());
            arrival.setArrival_actual(arrivalNode.get("actual").asText());
            arrival.setArrival_estimated_runway(arrivalNode.get("estimated_runway").asText());
            arrival.setArrival_actual_runway(arrivalNode.get("actual_runway").asText());

        } else {
            this.arrival = null;
            this.arrivalNode = null;
        }

    }
    public void setAirline(JsonNode n) throws JsonProcessingException {
        if(!n.isNull()){
            this.airline = new Airline();
            airlineNode = n;
            airline.setAirline_name(airlineNode.get("name").asText());
            airline.setAirline_iata(airlineNode.get("iata").asText());
            airline.setAirline_icao(airlineNode.get("icao").asText());
        }
        else {
            this.airline = null;
            this.airlineNode = null;
        }
    }
    public void setFlight(JsonNode n) throws JsonProcessingException {
        if(!n.isNull()){
            this.flight = new Flight();
            flightNode = n;
            flight.setFlight_number(flightNode.get("number").asText());
            flight.setFlight_iata(flightNode.get("iata").asText());
            flight.setFlight_icao(flightNode.get("icao").asText());
            flight.setFlight_codeshared(flightNode.get("codeshared").asText());

        } else {
            this.flight = null;
            this.flightNode = null;
        }
    }
    public void setAircraft(JsonNode n) throws JsonProcessingException {
        if(!n.isNull()){
            this.aircraft = new Aircraft();
            aircraftNode = n;
            aircraft.setAircraft_registration(aircraftNode.get("registration").asText());
            aircraft.setAircraft_iata(aircraftNode.get("iata").asText());
            aircraft.setAircraft_icao(aircraftNode.get("icao").asText());
            aircraft.setAircraft_icao24(aircraftNode.get("icao24").asText());

        } else {
            this.aircraft = null;
            this.aircraftNode = null;
        }
    }
    public void setLive(JsonNode n) throws JsonProcessingException {
        if(!n.isNull()){
            this.live = new Live();
            liveNode = n;
            live.setLive_updated(liveNode.get("updated").asText());
            live.setLive_latitude(liveNode.get("latitude").asText());
            live.setLive_longitude(liveNode.get("longitude").asText());
            live.setLive_altitude(liveNode.get("altitude").asText());
            live.setLive_direction(liveNode.get("direction").asText());
            live.setLive_speed_horizontal(liveNode.get("speed_horizontal").asText());
            live.setLive_speed_vertical(liveNode.get("speed_vertical").asText());
            live.setLive_is_ground(liveNode.get("is_ground").asText());

        } else {
            this.live = null;
            this.liveNode = null;
        }
    }

    public String getCiudadOrigen() throws Exception {
        String codigoIata = getDeparture().getDeparture_iata().toUpperCase();
        LectorExcel lector = LectorExcel.getInstance();
        final String yourDesktopPath = System.getProperty("user.dir") + "/src/main/java/Carga/";
        String ciudad = lector.findRows(yourDesktopPath + "codigosPaisesAeropuertos2.xlsx", codigoIata);
        return ciudad;
    }

    public void setTanque(Integer valor){this.tanque = valor;}
    public void setEstado(Estado e){this.estado = e;}
    public void setComida(String com){this.comida = com;}

    public void cargarTanque(Integer valor){
        if(this.tanque + valor <= 1000){
            this.tanque += valor;
        } else {
            this.tanque = 1000;
        }
    }

    public void cargarCombustible(){
        estado.cargarNafta();
    }
}
