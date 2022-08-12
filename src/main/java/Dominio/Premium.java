package Dominio;

import java.util.List;

public class Premium {
    public void consultarVuelos (Usuario user) throws Exception {
        List<Vuelo> vuelos;
        vuelos = user.getBusqueda().buscarVuelos();
        if(vuelos.size() == 0){
            UserService.mostrarMensajeDeError("La solicitud ingresada no es compatible con los vuelos de este sistema. Por favor intentelo de nuevo!");
        }
        else{
            UserService.mostrarListadoVuelos(vuelos);
        }
    }

    public Boolean verificarPagoAlDia(Usuario user)  {
        if(user.getPagaMembresia()){
            return true;
        }
        else{
            return false;
        }
    }
}
