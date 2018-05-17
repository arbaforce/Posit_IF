
package vue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;
import entite.Employee;
import entite.Medium;
import entite.Voyance;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

public class TableauDeBordVue extends Vue {
    
    @Override
    public void render(HttpServletRequest request, HttpServletResponse response) {
        
        HashMap<Long, Integer>  donneesVoyancesMediums =  (HashMap) request.getAttribute("donneesMedium");
        HashMap<Employee,Pair<Long,Float>> donneesVoyancesEmployes =  (HashMap) request.getAttribute("donneesEmploye");
        HashMap<Long, String> nomMediums = (HashMap) request.getAttribute("nomMediums");
        
        JsonObject histogrammeVoyancesMediums = new JsonObject();
        JsonObject histogrammeVoyancesEmployes = new JsonObject();
        JsonObject camembertVoyancesEmployes = new JsonObject();
        
        for (Map.Entry<Long,Integer> it : donneesVoyancesMediums.entrySet()) {
            histogrammeVoyancesMediums.addProperty(nomMediums.get(it.getKey()), it.getValue());
        }
        
        for (Map.Entry<Employee,Pair<Long,Float>> it : donneesVoyancesEmployes.entrySet()) {
            Employee employe = (Employee) it.getKey();
            
            String nom = employe.getFirstname()+" "+employe.getSurname();
            
            histogrammeVoyancesEmployes.addProperty(nom, it.getValue().getKey());
            
            camembertVoyancesEmployes.addProperty(nom, it.getValue().getValue());
        }
        
        
        JsonObject container = new JsonObject();
        
        container.add("histogrammeVoyancesMediums", histogrammeVoyancesMediums);
        container.add("histogrammeVoyancesEmployes", histogrammeVoyancesEmployes);
        container.add("camembertVoyancesEmployes", camembertVoyancesEmployes);
        
        outputJson(response, container);
    }
}
