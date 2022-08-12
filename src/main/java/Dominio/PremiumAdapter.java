package Dominio;

import java.util.ArrayList;

public class PremiumAdapter extends Categoria{
    private Premium catePremium = new Premium();

    public PremiumAdapter() {
        super();
        this.cantMax = null;
        this.nombre = "PremiumAdapter";
    }

    @Override
    public void consultarVueloExistente(Usuario user) throws Exception {
        if (catePremium.verificarPagoAlDia(user)) {
            catePremium.consultarVuelos(user);
        }
        else{
            UserService.mostrarMensajeDeError("\nNo se ha efectuado el pago para acceder a esta funcionalidad\n");
            System.out.println();
        }
    }
}
