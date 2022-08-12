package Consulta;

import Api.ApiCallVuelos;
import Dominio.Vuelo;
import java.util.List;

public abstract class Consultar {
    public List<Vuelo> filtrar(Busqueda b) throws Exception {
        List<Vuelo> vuelos;

        ApiCallVuelos api = new ApiCallVuelos();
        vuelos = api.consultarVuelos();

        return vuelos;
    }
}
