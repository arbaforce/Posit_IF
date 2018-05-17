
package entite;

import java.io.Serializable;
import javax.persistence.Entity;


@Entity
public class FortuneTeller extends Medium implements Serializable {

    private String media;

    public FortuneTeller() {
    }

    public FortuneTeller(String name, String biography, String media) {
        super(name, biography);
        this.media = media;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    @Override
    public String toString() {
        return super.toString() + " Talent : Voyant \n"+ " Support : " + this.media +"\n" ;
    }
    
}
