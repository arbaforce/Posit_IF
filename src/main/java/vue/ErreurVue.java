
package vue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;

public class ErreurVue extends Vue {
    
    public void render(HttpServletRequest request, HttpServletResponse response) {
        
        JsonObject container = new JsonObject();
        
        container.addProperty("erreur", (String)request.getAttribute("erreur"));
        
        outputJson(response, container);
    }
}
