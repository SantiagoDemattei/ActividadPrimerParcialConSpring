package Dominio;

import javax.persistence.*;

@Entity
public class Estandar extends Categoria{
    public Estandar() {
        super();
        this.cantMax = 1;
        this.nombre = "Estandar";
    }
}
