
package vue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;
import java.util.List;

public class PredictionsVue extends Vue {
    
    @Override
    public void render(HttpServletRequest request, HttpServletResponse response) {
        
        List<String> predictions = (List<String>)request.getAttribute("predictions");
        
        String s = "";
        
        if(predictions != null) {
            for(String prediction : predictions) {
                s += prediction + "<br><br>";
            }
        }
        
        JsonObject container = new JsonObject();
        container.addProperty("predictions", s);
        outputJson(response, container);
    }
}
