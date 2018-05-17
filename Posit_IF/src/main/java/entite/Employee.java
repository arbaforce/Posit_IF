
package entite;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;


@Entity
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEmployee;
    
    private String gender;
    
    private String surname;
    
    private String firstname;
    
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    
    @Embedded
    private Information information;
    
    private boolean free;
    
    @OneToMany
    private List<Voyance> voyances;
    
    @Version
    private int version;

    public Employee() {
    }

    public Employee(String gender, String surname, String firstname, String birthDate, Information information) {
        try{
            this.gender = gender;
            this.surname = surname;
            this.firstname = firstname;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dn = sdf.parse(birthDate);
            this.birthDate = dn;
            this.information = information;
            this.free=true;
         } catch (ParseException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getBirthDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(this.birthDate);
        return date;
    }

    public void setBirthDate(String birthDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dn = sdf.parse(birthDate);
        this.birthDate = dn;
    }
    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public List<Voyance> getVoyances() {
        return voyances;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }
    
     public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Client)) {
            return false;
        }
        Employee other = (Employee) object;
        return !((this.idEmployee == null && other.idEmployee != null) || (this.idEmployee != null && !this.idEmployee.equals(other.idEmployee)));
    }
    
    @Override
    public String toString() {
        return "Employee{" + "idEmployee=" + idEmployee + ", gender=" + gender + ", surname=" + surname + ", firstname=" + firstname + ", birthDate=" + birthDate + ", information=" + information + ", free=" + free + '}';
    }
    
    public void addVoyance(Voyance voyance){
        this.voyances.add(voyance);
    }
    
    public int getNumberVoyance(){
        return voyances.size();
    }
    
}
