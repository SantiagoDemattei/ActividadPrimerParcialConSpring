package Dominio;

public abstract class Estado {
    public Vuelo vuelo;

    public void cargarNafta(){}
    public void setVuelo(Vuelo v){
        vuelo = v;
    }
}
