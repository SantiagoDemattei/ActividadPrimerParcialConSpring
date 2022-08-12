package Consulta;

import java.util.ArrayList;
import Dominio.Vuelo;
import java.util.List;

public class ConsultarPorAerolinea extends Consultar{
    public List<Vuelo> filtrar(Busqueda b) throws Exception {
        List<Vuelo> vuelos;
        List<Vuelo> vuelosFiltrados;
        vuelos = super.filtrar(b);
        vuelosFiltrados = filtrarPorAerolinea(vuelos, b.getAerolinea());
        return vuelosFiltrados;
    }

    public List<Vuelo> filtrarPorAerolinea(List<Vuelo> vuelos, String aerolinea){
        List<Vuelo> vuelosFiltrados = new ArrayList<>();
        for(int i = 0; i < vuelos.size(); i++){
            Vuelo v = vuelos.get(i);
            if(v.getAirline().getAirline_name().equals(aerolinea)){
                vuelosFiltrados.add(v);
            }
        }
        return vuelosFiltrados;
    }
}
