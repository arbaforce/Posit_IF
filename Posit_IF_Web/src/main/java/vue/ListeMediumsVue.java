
package vue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;
import entite.*;
import java.util.Collection;

public class ListeMediumsVue extends Vue {
    
    @Override
    public void render(HttpServletRequest request, HttpServletResponse response) {
        
        Collection<Medium> mediums = (Collection<Medium>)request.getAttribute("mediums");
        
        JsonArray mediumsContainer = new JsonArray();
        
        for(Medium medium : mediums) {
            JsonObject mediumObject = new JsonObject();
            
            mediumObject.addProperty("id", medium.getIdMedium());
            mediumObject.addProperty("name", medium.getName());
            mediumObject.addProperty("biography", medium.getBiography());
            
            if(Astrologer.class.isInstance(medium)) {
                Astrologer astrologer = (Astrologer)medium;
                mediumObject.addProperty("promotion", astrologer.getPromotion());
                mediumObject.addProperty("school", astrologer.getSchool());
                mediumObject.addProperty("talent", "Astrologiste");
            } else if(TarotReader.class.isInstance(medium)) {
                TarotReader tarotReader = (TarotReader)medium;
                mediumObject.addProperty("cartes", tarotReader.getCards());
                mediumObject.addProperty("talent", "Tarologue");
            } else if(FortuneTeller.class.isInstance(medium)){
                FortuneTeller fortuneTeller = (FortuneTeller)medium;
                mediumObject.addProperty("media", fortuneTeller.getMedia());
                mediumObject.addProperty("talent", "Voyant");
            }
            
            mediumsContainer.add(mediumObject);
        }
        
        JsonObject container = new JsonObject();
        
        container.add("mediums", mediumsContainer);
        
        outputJson(response, container);
    }
}
