
package entite;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;


@Entity
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
public abstract class Medium implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idMedium;
    
    private String name;
    
    private String biography;
    
    @ManyToMany
    private List<Employee> employees;

    public Medium() {
    }

    public Medium(String name, String biography) {
        this.name = name;
        this.biography = biography;
    }

    public String getName() {
        return name;
    }

    public Long getIdMedium() {
        return idMedium;
    }

    public String getBiography() {
        return biography;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMedium != null ? idMedium.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medium)) {
            return false;
        }
        Medium other = (Medium) object;
        if ((this.idMedium == null && other.idMedium != null) || (this.idMedium != null && !this.idMedium.equals(other.idMedium))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Medium{" + "idMedium=" + idMedium + ", name=" + name + ", biography=" + biography + ", employees=" + employees + '}';
    }
    
    public void addEmployee(Employee e){
        this.employees.add(e);
    }
    
    public void removeEmployee(Employee e){
        this.employees.remove(e);
    }
    
}
