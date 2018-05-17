package controlleur;

import entite.*;
import javax.servlet.http.HttpServletRequest;

public class LoginMediumAction extends Action {
    
    @Override
    public void execute(HttpServletRequest request) throws Exception {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        Employee employee = service.connectEmployee(email, password);
        
        if(employee == null) {
            throw new Exception("Les identifiants sont incorrects.");
        }
        
        request.getSession().setAttribute("employe_id", employee.getIdEmployee());
    }
}
