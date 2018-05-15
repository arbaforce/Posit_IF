package controlleur;

import javax.servlet.http.HttpServletRequest;
import service.Service;

public abstract class Action {
    
    protected Service service;
    
    public abstract void execute(HttpServletRequest request) throws Exception;
    
    public void setServiceMetier(Service service) {
        this.service = service;
    }
}
