package controlleur;

import entite.*;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Martin
 */
public class LoginMediumAction extends Action{
    
    @Override
    public void execute(HttpServletRequest request) throws Exception {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        Employee employee = service.connectEmployee(email, password);
        
        if(employee == null) {
            throw new Exception("Les identifiants sont incorrects.");
        } else {
            throw new Exception("OK.");
        }
    }
}
