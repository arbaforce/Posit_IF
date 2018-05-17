
package dao;

import entite.Employee;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import javafx.util.Pair;
import javax.persistence.Query;

public class EmployeeDao { 
    
    public EmployeeDao()
    {}
    
    public void create(Employee e){
        JpaUtil.obtenirEntityManager().persist(e);
    }
    
    public Employee update(Employee e){
        JpaUtil.obtenirEntityManager().merge(e);
        long id=e.getIdEmployee();
        Employee emp = find(id);
        return  emp;
    }
    
    //find a client with his key (id)
    public Employee find(long id){
        Employee e=JpaUtil.obtenirEntityManager().find(Employee.class, id);
        return e;
    }
    
    //authentication
    public Employee findByMail(String email){
        Query q=JpaUtil.obtenirEntityManager().createQuery("SELECT e FROM Employee e WHERE e.information.mail = :email");
        q.setParameter("email", email);
        if(q.getResultList().isEmpty()){
            return null;
        }else{
            return (Employee) q.getResultList().get(0);
        }        
    }
    
     public Collection<Employee> findAll(){
        Query q=JpaUtil.obtenirEntityManager().createQuery("SELECT e FROM Employee e");
        return (Collection<Employee>)q.getResultList();
    }
    
     //used by the service VoyanceByEmployee 
    public HashMap<Employee,Pair<Long,Float>> statEmployee(List<Employee> emp){
        HashMap<Employee,Pair<Long,Float>> mapRes = new HashMap<>();
        long total = 0;
        Query q = JpaUtil.obtenirEntityManager().createQuery("select count(v) from Voyance v");
        total = (Long) q.getSingleResult();
        if(total != 0){
            for(Employee empe : emp){
                q = JpaUtil.obtenirEntityManager().createQuery("select v from Voyance v where v.employee = :emp");
                q.setParameter("emp", empe);
                long nbConsultation = (long)q.getResultList().size();
                float pourcentage = (float)(100*(nbConsultation))/total;
                Pair<Long,Float> paire = new Pair<>(nbConsultation,pourcentage);
                mapRes.putIfAbsent(empe, paire);
            }
        }
        return mapRes;
    }
     
}
