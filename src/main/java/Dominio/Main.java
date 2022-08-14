package Dominio;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Integer option = -1;
        Scanner sc = new Scanner(System.in);

        while (option != 0) {

            System.out.println("Ingresa un numero segun la operacion a realizar \n " +
                    "0. Salir\n " +
                    "1. Registrarse \n " +
                    "2. Iniciar sesion \n ");

            option = sc.nextInt();

            switch (option) {
                case 0:
                    System.out.println("Saliendo...");
                    UserService.clearScreen();
                    break;
                case 1:
                    System.out.println("Registrando...");
                    Usuario user = UserService.registrarUser();
                    UserService.registrarUsuario(user);
                    break;

                case 2:
                    Scanner sc3 = new Scanner(System.in);
                    Integer option3 = -1;
                    UserService.mostrarMensajeAccion("Iniciando sesion...");
                    System.out.println("\nIngrese email: ");
                    String mail = sc3.nextLine();

                    System.out.println("\nIngrese contrasena: ");
                    String pass = sc3.nextLine();

                    Usuario loggedUser = UserService.buscarLoginEnDb(mail, pass);
                    if(loggedUser == null)
                        option3 = 0;
                    while (option3 != 0) {
                        if(loggedUser.getCategoria().getClass().getSimpleName().equals("PremiumAdapter")){
                            option3 = UserService.mostrarMenuPremium(loggedUser, sc);

                        } else if (loggedUser.getCategoria().getClass().getSimpleName().equals("Estandar")){
                            UserService.mostrarMenuEstandar(loggedUser, sc);
                            option3 = sc.nextInt();
                            UserService.mostrarOpciones(loggedUser, option3, false);
                        }
                        else{
                            UserService.mostrarMenuIntermedio(loggedUser, sc);
                            option3 = sc.nextInt();
                            UserService.mostrarOpciones(loggedUser, option3, false);
                        }

                    }
                    break;
                default:
                    UserService.mostrarMensajeDeError("Opcion invalida\n");
                    break;
            }
        }
    }

}