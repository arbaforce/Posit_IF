
package service;

import entite.Client;
import dao.ClientDao;
import dao.EmployeeDao;
import dao.JpaUtil;
import dao.MediumDao;
import dao.VoyanceDao;
import entite.AstroProfile;
import entite.Employee;
import entite.Medium;
import entite.Voyance;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;
import javax.persistence.RollbackException;
import util.AstroTest;

public class Service {
    
    // utile de penser la classe service comme un objet car on pourrait avoir 
    //des attributs à l'avenir (ex : langue utilisateur)
    public Service(){}
    
   /*-------------------------------------------------------------------------*/ 
   //********************** SERVICES CLIENTS *********************************//
   /*-------------------------------------------------------------------------*/
    
    //Service pour enregistrer un nouveau client dans l'application
    public void createClient(Client c)
    {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        ClientDao cd= new ClientDao();
        try{
            cd.create(c);
            JpaUtil.validerTransaction();
            sendMailSuccess(c);
        }catch(RollbackException e){
            JpaUtil.annulerTransaction();
            sendMailFail(c);
        }finally{ 
            JpaUtil.fermerEntityManager();
        }    
    }
    
    //Service pour qu'un client puisse se connecter dans l'application
    public Client connectClient(String mail, String password){
        JpaUtil.creerEntityManager();
        ClientDao cd= new ClientDao();
        Client c=cd.findByMail(mail);
        if(c!=null){
            if(!c.getInformation().getPassword().equals(password)){
                c=null;
            }
        }
        JpaUtil.fermerEntityManager();
        return c;
    }
    
    //Service pour que le client puisse consulter la liste des Médiums
    public Collection<Medium> displayMediums (Client c){
        JpaUtil.creerEntityManager();
        MediumDao md = new MediumDao();
        Collection<Medium> m = md.findAll();
        JpaUtil.fermerEntityManager();
        return m;
    }
    
    //Service pour le client puisse faire une demande de voyance avec le médium
    //qu'il a choisi (Gestion de la concurrence, on laisse la possibilité de 
    //recommencer la transaction 3 fois)
    public Voyance askForVoyance(Client c, Medium m) throws EmployeeIsNotFree{
        
        JpaUtil.creerEntityManager();
        if (c==null || m==null){
            return null;
        }
        
        boolean repeatTransaction= true;
        int nbRepeat=0;
        Voyance v= null;
        
        List<Employee> emps=m.getEmployees();
        
        Employee e=null;
        Integer size=Integer.MAX_VALUE;
        
        for(Employee emp : emps){
           
            int sTemp=emp.getNumberVoyance();
            if(sTemp<size && emp.isFree()){
                e=emp;
                size=sTemp;
            }
        }
        if(e==null){ throw new EmployeeIsNotFree(); }
        
        VoyanceDao vdao=new VoyanceDao();
        EmployeeDao ed= new EmployeeDao();
        ClientDao cd= new ClientDao();
        while ((repeatTransaction)&&(nbRepeat<3)){
            try{
                repeatTransaction= false;
                JpaUtil.ouvrirTransaction();
                v=new Voyance(e,m,c);
                vdao.create(v);
                e.addVoyance(v);
                c.addVoyance(v);
                ed.update(e);
                cd.update(c);
                sendNotification(c, m);
                JpaUtil.validerTransaction();
            }catch(RollbackException ex){
                JpaUtil.annulerTransaction();
            }
        }
        JpaUtil.fermerEntityManager();  
        return v;
    }

    
    //Service pour le client pour qu'il puisse consulter l'historique des demandes
    //de voyances qu'il a fait
    public List<String> historyForClient (Client c){
        c.getHistorique().isEmpty();
        List <Voyance> voyancesClient= c.getHistorique();
        List <String> voyances = new ArrayList<String>();
        for (Voyance v: voyancesClient){
            String s= v.getMedium()+", le "+v.getBeginDate()+", durée: "+v.getDuration();
            voyances.add(s);
        }
        return voyances;
    }
        
   /*-------------------------------------------------------------------------*/ 
   //********************* SERVICES EMPLOYES *********************************//
   /*-------------------------------------------------------------------------*/
   
    
     //Service pour creation (en dur seulement) d'un employé
    public void createEmployee(Employee emp)
    {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        EmployeeDao cd= new EmployeeDao();
        try{
            cd.create(emp);
            JpaUtil.validerTransaction();
        }catch(RollbackException e){
            JpaUtil.annulerTransaction();
        }finally{ 
            JpaUtil.fermerEntityManager();
        }  
    }
    
    //Service pour trouver (en dur seulement) un employé à partir de son id
    public Employee findEmployee (long id){
        JpaUtil.creerEntityManager();
        EmployeeDao cd= new EmployeeDao();
        Employee emp=cd.find(id);
        JpaUtil.fermerEntityManager();
        return emp;
    }
    
    //Service pour qu'un client puisse se connecter dans l'application
    public Employee connectEmployee(String mail, String password){
        JpaUtil.creerEntityManager();
        EmployeeDao ed= new EmployeeDao();
        Employee e=ed.findByMail(mail);
        if(e!=null){
            if(!e.getInformation().getPassword().equals(password)){
                e=null;
            }
        }
        JpaUtil.fermerEntityManager();
        return e;
    }
    
    //Service utilisé pour avoir accès aux informations concernant un client
    public Client findClient (long id){
        JpaUtil.creerEntityManager();
        ClientDao cd= new ClientDao();
        Client c=cd.find(id);
        JpaUtil.fermerEntityManager();
        return c;
    }
    
    //Service pour obtenir automatiquement des prédictions pour l'employe
    public List<String> getPredictions(Client c) throws IOException{
        AstroTest at=new AstroTest("ASTRO-01-M0lGLURBU0ktQVNUUk8tQjAx");
        int amour= (int) (5*Math.random());
        int sante= (int) (5*Math.random());
        int travail= (int) (5*Math.random());
        return at.getPredictions(c.getAstroProfile().getColor(),c.getAstroProfile().getColor(),amour, sante, travail);
    }
    
    //Service pour pouvoir débuter une voyance
    public Voyance beginVoyance(long idVoy){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        VoyanceDao vdao= new VoyanceDao();
        Voyance v=vdao.find(idVoy);
        if(v==null){ return null;}
        
        EmployeeDao edao= new EmployeeDao();
        Employee emp=v.getEmployee();
        try{
            emp.setFree(false);
            edao.update(emp);
            v.setBegin();
            vdao.update(v);
            JpaUtil.validerTransaction();
        }catch(RollbackException e){
            JpaUtil.annulerTransaction();
        }finally{ 
            JpaUtil.fermerEntityManager();
        } 
        return v; 
    }
    
     //Service pour pouvoir finir une voyance
    public Voyance endVoyance(long idVoy){
        
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        VoyanceDao vdao= new VoyanceDao();
        Voyance v=vdao.find(idVoy);
        try{
            v.setEnd();
            vdao.update(v);
            JpaUtil.validerTransaction();
        }catch(RollbackException e){
            JpaUtil.annulerTransaction();
        }finally{ 
            JpaUtil.fermerEntityManager();
        }
        return v;
    }
        
    //Service pour cloturer une voyance
    public void closeVoyance(long idVoy, String comments){
        
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        VoyanceDao vdao= new VoyanceDao();
        Voyance v=vdao.find(idVoy);
        EmployeeDao edao= new EmployeeDao();
        Employee emp= v.getEmployee();
        try{
            emp.setFree(true);
            edao.update(emp);
            v.setComment(comments);
            vdao.update(v);
            JpaUtil.validerTransaction();
        }catch(RollbackException e){
            JpaUtil.annulerTransaction();
        }finally{ 
            JpaUtil.fermerEntityManager();
        } 
    }

    
    //Service pour que l'employe puisse accéder au profil Astrologique d'un client
    public AstroProfile displayAstroprofile (Client c){
        return c.getAstroProfile();
    }
    
    //Service pour l'employé puisse consulter l'historique des voyances que le 
    //client a effectué
    public List<Voyance> getHistoryOfClient (Client c){
        JpaUtil.creerEntityManager();
        VoyanceDao vdao= new VoyanceDao();
        List<Voyance> lv= (List<Voyance>) vdao.getHistoriqueClient(c);
        JpaUtil.fermerEntityManager();
        return lv;
    }
    
    public HashMap<Long, Integer> voyanceByMedium(){
        HashMap<Long,Integer> hmap;
        hmap = new HashMap();
       
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        MediumDao md= new MediumDao();
        VoyanceDao vd=new VoyanceDao();
        try{
            Collection<Medium> lm=md.findAll();
            for(Medium m:lm){
                hmap.put(m.getIdMedium(), vd.findAll(m).size());
            }
            JpaUtil.validerTransaction();
        }catch(Exception e){
            JpaUtil.annulerTransaction();
        }finally{
            JpaUtil.fermerEntityManager();
        }
        return hmap;
    }
    
    public HashMap<Employee,Pair<Long,Float>> voyanceByEmployee(){
        JpaUtil.creerEntityManager();
        EmployeeDao eDAO = new EmployeeDao();
        List<Employee> emp = (List<Employee>) eDAO.findAll();
        HashMap<Employee,Pair<Long,Float>> res = new HashMap<Employee,Pair<Long,Float>>();
        try{
        res = eDAO.statEmployee(emp);
        } catch(Exception e){
            System.out.println(e);
        }
        JpaUtil.fermerEntityManager();
        return res;
    }
    
   /*-------------------------------------------------------------------------*/ 
   //*********************** SERVICES MEDIUM *********************************//
   /*-------------------------------------------------------------------------*/
   
    //Service pour creation (en dur seulement) d'un médium
    public void createMedium(Medium m)
    {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        MediumDao md= new MediumDao();
        try{
            md.create(m);
            JpaUtil.validerTransaction();
        }catch(Exception e){
            JpaUtil.annulerTransaction();
        }finally{ 
            JpaUtil.fermerEntityManager();
        }  
    }
    
    //Service pour trouver (en dur seulement) un médium à partir de son id
    public Medium findMedium (long id){
        JpaUtil.creerEntityManager();
        MediumDao cd= new MediumDao();
        Medium m=cd.find(id);
        JpaUtil.fermerEntityManager();
        return m;
    }
    
    //Service qui permet de définir explicitement quels sont les médiums qu'un
    //employé peut incarner
    public void affectEmployee(Employee emp, Medium m)
    {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        MediumDao md= new MediumDao();
        try{
            md.affectEmployee(emp,m);
            JpaUtil.validerTransaction();
        }catch(Exception e){
            JpaUtil.annulerTransaction();
        }finally{ 
            JpaUtil.fermerEntityManager();
        }  
    }

    //**************** SERVICES COMPLEMENTAIRES ****************
    
    private void sendMailFail(Client c){
        System.out.println("Expediteur: contact@posit.if");
        System.out.println("Pour: "+c.getInformation().getMail());
        System.out.println("Sujet: Bienvenue chez POSIT'IF");
        System.out.println("Corps:");
        System.out.println("Bonjour "+c.getFirstname()+",");
        System.out.println("Votre inscription au service POSIT'IF a malencontreusement échoué... Merci de recommencer ultérieurement.");        
    }
    
    private void sendMailSuccess(Client c){
        System.out.println("Expediteur: contact@posit.if");
        System.out.println("Pour: "+c.getInformation().getMail());
        System.out.println("Sujet: Bienvenue chez POSIT'IF");
        System.out.println("Corps:");
        System.out.println("Bonjour "+c.getFirstname()+",");
        System.out.println("Nous vous confirmons votre inscription au service POSIT'IF. Votre numéro de client est : "+c.getIdClient());        
    }
    
    private void sendNotification(Client c, Medium m){
        System.out.println("Voyance demandée pour client "+c.getFirstname()+" "+c.getSurname()+" (#"+c.getIdClient()+"), Médium : "+m.getName());
    }
    
     
}
