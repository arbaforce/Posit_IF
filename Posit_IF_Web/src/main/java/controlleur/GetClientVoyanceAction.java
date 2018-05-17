package controlleur;

import entite.*;
import javax.servlet.http.HttpServletRequest;

public class GetClientVoyanceAction extends Action {

    @Override
    public void execute(HttpServletRequest request) throws Exception {
        
        if(request.getSession().getAttribute("voyance_id") == null
            || request.getSession().getAttribute("employe_id") == null) {
            throw new Exception("Vous n'êtes pas connecté.");
        }
        
        Employee employe = this.service.findEmployee((long)request.getSession().getAttribute("employe_id"));
        long idVoyance = (long)request.getSession().getAttribute("voyance_id");
        
        Voyance voyance = null;
        for(Voyance v : employe.getVoyances()) {
            if(v.getIdVoyance() == idVoyance) {
                voyance = v;
                break;
            }
        }
        
        if(voyance == null) {
            throw new Exception("Cette action n'est plus disponible.");
        }
        
        request.setAttribute("client", voyance.getClient());
    }
}
