
package vue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;
import entite.Employee;

public class EmployeVue extends Vue {
    
    @Override
    public void render(HttpServletRequest request, HttpServletResponse response) {
        
        Employee employe = (Employee)request.getAttribute("employe");
        
        JsonObject container = new JsonObject();
        
        container.addProperty("surname", employe.getSurname());
        container.addProperty("firstname", employe.getFirstname());
        container.addProperty("gender", employe.getGender());
        container.addProperty("birthdate", employe.getBirthDate());
        container.addProperty("mail", employe.getInformation().getMail());
        container.addProperty("phone", employe.getInformation().getPhone());
        
        outputJson(response, container);
    }
}
