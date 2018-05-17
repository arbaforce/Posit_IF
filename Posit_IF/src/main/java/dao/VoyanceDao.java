package dao;

import entite.Client;
import entite.Medium;
import entite.Voyance;
import java.util.Collection;
import javax.persistence.Query;

public class VoyanceDao {

    public VoyanceDao() {
    }
    
    public void create(Voyance v){
        JpaUtil.obtenirEntityManager().persist(v);
    }
    
    public Voyance update(Voyance v){
        v=JpaUtil.obtenirEntityManager().merge(v);
        return  v;
    }
    
    public Voyance find(long id){
        Voyance v=JpaUtil.obtenirEntityManager().find(Voyance.class, id);
        return v;
    }
    
     public Collection<Voyance> findAll(){
        Query q=JpaUtil.obtenirEntityManager().createQuery("SELECT e FROM Employee e");
        return (Collection<Voyance>)q.getResultList();
    }
     
    public Collection<Voyance> findAll(Medium m){
        Query q=JpaUtil.obtenirEntityManager().createQuery("SELECT v FROM Voyance v WHERE v.medium=:m");
        q.setParameter("m", m);
        return (Collection<Voyance>)q.getResultList();
    }
    
    public Collection<Voyance> getHistoriqueClient(Client c){
        Query q=JpaUtil.obtenirEntityManager().createQuery("SELECT v FROM Voyance v WHERE v.client =:client ");
        q.setParameter("client", c);
        return (Collection<Voyance>)q.getResultList();
    }
}
