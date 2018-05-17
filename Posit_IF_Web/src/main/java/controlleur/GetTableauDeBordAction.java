package controlleur;

import javax.servlet.http.HttpServletRequest;
import entite.*;
import java.util.*;
import javafx.util.Pair;

public class GetTableauDeBordAction extends Action {

    @Override
    public void execute(HttpServletRequest request) throws Exception {
        
        if(request.getSession().getAttribute("employe_id") == null) {
            throw new Exception("Vous n'êtes pas connecté.");
        }
        
        HashMap<Long, Integer>  donneesVoyancesMediums =  this.service.voyanceByMedium();
        HashMap<Employee,Pair<Long,Float>> donneesVoyancesEmployes =  this.service.voyanceByEmployee();
        
        HashMap<Long, String> nomMediums = new HashMap<Long,String>();
        
        
        for (Map.Entry<Long,Integer> it : donneesVoyancesMediums.entrySet()) {
            long idMedium = it.getKey();
            String nom = this.service.findMedium(idMedium).getName();
            nomMediums.put(idMedium, nom);
        }
        
        request.setAttribute("donneesMedium", donneesVoyancesMediums);
        request.setAttribute("nomMediums", nomMediums);
        request.setAttribute("donneesEmploye", donneesVoyancesEmployes);
    }
}
