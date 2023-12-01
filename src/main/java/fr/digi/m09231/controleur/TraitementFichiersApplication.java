import fr.digi.m09231.daos.VilleDao;
import fr.digi.m09231.services.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class TraitementFichiersApplication implements CommandLineRunner {

    @Autowired
    private VilleDao villeDao;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(TraitementFichiersApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Utilisez villeDao pour insérer des villes depuis le fichier
        // Exemple d'utilisation avec le chemin du fichier "C:/Temp/recensement.csv"
        List<Ville> villes = lireFichierEtExtraireVilles("C:/Temp/recensement.csv");
        for (Ville ville : villes) {
            villeDao.insert(ville);
        }
    }

    private List<Ville> lireFichierEtExtraireVilles(String cheminFichier) {
        // Ajoutez votre logique pour lire le fichier et extraire les villes
        // Utilisez la classe Path et Files pour lire le fichier CSV par exemple
        // Retournez la liste des villes lues
        return null; // Remplacez ceci par la vraie implémentation
    }
}