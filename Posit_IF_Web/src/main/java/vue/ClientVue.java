
package vue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;
import entite.Client;

public class ClientVue extends Vue {
    
    @Override
    public void render(HttpServletRequest request, HttpServletResponse response) {
        
        Client client = (Client)request.getAttribute("client");
        
        JsonObject container = new JsonObject();
        
        container.addProperty("surname", client.getSurname());
        container.addProperty("firstname", client.getFirstname());
        container.addProperty("gender", client.getGender());
        container.addProperty("birthdate", client.getBirthDate());
        container.addProperty("mail", client.getInformation().getMail());
        container.addProperty("phone", client.getInformation().getPhone());
        container.addProperty("chineseSign", client.getAstroProfile().getChineseSign());
        container.addProperty("color", client.getAstroProfile().getColor());
        container.addProperty("totemAnimal", client.getAstroProfile().getTotemAnimal());
        container.addProperty("zodiacSign", client.getAstroProfile().getZodiacSign());
        
        outputJson(response, container);
    }
}
