
package entite;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;


@Entity
public class Voyance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idVoyance;
    
    @Temporal(TemporalType.DATE)
    private Date beginDate;
    
    @Temporal(TemporalType.TIME)
    private Date beginHour;
    
    @Temporal(TemporalType.TIME)
    private Date endHour;
    
    private String comment;
    
    @ManyToOne //bidirectional
    private Employee employee;
    
    @ManyToOne
    private Medium medium;
    
    @ManyToOne
    private Client client;
    
    @Version
    private int version;
    
    public Voyance() {
    }

    public Voyance(Employee employee, Medium medium, Client client) {
        this.employee = employee;
        this.medium = medium;
        this.client = client;
    }

    public String getBeginDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = null;
        if(beginDate!=null)
            date = sdf.format(this.beginDate);
        return date;
    }

    public String getBeginHour() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        String date = null;
        if(beginHour!=null)
            date = sdf.format(this.beginHour);
        return date;
    }

    public String getEndHour() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        String date = null;
        if(endHour!=null)
            date = sdf.format(this.endHour);
        return date;
    }
    
    public String getDuration(){
        long oneHour=1000*60*60;
        long oneMinut=1000*60;
        String result;
        if(beginHour!=null && endHour !=null){
            long duration=Math.abs(beginHour.getTime()-endHour.getTime());
            result= Math.floor(duration/oneHour) +"h"+Math.floor(duration/oneMinut)+"mn";
        }else{
            result="00h00mn";
        }
        return result;
    }

    public int getVersion() {
        return version;
    }

    public Employee getEmployee() {
        return employee;
    }
    
    public Long getIdVoyance() {
        return idVoyance;
    }


    public String getComment() {
        return comment;
    }

    public Medium getMedium() {
        return medium;
    }

    public Client getClient() {
        return client;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setBegin() {
        try {
            SimpleDateFormat d = new SimpleDateFormat ("dd/MM/yyyy" );
            SimpleDateFormat h = new SimpleDateFormat ("hh:mm");
            Date currentTime_1 = new Date();
            String dateString = d.format(currentTime_1);
            String heureString = h.format(currentTime_1);
            Date dn = d.parse(dateString);
            Date hn = h.parse(heureString);
            this.beginDate = dn;
            this.beginHour = hn;
        } catch (ParseException ex) {
            Logger.getLogger(Voyance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setEnd() {
         try {
            SimpleDateFormat h = new SimpleDateFormat ("hh:mm");
            Date currentTime_1 = new Date();
            String heureString = h.format(currentTime_1);
            Date hn = h.parse(heureString);
            this.endHour = hn;
        } catch (ParseException ex) {
            Logger.getLogger(Voyance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVoyance != null ? idVoyance.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Voyance)) {
            return false;
        }
        Voyance other = (Voyance) object;
        if ((this.idVoyance == null && other.idVoyance != null) || (this.idVoyance != null && !this.idVoyance.equals(other.idVoyance))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Voyance{" + "beginDate=" + beginDate + ", beginHour=" + beginHour + ", endHour=" + endHour + ", comment=" + comment + ", employee=" + employee + ", client=" + client + ", version=" + version + '}';
    }
    
    
    
}
