package controlleur;

import javax.servlet.http.HttpServletRequest;
import entite.*;
import service.EmployeeIsNotFree;

public class AskForVoyanceAction extends Action {

    @Override
    public void execute(HttpServletRequest request) throws Exception {
        
        if(request.getSession().getAttribute("client_id") == null) {
            throw new Exception("Vous n'êtes pas connecté.");
        }
        
        Client client = this.service.findClient((long)request.getSession().getAttribute("client_id"));
        
        long idMedium = Long.parseLong(request.getParameter("idMedium"));
        
        Medium medium = this.service.findMedium(idMedium);
        
        if(medium == null) {
            throw new Exception("Ce medium n'existe pas.");
        }
        
        try {
            this.service.askForVoyance(client, medium);
        }
        catch(EmployeeIsNotFree e) {
            throw new Exception("Ce medium n'est pas disponible pour l'instant."
                + " Veuillez rééssayer plus tard.");
        }
    }
}
