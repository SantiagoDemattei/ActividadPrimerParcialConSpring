package Api;

import org.apache.cxf.jaxrs.client.WebClient;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.core.Response;
import com.fasterxml.jackson.databind.JsonNode;
import Dominio.Clima;
import java.util.*;

public class ApiCallClima {
    String TOKEN = "25db60cfe504151dd2df40a6e21061e6";
    String PARAMETRO = "";

    public void setParametro(String city){
        this.PARAMETRO = city;
    }

    public Float consultarClima() throws Exception {

        WebClient client = WebClient.create("https://api.openweathermap.org/data/2.5/weather?q=" + PARAMETRO + "&appid=" + TOKEN);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Response response = client
                .header("Content-Type", "application/json")
                .get();

        String responseBody = response.readEntity(String.class); // json entero
        JsonNode jsonNode = mapper.readTree(responseBody); // lo convertimos a jsonNode
        //System.out.println("response = " + responseBody);
        Clima clima = new Clima(jsonNode); // deserializamos el jsonNode y creamos un objeto clima
        Float temperatura;
        jsonNode = mapper.readTree(clima.getMain());
        temperatura = clima.getTemp(jsonNode);
        return clima.conversorKelvinCelsius(temperatura);
    }
}
