package Consulta;

import java.util.List;
import Dominio.Vuelo;

public class Busqueda {
    Consultar estrategia;
    String aeropuerto;
    String fecha;
    String aerolinea;


    public Busqueda(Consultar estrategy, String des, String date, String aero) {
        this.estrategia = estrategy;
        this.aeropuerto = des;
        this.fecha = date;
        this.aerolinea = aero;
    }

    public List<Vuelo> buscarVuelos() throws Exception {
        return estrategia.filtrar(this);
    }

    public String getFecha() {
        return fecha;
    }

    public String getAeropuertoDestino() {
        return aeropuerto;
    }

    public String getAerolinea() {
        return aerolinea;
    }
}
