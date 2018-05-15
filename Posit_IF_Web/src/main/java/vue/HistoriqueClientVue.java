
package vue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;
import java.util.List;

public class HistoriqueClientVue extends Vue {
    
    @Override
    public void render(HttpServletRequest request, HttpServletResponse response) {
        
//        List<String> historique = (List<String>)request.getAttribute("historique");
//        
//        String liste = "Test";
//        
//        for(String s : historique) {
//            liste += s + "<br>";
//        }
        
        JsonObject container = new JsonObject();
        container.addProperty("historique", "Test");
        outputJson(response, container);
    }
}
