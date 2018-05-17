
package vue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;
import entite.Voyance;
import java.util.List;

public class HistoriqueClientVoyanceVue extends Vue {
    
    @Override
    public void render(HttpServletRequest request, HttpServletResponse response) {
        
        List<Voyance> historique = (List<Voyance>)request.getAttribute("historique");
        
        JsonArray historiqueContainer = new JsonArray();
        
        for(Voyance v : historique) {
            
            if(v.getBeginDate()!=null && v.getBeginHour() != null
                && !v.getDuration().equals("00h00mn")) {
                
                JsonObject historiqueObject = new JsonObject();
                historiqueObject.addProperty("medium_name", v.getMedium().getName());
                historiqueObject.addProperty("employe_surname", v.getEmployee().getSurname());
                historiqueObject.addProperty("employe_firstname", v.getEmployee().getFirstname());
                historiqueObject.addProperty("duree", v.getDuration());
                historiqueObject.addProperty("date", v.getBeginDate());
                historiqueObject.addProperty("comment", v.getComment());
                historiqueContainer.add(historiqueObject);
            }
        }
        
        JsonObject container = new JsonObject();
        container.add("historique", historiqueContainer);
        outputJson(response, container);
    }
}
