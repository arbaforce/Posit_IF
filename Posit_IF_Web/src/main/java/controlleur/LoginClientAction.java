package controlleur;

import javax.servlet.http.HttpServletRequest;
import entite.*;

public class LoginClientAction extends Action {

    @Override
    public void execute(HttpServletRequest request) throws Exception {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        Client client = service.connectClient(email, password);
        
        if(client == null) {
            throw new Exception("Les identifiants sont incorrects.");
        }
        
        request.getSession().setAttribute("client_id", client.getIdClient());
    }
}
