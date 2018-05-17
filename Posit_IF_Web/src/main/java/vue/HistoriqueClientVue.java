
package vue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;
import entite.Medium;
import entite.Voyance;
import java.util.List;

public class HistoriqueClientVue extends Vue {
    
    @Override
    public void render(HttpServletRequest request, HttpServletResponse response) {
        
        List<Voyance> historique = (List<Voyance>)request.getAttribute("historique");
        
        
        JsonArray historiqueContainer = new JsonArray();
        
        for(Voyance v : historique) {
        
            JsonObject historiqueObject = new JsonObject();
            historiqueObject.addProperty("medium_name", v.getMedium().getName());
            
            if(v.getBeginDate()!=null){
                historiqueObject.addProperty("date", v.getBeginDate());
            }
            
            if(v.getBeginHour()==null && v.getDuration()=="00h00mn") {
                historiqueObject.addProperty("duree", "n'a pas encore eu lieu");
            } else if(v.getDuration()=="00h00mn") {
                historiqueObject.addProperty("duree", "en cours");
            } else{
                historiqueObject.addProperty("duree", v.getDuration());
            }
            
            historiqueContainer.add(historiqueObject);
        }
        
        JsonObject container = new JsonObject();
        
        container.add("historique", historiqueContainer);
        
        outputJson(response, container);
    }
}
