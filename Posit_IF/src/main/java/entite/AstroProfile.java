
package entite;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Embeddable;


@Embeddable
public class AstroProfile implements Serializable {

    private String zodiacSign;
    
    private String chineseSign;
    
    private String color;
    
    private String totemAnimal;

    public AstroProfile() {
    }

    public AstroProfile(List<String> astroProfile) {
        this.zodiacSign = astroProfile.get(0);
        this.chineseSign = astroProfile.get(1);
        this.color = astroProfile.get(2);
        this.totemAnimal = astroProfile.get(3);
    }

    public String getZodiacSign() {
        return zodiacSign;
    }

    public void setZodiacSign(String zodiacSign) {
        this.zodiacSign = zodiacSign;
    }

    public String getChineseSign() {
        return chineseSign;
    }

    public void setChineseSign(String chineseSign) {
        this.chineseSign = chineseSign;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTotemAnimal() {
        return totemAnimal;
    }

    public void setTotemAnimal(String totemAnimal) {
        this.totemAnimal = totemAnimal;
    }

    @Override
    public String toString() {
        return "AstroProfile{" + "zodiacSign=" + zodiacSign + ", chineseSign=" + chineseSign + ", color=" + color + ", totemAnimal=" + totemAnimal + '}';
    }
    
}
