package controlleur;

import javax.servlet.http.HttpServletRequest;
import entite.*;
import java.util.Collection;

public class DisplayMediumsAction extends Action {

    @Override
    public void execute(HttpServletRequest request) throws Exception {
        
        if(request.getSession().getAttribute("client_id") == null) {
            throw new Exception("Vous n'êtes pas connecté.");
        }
        
        Client client = this.service.findClient((long)request.getSession().getAttribute("client_id"));
        
        Collection<Medium> mediums = this.service.displayMediums(client);
        
        request.setAttribute("mediums", mediums);
    }
}
