package Database;

import java.util.Base64;

public class Encriptacion {

    public static String encriptacion(String pass){
        try{
            return Base64.getEncoder().encodeToString(pass.getBytes());
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static String desencriptacion(String passEncriptada){
        try{
            byte[] bytes = Base64.getDecoder().decode(passEncriptada);
            return new String(bytes);
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}