
package vue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;

public class DefaultVue extends Vue {
    
    @Override
    public void render(HttpServletRequest request, HttpServletResponse response) {
        
        outputJson(response, new JsonObject());
    }
}
