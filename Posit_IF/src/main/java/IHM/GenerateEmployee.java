
package IHM;

import dao.JpaUtil;
import entite.Astrologer;
import entite.Client;
import entite.Employee;
import entite.FortuneTeller;
import entite.Information;
import entite.TarotReader;
import static java.awt.SystemColor.info;
import java.util.List;
import service.Service;

/**
 *
 * @author erouille
 */
public class GenerateEmployee {
    public static void main(String[] args) {
        JpaUtil.init();
        
        Information info1=new Information("anakin.skywalker@gmail.com","espace","0600000000","sith");
        Employee e1 = new Employee("M", "Skywalker", "Anakin","01/01/1900", info1);
        
        Information info2=new Information("luke.skywalker@gmail.com","espace","0664170706","jedi");
        Employee e2 = new Employee("M", "Skywalker", "Luke","05/01/1950", info2);
       
        Information info3=new Information("padme.amidala@gmail.com","naboo","0664170706","princesse");
        Employee e3 = new Employee("F", "Amidala", "Padmé","05/01/1900", info3);
        
        Information info4=new Information("bb8@gmail.com","vaisseau spatial","0664170706","force");
        Employee e4 = new Employee("A", " ", "BB-8","05/01/2000", info4);
        
        Information info5=new Information("poe.dameron@gmail.com","vaisseau spatial","0664170708","resistance");
        Employee e5 = new Employee("M", "Dameron", "Poe","05/01/1980", info5);
        
        Information info6=new Information("chewbacca@gmail.com","kashyyyk"," ","graouh");
        Employee e6= new Employee("A", " ", "Chewbacca","05/01/1880", info6);
        
        Information info7=new Information("bogossDeLespace@gmail.com","tatooine","0664170706","jesuisbo");
        Employee e7 = new Employee("M", "Le Hutt", "Jabba","05/01/2008", info7);
        
        Information info8=new Information("obi-wan.kenobi@gmail.com","ran","0664170706","laforce");
        Employee e8 = new Employee("M", "Kenobi", "Obi-wan","05/01/1900", info8);
        
        Information info9=new Information("dark.maul@gmail.com","etoile noire","0664170706","badguy");
        Employee e9 = new Employee("M", "Maul", "Dark","05/01/1960", info9);
        
        Information info10=new Information("R2-D2@gmail.com","espace","0664170706","droide");
        Employee e10 = new Employee("A", " ", "R2-D2","05/01/2008", info10);
        
        Information info11=new Information("Palpatine@gmail.com","Empire Galactique","0664170706","chocolatine");
        Employee e11 = new Employee("M", "Palpatine", "Sheev","05/01/1882", info11);
        
        Information info12=new Information("JarJar@gmail.com","naboo","0664170706","gungan");
        Employee e12 = new Employee("A", "Binks", "Jarjar","05/01/1882", info12);
        
        
        Service s=new Service();
        s.createEmployee(e1);
        s.createEmployee(e2);
        s.createEmployee(e3);
        s.createEmployee(e4);
        s.createEmployee(e5);
        s.createEmployee(e6);
        s.createEmployee(e7);
        s.createEmployee(e8);
        s.createEmployee(e9);
        s.createEmployee(e10);
        s.createEmployee(e11);
        s.createEmployee(e12);
        
        FortuneTeller m1 = new FortuneTeller("Sibylle Trelawney", "Le sinistros,....le sinistros", "Marc de café");
        FortuneTeller m2 = new FortuneTeller("Professor Charles Xavier", "La mutation, c'est la clé de notre évolution. C'est elle qui nous a mené de l'état de simple cellule à l'espèce dominante sur notre planète. Mais tous les deux ou trois cents milles ans, l'évolution fait un bon en avant...", "ton futur");
        Astrologer m3 = new Astrologer("Lyn Cassady", "The Men Who Stare at Goats", "New World Army", "1945");
        TarotReader m4 = new TarotReader("Homer Simpson","C'est pas parce que je m'en fous que je comprends pas", "Il peut marcher au plafond", "Poker");
        Astrologer m5 = new Astrologer("Le magicien d'Oz", "The Men Who Stare at Goats", "New World Army", "1945");
        TarotReader m6 = new TarotReader("Insaisissables Squad","Voir c'est croire; mais ce que l'on voit est-il réel? Ce n'est qu'une question de point de vue.", "Cambriolages","Illusions");
        FortuneTeller m7= new FortuneTeller("Yoda","Difficile à voir. Toujours en mouvement est l'avenir.","La force");
        
        s.createMedium(m1);
        s.createMedium(m2);
        s.createMedium(m3);
        s.createMedium(m4);
        s.createMedium(m5);
        s.createMedium(m6);
        s.createMedium(m7);
        
        s.affectEmployee(e1, m1);
        s.affectEmployee(e1, m2);
        s.affectEmployee(e1, m3);
        s.affectEmployee(e1, m4);
        
        s.affectEmployee(e2, m4);
        s.affectEmployee(e2, m5);
        s.affectEmployee(e2, m6);
        s.affectEmployee(e2, m7);
        
        s.affectEmployee(e3, m2);
        s.affectEmployee(e3, m4);
        s.affectEmployee(e3, m6);
        
        s.affectEmployee(e4, m6);
        s.affectEmployee(e4, m5);
        s.affectEmployee(e4, m3);
        
        s.affectEmployee(e5, m1);
        s.affectEmployee(e5, m3);
        s.affectEmployee(e5, m5);
        s.affectEmployee(e5, m7);
        
        s.affectEmployee(e6, m2);
        s.affectEmployee(e6, m7);
        
        s.affectEmployee(e7, m1);
        
        s.affectEmployee(e8, m5);
        s.affectEmployee(e8, m6);
        s.affectEmployee(e8, m3);
        s.affectEmployee(e8, m4);
        
        s.affectEmployee(e10, m2);
        s.affectEmployee(e10, m7);
        
        s.affectEmployee(e11, m7);
        
        s.affectEmployee(e12, m1); 
        
        List<Employee> list=s.findMedium(m1.getIdMedium()).getEmployees();
        for(Employee emp:list){
            System.out.println(emp.getSurname());
        }

        JpaUtil.destroy();
    }
    
}
