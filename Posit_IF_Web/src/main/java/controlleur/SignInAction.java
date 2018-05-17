/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import javax.servlet.http.HttpServletRequest;
import entite.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Martin
 */
public class SignInAction extends Action {

    @Override
    public void execute(HttpServletRequest request) throws Exception {
        
        String gender = request.getParameter("gender");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String birthDate = request.getParameter("birthDate");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        //Pour changer le format de la date
        final String OLD_FORMAT = "yyyy-MM-dd";
        final String NEW_FORMAT = "dd/MM/yyyy";
        
        String newDateString;

        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        Date d = sdf.parse(birthDate);
        sdf.applyPattern(NEW_FORMAT);
        newDateString = sdf.format(d);
        //---------------------------
        
        Client client = new Client(gender,lastName,firstName,newDateString, new Information(email,address,phone,password));
        
        service.createClient(client);
    }
}
