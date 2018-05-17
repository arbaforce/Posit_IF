package controlleur;

import entite.*;
import javax.servlet.http.HttpServletRequest;

public class RealiserVoyanceAction extends Action {

    @Override
    public void execute(HttpServletRequest request) throws Exception {
        
        if(request.getSession().getAttribute("employe_id") == null) {
            throw new Exception("Vous n'êtes pas connecté.");
        }
        
        long idVoyance = Long.parseLong(request.getParameter("idVoyance"));
        
        Employee employe = this.service.findEmployee((long)request.getSession().getAttribute("employe_id"));
        
        boolean estVoyanceEmploye = false;
        for(Voyance voyance : employe.getVoyances()) {
            if(voyance.getIdVoyance() == idVoyance) {
                estVoyanceEmploye = true;
                break;
            }
        }
        
        if(!estVoyanceEmploye) {
            throw new Exception("Cette voyance n'est pas valide.");
        }
        
        request.getSession().setAttribute("voyance_id", idVoyance);
    }
}
