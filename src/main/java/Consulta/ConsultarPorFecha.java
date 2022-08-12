package Consulta;

import java.time.LocalDate;
import java.util.*;
import Dominio.UserService;
import Dominio.Vuelo;

public class ConsultarPorFecha extends Consultar{
    public List<Vuelo> filtrar(Busqueda b) throws Exception {
        List<Vuelo> vuelos;
        List<Vuelo> vuelosFiltrados;
        vuelos = super.filtrar(b);
        String fecha = cambiarFormatoFecha(b.getFecha());
        vuelosFiltrados = filtrarPorFecha(vuelos, fecha);
        return vuelosFiltrados;
    }

    public List<Vuelo> filtrarPorFecha(List<Vuelo> vuelos, String fecha){
        List<Vuelo> vuelosFiltrados = new ArrayList<>();
        for(int i = 0; i < vuelos.size(); i++){
            Vuelo v = vuelos.get(i);
            if(v.getDeparture().getDeparture_scheduled().equals(fecha)){
                vuelosFiltrados.add(v);
            }
        }
        return vuelosFiltrados;
    }

    public String cambiarFormatoFecha(String horaMinutos){
        LocalDate fecha = LocalDate.now(); // devuelve la fecha del sistema en formato YYYY-MM-DD
        fecha = fecha.plusDays(1);
        String fechaFormateada = fecha + "T" + horaMinutos + ":00+00:00";
        return fechaFormateada;

    }
}
