
package entite;

import java.io.Serializable;
import javax.persistence.Entity;



@Entity
public class TarotReader extends Medium implements Serializable {
    
    private String cards;

    public TarotReader() {
    }
    
    public TarotReader(String name, String biography, String talent, String cards) {
        super(name, biography);
        this.cards = cards;
    }

    public String getCards() {
        return cards;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return super.toString() + " Talent : Tarologue \n"+ " Cartes : " + this.cards +"\n" ;
    }
    
    
}
