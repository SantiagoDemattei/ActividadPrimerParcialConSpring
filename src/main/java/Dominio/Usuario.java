package Dominio;

import Carga.RepoVuelosNuevo;
import Consulta.Busqueda;
import Consulta.Consultar;
import Database.UsuarioDb;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import java.util.*;

public class Usuario {
    private Integer id;
    private String nombre;
    private String apellido;
    private String mail;
    //private List<Vuelo> vuelosFiltrados;
    private Busqueda busqueda;
    private Vuelo prototipo;
    private String password;
    private Categoria categoria;
    private Boolean pagaMembresia;


    //SETTERS
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setApellido(String a) {this.apellido = a;}
    public void setMail(String mail) {this.mail = mail;}
    public void setPassword(String password) {this.password = password;}
    public void setId(Integer id) {this.id = id;}
    public void setCategoria(Categoria c){this.categoria = c;}
    public void setBusqueda(Consultar estrategy, String des, String date, String aero) {
        Busqueda b = new Busqueda(estrategy, des, date, aero);
        this.busqueda = b;
    }
    public void setPrototipo(Vuelo vuelo){this.prototipo = vuelo;}
    public void setPagaMembresia(Boolean p) {this.pagaMembresia = p;}

    //GETTERS
    public Categoria getCategoria() {return categoria;}
    public String getNombre() {return this.nombre;}
    public String getApellido() {return this.apellido;}
    public String getMail() {return this.mail;}
    public String getPassword() {return this.password;}
    public Integer getId() {return this.id;}
    public Vuelo getPrototipo(){return this.prototipo;}
    public Busqueda getBusqueda(){return this.busqueda;}
    public Boolean getPagaMembresia(){return this.pagaMembresia;}

    //CONSTRUCTORES
    public Usuario(String n, String a, String m, String pass, Categoria c, Boolean b) {
        this.nombre = n;
        this.apellido = a;
        this.mail = m;
        this.password = pass;
        this.categoria = c;
        this.pagaMembresia = b;
    }

    public Usuario(String m, String p) {
        this.mail = m;
        this.password = p;
    }
    public Usuario() {
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", categoria=" + categoria +
                ", pagaMembresia=" + pagaMembresia +
                '}';
    }

    //METODOS
    public void setearDatosVueloClonado(String numVuelo, String puertaEmbarque) throws Exception{
        RepoVuelosNuevo repo = RepoVuelosNuevo.getInstance();
        Vuelo vueloClonado = this.getPrototipo().clonar();
        vueloClonado.getFlight().setFlight_number(numVuelo);
        vueloClonado.getDeparture().setDeparture_gate(puertaEmbarque);
        repo.cargarVuelo(vueloClonado);
    }


    public void cargarDatosNuevoVuelo(String aeropuertoDestino) throws Exception{
        Scanner sc4 = new Scanner(System.in);
        Vuelo vueloNuevo = new Vuelo();
        RepoVuelosNuevo repo = RepoVuelosNuevo.getInstance();
        vueloNuevo.getArrival().setArrival_airport(aeropuertoDestino);
        UserService.cargarDatosBase(sc4, vueloNuevo);
        UserService.cargarDatosDeparture(sc4, vueloNuevo);
        UserService.cargarDatosArrival(sc4, vueloNuevo);
        UserService.cargarDatosAirline(sc4, vueloNuevo);
        UserService.cargarDatosFlight(sc4, vueloNuevo);
        UserService.cargarDatosAircraft(sc4, vueloNuevo);
        Estado e = new NoAptoParaDespegar();
        vueloNuevo.setEstado(e);
        e.setVuelo(vueloNuevo);
        vueloNuevo.setTanque(0);
        vueloNuevo.setComida(null);
        repo.cargarVuelo(vueloNuevo);
    }


    public void pagar() throws Exception{
        if(getPagaMembresia()){
            UserService.mostrarMensajeDeError("Ya realizaste el pago de la membresia\n");
            System.out.println();
        }
        else {
            setPagaMembresia(true);
            UsuarioDb.actualizarUsuarioEnDb(this);
            UserService.mostrarMensajeConsulta("El pago se ha efectuado con exito!! \n");
            System.out.println();
        }
    }
}
