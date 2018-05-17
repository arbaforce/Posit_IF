package controlleur;

import javax.servlet.http.HttpServletRequest;
import entite.*;
import java.util.List;

public class HistoriqueClientAction extends Action {

    @Override
    public void execute(HttpServletRequest request) throws Exception {
        
        if(request.getSession().getAttribute("client_id") == null) {
            throw new Exception("Vous n'êtes pas connecté.");
        }
        
        Client client = this.service.findClient((long)request.getSession().getAttribute("client_id"));
        List<Voyance> historique = client.getHistorique();
        
        request.setAttribute("historique", historique);
    }
}
