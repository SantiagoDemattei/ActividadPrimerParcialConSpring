package Dominio;

public class AptoParaDespegar extends Estado{
    public void cargarNafta(){
        this.vuelo.cargarTanque(0);
        UserService.mostrarMensajeConsulta(this.chequearEstado());
    }
    public String chequearEstado(){
        return "El vuelo esta listo para pasar al control de temperatura";
    }
}
