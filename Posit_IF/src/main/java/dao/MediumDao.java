package dao;

import entite.Employee;
import entite.Medium;
import java.util.Collection;
import java.util.List;
import javax.persistence.Query;


public class MediumDao {

    public MediumDao() {
    }
    
    public void create(Medium m){
        JpaUtil.obtenirEntityManager().persist(m);
    }
    
    public Medium update(Medium m){
        m=JpaUtil.obtenirEntityManager().merge(m);
        return  m;
    }
    
    //find a client with his key (id)
    public Medium find(long id){
        Medium m=JpaUtil.obtenirEntityManager().find(Medium.class, id);
        return m;
    }
    
     public Collection<Medium> findAll(){
        Query q=JpaUtil.obtenirEntityManager().createQuery("SELECT m FROM Medium m");
        return (Collection<Medium>)q.getResultList();
    }
    
     //add the employee in parameter to the list of employee possessed by the medium
    public void affectEmployee(Employee e, Medium m){
        m.addEmployee(e);
        JpaUtil.obtenirEntityManager().merge(m);
    }
    
}
