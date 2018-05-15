package controlleur;

import javax.servlet.http.HttpServletRequest;
import entite.*;

public class GetClientAction extends Action {

    @Override
    public void execute(HttpServletRequest request) throws Exception {
        
        if(request.getSession().getAttribute("client_id") == null) {
            throw new Exception("Vous n'êtes pas connecté.");
        }
        
        Client client = this.service.findClient((long)request.getSession().getAttribute("client_id"));
        
        request.setAttribute("client", client);
    }
}
