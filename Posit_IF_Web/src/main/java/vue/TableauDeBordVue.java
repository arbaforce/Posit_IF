
package vue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.String;
import entite.Employee;
import entite.Medium;
import entite.Voyance;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
        
        Iterator it = donneesVoyancesMediums.entrySet().iterator();
        while (it.hasNext()){
            long idMedium = (long) it.next();
            
            histogrammeVoyancesMediums.addProperty(nomMediums.get(idMedium), donneesVoyancesMediums.get(idMedium));
        }
        
        it = donneesVoyancesEmployes.entrySet().iterator();
        while (it.hasNext()){
            Employee employe = (Employee) it.next();
            
            histogrammeVoyancesEmployes.addProperty(employe.getSurname(), donneesVoyancesEmployes.get(employe).getKey());
            
            camembertVoyancesEmployes.addProperty(employe.getSurname(), donneesVoyancesEmployes.get(employe).getValue());
        }
        
        JsonObject container = new JsonObject();
        
        container.add("histogrammeVoyancesMediums", histogrammeVoyancesMediums);
        container.add("histogrammeVoyancesEmployes", histogrammeVoyancesEmployes);
        container.add("camembertVoyancesEmployes", camembertVoyancesEmployes);
        
        outputJson(response, container);
    }
}
