package controlleur;

import dao.JpaUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vue.*;
import service.*;

@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {
    
    public ActionServlet() {
        JpaUtil.init();
        
        this.service = new Service();
    }
    
    private final Service service;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        String todo = request.getParameter("action");
        Action action = null;
        Vue vue = null;
        
        if(todo != null) {
            switch(todo) {
                case "login_client":
                    action = new LoginClientAction();
                    vue = new DefaultVue();
                    break;
                case "logout_client":
                    action = new LogoutClientAction();
                    vue = new DefaultVue();
                    break;
                case "display_mediums":
                    action = new DisplayMediumsAction();
                    vue = new ListeMediumsVue();
                    break;
                case "ask_for_voyance":
                    action = new AskForVoyanceAction();
                    vue = new DefaultVue();
                    break;
                case "historique_client":
                    action = new HistoriqueClientAction();
                    vue = new HistoriqueClientVue();
                    break;
                case "sign_in":
                    action = new SignInAction();
                    vue = new DefaultVue();
                    break;
                case "get_client":
                    action = new GetClientAction();
                    vue = new ClientVue();
                    break;
                case "login_medium":
                    action = new LoginMediumAction();
                    vue = new DefaultVue();
                    break;
                case "logout_medium":
                    action = new LogoutMediumAction();
                    vue = new DefaultVue();
                    break;
                case "get_employe":
                    action = new GetEmployeAction();
                    vue = new EmployeVue();
                    break;
                case "get_affectations":
                    action = new GetAffectationsAction();
                    vue = new AffectationsVue();
                    break;
            }
        }
        
        if(action != null && vue != null) {
            
            action.setServiceMetier(this.service);
            
            try {
                action.execute(request);
            }
            catch(Exception e) {
                vue = new ErreurVue();
                request.setAttribute("erreur", e.getMessage());
            }
            finally {
                vue.render(request, response);
            }
            
        } else {
            vue = new ErreurVue();
            request.setAttribute("erreur", "L'action demandée n'existe pas.");
            vue.render(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
