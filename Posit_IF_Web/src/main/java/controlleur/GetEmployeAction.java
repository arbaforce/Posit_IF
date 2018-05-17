package controlleur;

import javax.servlet.http.HttpServletRequest;
import entite.*;

public class GetEmployeAction extends Action {

    @Override
    public void execute(HttpServletRequest request) throws Exception {
        
        if(request.getSession().getAttribute("employe_id") == null) {
            throw new Exception("Vous n'êtes pas connecté.");
        }
        
        Employee employe = this.service.findEmployee((long)request.getSession().getAttribute("employe_id"));
        
        request.setAttribute("employe", employe);
    }
}
