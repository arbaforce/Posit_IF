
package vue;

import com.google.gson.*;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Vue {
    
    public abstract void render(HttpServletRequest request, HttpServletResponse response);
    
    protected void outputJson(HttpServletResponse response, JsonObject container) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        
        try {
            PrintWriter out = response.getWriter();
            out.println(new GsonBuilder().setPrettyPrinting().create().toJson(container));
            out.close();
        }
        catch(Exception e) {
        }
    }
}
