package controlleur;

import javax.servlet.http.HttpServletRequest;

public class CloseVoyanceAction extends Action {

    @Override
    public void execute(HttpServletRequest request) throws Exception {
        
        if(request.getSession().getAttribute("voyance_id") == null
            || request.getSession().getAttribute("employe_id") == null) {
            throw new Exception("Vous n'êtes pas connecté.");
        }
        
        long idVoyance = (long)request.getSession().getAttribute("voyance_id");
        
        String commentaire = request.getParameter("commentaire");
        
        if(commentaire == null) {
            throw new Exception("Le commentaire est manquant.");
        }
        
        this.service.closeVoyance(idVoyance, commentaire);
    }
}
