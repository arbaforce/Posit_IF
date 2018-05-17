
package entite;

import java.io.Serializable;
import javax.persistence.Entity;


@Entity
public class Astrologer extends Medium implements Serializable {

    private String school;
    
    private String promotion;

    public Astrologer() {
    }

    public Astrologer(String name, String biography, String school, String promotion) {
        super(name, biography);
        this.school = school;
        this.promotion = promotion;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        return super.toString() + " Talent : Astrologue \n"+ " Ecole : " + this.school +"\n Promotion : "+this.promotion+" \n";
    }
    
}
