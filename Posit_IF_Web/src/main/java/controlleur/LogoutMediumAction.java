package controlleur;

import javax.servlet.http.HttpServletRequest;

public class LogoutMediumAction extends Action {

    @Override
    public void execute(HttpServletRequest request) throws Exception {
        
        request.getSession().removeAttribute("employe_id");
    }
}
