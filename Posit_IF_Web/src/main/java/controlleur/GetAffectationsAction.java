package controlleur;

import javax.servlet.http.HttpServletRequest;
import entite.*;
import java.util.ArrayList;
import java.util.List;

public class GetAffectationsAction extends Action {

    @Override
    public void execute(HttpServletRequest request) throws Exception {
        
        if(request.getSession().getAttribute("employe_id") == null) {
            throw new Exception("Vous n'êtes pas connecté.");
        }
        
        Employee employe = this.service.findEmployee((long)request.getSession().getAttribute("employe_id"));
        
        List<Voyance> affectations = new ArrayList<Voyance>();
        
        List<Voyance> voyances = employe.getVoyances();
        
        for(Voyance voyance : voyances) {
            if(voyance.getEndHour() == null && voyance.getComment() == null) {
                affectations.add(voyance);
            }
        }
        
        request.setAttribute("affectations", affectations);
    }
}
