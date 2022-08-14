package Dominio;

import Carga.RepoVuelosNuevo;
import Consulta.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import Consulta.ConsultarPorAeropuertoDestino;
import Database.CRUDUsuario;
import Database.Encriptacion;

public class UserService {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public static Usuario registrarUser() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Scanner console = new Scanner(System.in);
        Integer option;

        System.out.println("\nIngrese email: ");
        String mail = console.nextLine();
        if(mail == null){
            mostrarMensajeDeError("\nEl email no puede estar vacio\n");
            return registrarUser();
        }
        if(CRUDUsuario.buscarUsuarioPorMail(mail) != null){
            mostrarMensajeDeError("\nEl email ya existe, intente con otro");
            return registrarUser();
        }
        System.out.println("\nIngrese contrasena: ");
        String pass = console.nextLine();
        pass = Encriptacion.encriptacion(pass);

        Usuario user = new Usuario(mail, pass);

        user.setPagaMembresia(false);

        System.out.println("\nIngrese nombre: ");
        user.setNombre(console.nextLine());

        System.out.println("\nIngrese apellido: ");
        user.setApellido(console.nextLine());

        System.out.println("\nIngrese categoria de usuario: 1-Estandar 2-Intermedio 3-Premium");
        option = console.nextInt();
        console.nextLine();
        instanciarCategoria(option, user);
        System.out.println();
        return user;
    }

    public static void instanciarCategoria(Integer c, Usuario user){
        Categoria categoria;
        while(true) {
            switch (c) {
                case 1:
                    categoria = new Estandar();
                    user.setCategoria(categoria);
                    return;
                case 2:
                    categoria = new Intermedio();
                    user.setCategoria(categoria);
                    return;
                case 3:
                    categoria = new PremiumAdapter();
                    user.setCategoria(categoria);
                    return;
                default:
                    mostrarMensajeDeError("Categoria no valida");
            }
        }
    }

    public static void registrarUsuario(Usuario user) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        CRUDUsuario.insertarUsuario(user);
    }

    public static Usuario buscarLoginEnDb(String mail, String pass) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Usuario user = CRUDUsuario.buscarUsuarioPorMail(mail);
        if(user == null){
            mostrarMensajeDeError("\nNo se ha encontrado el email ingresado\n");
        }else{
            if(CRUDUsuario.coincideContrasenia(pass, Encriptacion.desencriptacion(user.getPassword()))){
                System.out.println();
                UserService.clearScreen();
                UserService.mostrarMensajeConsulta("Bienvenido/a " + user.getNombre());
            }else{
                UserService.mostrarMensajeDeError("\nContraseña incorrecta\n");
                System.out.println();
                user = null;
            }
        }
        return user;
    }

    public static void menuConsultarVuelo(Usuario user) throws Exception {
        String option = "K";
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        while (option != "quit") {
            Boolean bandera = true;
            System.out.println("Ingrese la forma de busqueda: A: aerolinea, D: destino, F: fecha, S: salir");
            option = sc.nextLine();
            switch (option) {
                case "S":
                    mostrarMensajeAccion("Fin busqueda");
                    Thread.sleep(500);
                    clearScreen();
                    option = "quit";
                    bandera = false;
                    break;
                case "A":
                    ConsultarPorAerolinea consultaA = new ConsultarPorAerolinea();
                    System.out.println("Ingrese la aerolinea: ");
                    String aerolinea = sc2.nextLine();
                    user.setBusqueda(consultaA, null, null, aerolinea);
                    System.out.println();
                    UserService.mostrarMensajeAccion("Buscando los vuelos que pertenecen a la aerolinea: " + aerolinea);
                    if(user.getCategoria().getClass().getSimpleName().equals("PremiumAdapter") && !user.getPagaMembresia()){
                        option = "quit";
                    }

                    if(!user.getCategoria().getClass().getSimpleName().equals("PremiumAdapter")){
                        option = "quit";
                    }
                    break;
                case "D":
                    ConsultarPorAeropuertoDestino consultaD = new ConsultarPorAeropuertoDestino();
                    System.out.println("Ingrese el aeropuerto de destino: ");
                    String destino = sc2.nextLine();
                    user.setBusqueda(consultaD, destino, null, null);
                    UserService.mostrarMensajeAccion("Buscando los vuelos con destino al aeropuerto: " + destino);
                    if(user.getCategoria().getClass().getSimpleName().equals("PremiumAdapter") && !user.getPagaMembresia()){
                        option = "quit";
                    }

                    if(!user.getCategoria().getClass().getSimpleName().equals("PremiumAdapter")){
                        option = "quit";
                    }
                    break;
                case "F":
                    ConsultarPorFecha consultaF = new ConsultarPorFecha();
                    // formato requerido por la api: YYYY-MM-DDThh:mm:ss+hh:ss (Ejemplo: 2022-08-01T14:50:00+00:00)
                    System.out.println("Ingrese la hora del vuelo en el siguiente formato: hora:minuto");
                    String fecha = sc2.nextLine();
                    user.setBusqueda(consultaF, null, fecha, null);
                    UserService.mostrarMensajeAccion("Buscando los vuelos con hora: " + fecha);

                    if(user.getCategoria().getClass().getSimpleName().equals("PremiumAdapter") && !user.getPagaMembresia()){
                        option = "quit";
                    }

                    if(!user.getCategoria().getClass().getSimpleName().equals("PremiumAdapter")){
                        option = "quit";
                    }
                    break;
                default:
                    UserService.mostrarMensajeDeError("Opcion invalida");
                    bandera = false;
                    break;
            }
            if(bandera){
                user.getCategoria().consultarVueloExistente(user);
            }
        }
    }

    public static void mostrarListadoVuelos(List<Vuelo> vuelos) {
        mostrarMensajeAccion("Mostrando Vuelos...");
        for(int i=0; i < vuelos.size(); i++){
            Vuelo vuelo = vuelos.get(i);
            System.out.println("----------------------------------------------- VUELO: " + i + " -----------------------------------------------");
            System.out.println();
            System.out.println("\nDATA: ");
            System.out.println(vuelo.getFlight_date() + " " + vuelo.getFlight_status());

            if(vuelo.getDeparture() != null) {
                System.out.println("\nDEPARTURE: ");
                System.out.println("Departure Airport: " + vuelo.getDeparture().getDeparture_airport());
                System.out.println("Departure Timezone: " + vuelo.getDeparture().getDeparture_timezone());
                System.out.println("Departure Airport Iata Code: " + vuelo.getDeparture().getDeparture_iata());
                System.out.println("Departure Airport Icao Code: " + vuelo.getDeparture().getDeparture_iaco());
                System.out.println("Departure Airport Terminal: " + vuelo.getDeparture().getDeparture_terminal());
                System.out.println("Departure Airport Gate: " + vuelo.getDeparture().getDeparture_gate());
                System.out.println("Departure Delay: " + vuelo.getDeparture().getDeparture_delay());
                System.out.println("Departure Scheduled Time: " + vuelo.getDeparture().getDeparture_scheduled());
                System.out.println("Departure Estimated Time: " + vuelo.getDeparture().getDeparture_estimated());
                System.out.println("Departure Actual Time: " + vuelo.getDeparture().getDeparture_actual());
                System.out.println("Departure Estimated Runway: " + vuelo.getDeparture().getDeparture_estimated_runway());
                System.out.println("Departure Actual Runway: " + vuelo.getDeparture().getDeparture_actual_runway());
            }

            if(vuelo.getArrival() != null){
                System.out.println("\nARRIVAL: ");
                System.out.println("Arrival Airport: " + vuelo.getArrival().getArrival_airport());
                System.out.println("Arrival Timezone: " + vuelo.getArrival().getArrival_timezone());
                System.out.println("Arrival Airport Iata Code: " + vuelo.getArrival().getArrival_iata());
                System.out.println("Arrival Airport Icao Code: " + vuelo.getArrival().getArrival_iaco());
                System.out.println("Arrival Airport Terminal: " + vuelo.getArrival().getArrival_terminal());
                System.out.println("Arrival Airport Gate: " + vuelo.getArrival().getArrival_gate());
                System.out.println("Arrival Baggage Terminal: " + vuelo.getArrival().getArrival_baggage());
                System.out.println("Arrival Delay: " + vuelo.getArrival().getArrival_delay());
                System.out.println("Arrival Scheduled Time: " + vuelo.getArrival().getArrival_scheduled());
                System.out.println("Arrival Estimated Time: " + vuelo.getArrival().getArrival_estimated());
                System.out.println("Arrival Actual Time: " + vuelo.getArrival().getArrival_actual());
                System.out.println("Arrival Estimated Runway: " + vuelo.getArrival().getArrival_estimated_runway());
                System.out.println("Arrival Actual Runway: " + vuelo.getArrival().getArrival_actual_runway());
            }

            if(vuelo.getAirline() != null) {
                System.out.println("\nAIRLINE: ");
                System.out.println("Airline Name:" + vuelo.getAirline().getAirline_name());
                System.out.println("Airline Code Iata Code: " + vuelo.getAirline().getAirline_iata());
                System.out.println("Airline Code Icao Code: " + vuelo.getAirline().getAirline_icao());
            }

            if(vuelo.getFlight() != null) {
                System.out.println("\nFLIGHT: ");
                System.out.println("Flight Number: " + vuelo.getFlight().getFlight_number());
                System.out.println("Flight Code Iata Code: " + vuelo.getFlight().getFlight_iata());
                System.out.println("Flight Code Icao Code: " + vuelo.getFlight().getFlight_icao());
                System.out.println("Flight Codeshared: " + vuelo.getFlight().getFlight_codeshared());
            }

            if(vuelo.getAircraft() != null) {
                System.out.println("\nAIRCRAFT: ");
                System.out.println("Aircraft Registration: " + vuelo.getAircraft().getAircraft_registration());
                System.out.println("Aircraft Iata Code: " + vuelo.getAircraft().getAircraft_iata());
                System.out.println("Aircraft Icao Code: " + vuelo.getAircraft().getAircraft_icao());
                System.out.println("Aircraft Icao24 Code: " + vuelo.getAircraft().getAircraft_icao24());
            }

            System.out.println("\nESTADO: " + vuelo.getEstado().getClass().getSimpleName());

            if(vuelo.getComida() != null) {
                System.out.println("\nCOMIDA: " + vuelo.getComida());
            }
            else
            {
                System.out.println("\nCOMIDA: No dispone.");
            }

            System.out.println("\nTANQUE: " + vuelo.getTanque() + " litros. ");

            System.out.println("--------------------------------------------------------------------------------------------------------");
        }
        System.out.println();
    }

    public static void menuCargarVuelo(Usuario user) throws Exception {
        Scanner sc4 = new Scanner(System.in);
        System.out.println("Ingrese el aeropuerto de destino del vuelo que quiere cargar: ");
        String destino = sc4.nextLine();
        ConsultarPorAeropuertoDestino consultaD = new ConsultarPorAeropuertoDestino();
        user.setBusqueda(consultaD, destino, null, null);
        mostrarMensajeConsulta("Aguarde un momento por favor...");
        List<Vuelo> vuelos;
        vuelos = user.getBusqueda().buscarVuelos();

        if(vuelos.size() == 0) {
            UserService.mostrarMensajeDeError("\nNuestro sistema no ha podido encontrar vuelos existentes para el destino ingresado\n");
            System.out.println("A continuacion, ingrese los datos del nuevo vuelo");
            user.cargarDatosNuevoVuelo(destino);
        }
        else{
            System.out.println("Los vuelos que usted puede utilizar como plantilla son los siguientes: ");
            Thread.sleep(1500);
            mostrarListadoVuelos(vuelos);
            Scanner sc5 = new Scanner(System.in);
            System.out.println("Seleccione el ID del vuelo que desea usar como plantilla: ");
            Integer indice = sc5.nextInt();
            sc5.nextLine();
            Vuelo vuelo = vuelos.get(indice);
            System.out.println("Seleccione el numero del vuelo nuevo: ");
            String numero = sc5.nextLine();
            System.out.println("Seleccione la puerta de embarque del vuelo nuevo: ");
            String puerta = sc5.nextLine();
            System.out.println();
            user.setPrototipo(vuelo);
            user.setearDatosVueloClonado(numero, puerta);
        }

    }

    public static Integer mostrarMenuPremium(Usuario user, Scanner sc) throws Exception {
        Integer option = -1;
        mostrarMenuIntermedio(user, sc);
        System.out.println(" 6. Pagar");
        option =  sc.nextInt();
        mostrarOpciones(user, option, true);
        return option;
    }

    public static void mostrarMenuEstandar(Usuario user, Scanner sc) throws Exception {
        System.out.println("Ingresa un numero segun la operacion a realizar \n " +
                "0. Salir\n " +
                "1. Consultar vuelos \n " +
                "2. Cargar nuevo vuelo \n " +
                "3. Mostrar menus cargados en el sistema \n" +
                "4. Cargar tanque del avion de un vuelo cargado");
    }
    public static void mostrarMenuIntermedio(Usuario user, Scanner sc) throws Exception {
        System.out.println("Ingresa un numero segun la operacion a realizar \n " +
                "0. Salir\n " +
                "1. Consultar vuelos \n " +
                "2. Cargar nuevo vuelo \n" +
                "3. Mostrar menus cargados en el sistema \n" +
                "4. Cargar tanque del avion de un vuelo cargado\n" +
                "5. Controlar temperatura para el despegue de los vuelos cargados ");
    }

    public static void mostrarOpciones(Usuario user, Integer option, Boolean esPremium) throws Exception {
        switch (option) {
            case 0:
                mostrarMensajeAccion("Cerrando Sesion...");
                clearScreen();
                break;
            case 1:
                mostrarMensajeAccion("Consultando vuelos...");
                menuConsultarVuelo(user);
                break;
            case 2:
                mostrarMensajeAccion("Cargando nuevo vuelo...");
                menuCargarVuelo(user);
                break;
            case 3:
                mostrarVuelosCargados();
                break;
            case 4:
                llenarTanque();
                break;

            case 5:
                mostrarMensajeAccion("Controlando temperatura para el despegue de vuelos cargados...");
                RepoVuelosNuevo.controlarTemperaturaParaDespegue();
                break;
            case 6:
                if (esPremium) {
                    user.pagar();
                }
                else {
                    UserService.mostrarMensajeDeError("\nOpcion invalida\n");
                }
                break;

            default:
                UserService.mostrarMensajeDeError("\nOpcion invalida\n");
                break;
        }
    }

    public static boolean mostrarVuelosCargados(){
        RepoVuelosNuevo repo = RepoVuelosNuevo.getInstance();
        List<Vuelo> vuelos = repo.getVuelosNuevos();
        if(vuelos.size() == 0){
            UserService.mostrarMensajeDeError("No hay vuelos cargados en el sistema");
            System.out.println();
            return false;
        }
        mostrarListadoVuelos(vuelos);
        return true;
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public static void cargarDatosBase(Scanner sc4, Vuelo vueloNuevo){
        System.out.println("Ingrese la fecha del vuelo: ");
        vueloNuevo.setFlight_date(sc4.nextLine());
        System.out.println("Ingrese el status del vuelo: ");
        vueloNuevo.setFlight_status(sc4.nextLine());
    }

    public static void cargarDatosDeparture(Scanner sc4, Vuelo vueloNuevo){

        System.out.println("Ingrese aeropuerto de origen: ");
        vueloNuevo.getDeparture().setDeparture_airport(sc4.nextLine());
        System.out.println("Ingrese timezone de origen: ");
        vueloNuevo.getDeparture().setDeparture_timezone(sc4.nextLine());
        System.out.println("Ingrese codigo iata de origen: ");
        vueloNuevo.getDeparture().setDeparture_iata(sc4.nextLine().toUpperCase());
        System.out.println("Ingrese codigo icao de origen: ");
        vueloNuevo.getDeparture().setDeparture_iaco(sc4.nextLine().toUpperCase());
        System.out.println("Ingrese terminal de origen: ");
        vueloNuevo.getDeparture().setDeparture_terminal(sc4.nextLine());
        System.out.println("Ingrese gate de origen: ");
        vueloNuevo.getDeparture().setDeparture_gate(sc4.nextLine());
        System.out.println("Ingrese delay de origen: ");
        vueloNuevo.getDeparture().setDeparture_delay(sc4.nextLine());
        System.out.println("Ingrese tiempo scheduled de origen: ");
        vueloNuevo.getDeparture().setDeparture_scheduled(sc4.nextLine());
        System.out.println("Ingrese tiempo estimated de origen: ");
        vueloNuevo.getDeparture().setDeparture_estimated(sc4.nextLine());
        System.out.println("Ingrese tiempo actual de origen: ");
        vueloNuevo.getDeparture().setDeparture_actual(sc4.nextLine());
        System.out.println("Ingrese pista estimada de origen: ");
        vueloNuevo.getDeparture().setDeparture_estimated_runway(sc4.nextLine());
        System.out.println("Ingrese pista actual de origen: ");
        vueloNuevo.getDeparture().setDeparture_actual_runway(sc4.nextLine());
    }
    public static void cargarDatosArrival(Scanner sc4, Vuelo vueloNuevo){
        System.out.println("Ingrese timezone destino: ");
        vueloNuevo.getArrival().setArrival_timezone(sc4.nextLine());
        System.out.println("Ingrese codigo iata de destino: ");
        vueloNuevo.getArrival().setArrival_iata(sc4.nextLine().toUpperCase());
        System.out.println("Ingrese codigo icao de destino: ");
        vueloNuevo.getArrival().setArrival_iaco(sc4.nextLine().toUpperCase());
        System.out.println("Ingrese terminal de destino: ");
        vueloNuevo.getArrival().setArrival_terminal(sc4.nextLine());
        System.out.println("Ingrese gate de destino: ");
        vueloNuevo.getDeparture().setDeparture_gate(sc4.nextLine());
        System.out.println("Ingrese baggage de destino: ");
        vueloNuevo.getArrival().setArrival_baggage(sc4.nextLine());
        System.out.println("Ingrese delay de destino: ");
        vueloNuevo.getArrival().setArrival_delay(sc4.nextLine());
        System.out.println("Ingrese tiempo scheduled de destino: ");
        vueloNuevo.getArrival().setArrival_scheduled(sc4.nextLine());
        System.out.println("Ingrese tiempo estimated de destino: ");
        vueloNuevo.getArrival().setArrival_estimated(sc4.nextLine());
        System.out.println("Ingrese tiempo actual de destino: ");
        vueloNuevo.getArrival().setArrival_actual(sc4.nextLine());
        System.out.println("Ingrese pista estimada de destino: ");
        vueloNuevo.getArrival().setArrival_estimated_runway(sc4.nextLine());
        System.out.println("Ingrese pista actual de destino: ");
        vueloNuevo.getArrival().setArrival_actual_runway(sc4.nextLine());
    }
    public static void cargarDatosAirline(Scanner sc4, Vuelo vueloNuevo){
        System.out.println("Ingrese nombre de aerolinea: ");
        vueloNuevo.getAirline().setAirline_name(sc4.nextLine());
        System.out.println("Ingrese codigo iata de aerolinea: ");
        vueloNuevo.getAirline().setAirline_iata(sc4.nextLine().toUpperCase());
        System.out.println("Ingrese codigo icao de aerolinea: ");
        vueloNuevo.getAirline().setAirline_icao(sc4.nextLine().toUpperCase());
    }
    public static void cargarDatosFlight(Scanner sc4, Vuelo vueloNuevo){
        System.out.println("Ingrese numero de vuelo: ");
        vueloNuevo.getFlight().setFlight_number(sc4.nextLine());
        System.out.println("Ingrese codigo iata de vuelo: ");
        vueloNuevo.getFlight().setFlight_iata(sc4.nextLine().toUpperCase());
        System.out.println("Ingrese codigo icao de vuelo: ");
        vueloNuevo.getFlight().setFlight_icao(sc4.nextLine().toUpperCase());
        System.out.println("Ingrese codigo de compartido: ");
        vueloNuevo.getFlight().setFlight_codeshared(sc4.nextLine());
    }
    public static void cargarDatosAircraft(Scanner sc4, Vuelo vueloNuevo){
        System.out.println("Ingrese numero de registro de la aeronave: ");
        vueloNuevo.getAircraft().setAircraft_registration(sc4.nextLine());
        System.out.println("Ingrese codigo iata de la aeronave : ");
        vueloNuevo.getAircraft().setAircraft_iata(sc4.nextLine().toUpperCase());
        System.out.println("Ingrese codigo icao de la aeronave: ");
        vueloNuevo.getAircraft().setAircraft_icao(sc4.nextLine().toUpperCase());
        System.out.println("Ingrese codigo icao24 de la aeronave: ");
        vueloNuevo.getAircraft().setAircraft_icao24(sc4.nextLine());
    }

    public static void mostrarMensajeDeError(String mensaje) {
        System.out.println(ANSI_RED + mensaje + ANSI_RESET);
    }

    public static void mostrarMensajeConsulta(String mensaje){
        System.out.println(ANSI_CYAN + mensaje + ANSI_RESET);
    }

    public static void mostrarMensajeAccion(String mensaje){
        System.out.println(ANSI_YELLOW + mensaje + ANSI_RESET);
    }

    public static void llenarTanque(){
        if(mostrarVuelosCargados()) {
            mostrarMensajeConsulta("Seleccione el id del vuelo al que desea cargarle el tanque");
            Scanner sc = new Scanner(System.in);
            Integer i = sc.nextInt();
            RepoVuelosNuevo repo = RepoVuelosNuevo.getInstance();
            List<Vuelo> vuelos = repo.getVuelosNuevos();
            Vuelo vuelo = vuelos.get(i);
            vuelo.cargarCombustible();
        }
    }
}
