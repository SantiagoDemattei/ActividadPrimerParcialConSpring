package Dominio;

import Database.UsuarioDb;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Estandar extends Categoria{
    public Estandar() {
        super();
        this.cantMax = 1;
        this.nombre = "Estandar";
    }
}
