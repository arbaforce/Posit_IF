package controlleur;

import javax.servlet.http.HttpServletRequest;

public class LogoutClientAction extends Action {

    @Override
    public void execute(HttpServletRequest request) throws Exception {
        
        request.getSession().removeAttribute("client_id");
    }
}
