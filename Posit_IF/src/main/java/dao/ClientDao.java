package dao;

import entite.Client;
import java.util.Collection;
import javax.persistence.Query;


public class ClientDao { 
    
    public ClientDao()
    {}
    
    public void create(Client c){
        JpaUtil.obtenirEntityManager().persist(c);
    }
    
    //find a client with his key (id)
    public Client find(long id){
        Client c=JpaUtil.obtenirEntityManager().find(Client.class, id);
        return c;
    }
    
    //authentication
    public Client findByMail(String email){
        Query q=JpaUtil.obtenirEntityManager().createQuery("SELECT c FROM Client c WHERE c.information.mail = :email");
        q.setParameter("email", email);
        if(q.getResultList().isEmpty()){
            return null;
        }else{
            return (Client) q.getSingleResult();
        }        
    }
    
    public Client update(Client c){
        c=JpaUtil.obtenirEntityManager().merge(c);
        return  c;
    }
    
    public Collection<Client> findAll(){
        Query q=JpaUtil.obtenirEntityManager().createQuery("SELECT c FROM Client c");
        
        return (Collection<Client>)q.getResultList();
    }
}
