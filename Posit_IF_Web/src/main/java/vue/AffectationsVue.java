
package vue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;
import entite.*;
import java.util.List;

public class AffectationsVue extends Vue {
    
    @Override
    public void render(HttpServletRequest request, HttpServletResponse response) {
        
        List<Voyance> affectations = (List<Voyance>)request.getAttribute("affectations");
        
        JsonArray affectationsContainer = new JsonArray();
        
        for(Voyance affectation : affectations) {
            JsonObject affectationObject = new JsonObject();
            
            affectationObject.addProperty("id", affectation.getIdVoyance());
            affectationObject.addProperty("firstname", affectation.getClient().getFirstname());
            affectationObject.addProperty("surname", affectation.getClient().getSurname());
            affectationObject.addProperty("medium", affectation.getMedium().getName());
            
            affectationsContainer.add(affectationObject);
        }
        
        JsonObject container = new JsonObject();
        container.add("affectations", affectationsContainer);
        outputJson(response, container);
    }
}
