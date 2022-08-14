package Dominio;

import javax.persistence.Entity;


@Entity
public class Intermedio extends Categoria{
    public Intermedio() {
        super();
        this.cantMax = 1;
        this.nombre = "Intermedio";
    }
}
