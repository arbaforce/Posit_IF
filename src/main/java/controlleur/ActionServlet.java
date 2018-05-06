package controlleur;

import dao.JpaUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vue.*;
import service.*;

/**
 *
 * @author mgreco
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        JpaUtil.init();
        
        HttpSession session = request.getSession(true);
        request.setCharacterEncoding("UTF-8");
        
        String todo = request.getParameter("action");
        Action action = null;
        Vue vue = null;
        
        if(todo != null) {
            switch(todo) {
                case "login":
                    action = new LoginAction();
                    vue = new LoginVue();
                    break;
                    
                case "login_medium":
                    action = new LoginMediumAction();
                    vue = new LoginVue();
                    break;
            }
        }
        
        if(action != null && vue != null) {
            
            Service service = new Service();
            action.setServiceMetier(service);
            
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
        
        JpaUtil.destroy();
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
