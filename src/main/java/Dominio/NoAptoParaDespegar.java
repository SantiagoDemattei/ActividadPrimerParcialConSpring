package Dominio;

import Database.CRUDVuelo;

public class NoAptoParaDespegar extends Estado{
    public void cargarNafta(){
        this.vuelo.cargarTanque(300);
        this.chequearEstado();
        UserService.mostrarMensajeAccion("Se han cargado 300 litros de combustible\n");
    }
    public void chequearEstado(){
        if(this.vuelo.getTanque() == 1000){ // la capacidad maxima de nafta de todos los aviones es 1000
            Estado e = new AptoParaDespegar();
            this.vuelo.setEstado(e);
            e.setVuelo(this.vuelo);
            this.vuelo.setEstadoString("AptoParaDespegar");
            this.vuelo.setComida("Arroz con pollo");
        }
        CRUDVuelo.actualizarVuelo(this.vuelo);
    }
}
