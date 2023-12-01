package fr.digi.m09231.controleur;

import fr.digi.m09231.daos.DepartementNotFoundException;
import fr.digi.m09231.daos.VilleDao;
import fr.digi.m09231.services.CodeDeptException;
import fr.digi.m09231.services.Ville;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    private final VilleDao villeDao;

    public VilleControleur(VilleDao villeDao) {
        this.villeDao = villeDao;
    }

    @PostMapping("/departement/{departementId}")
    public ResponseEntity<String> insererVilleDansDepartement(@RequestBody Ville ville, @PathVariable Long departementId) {
        try {
            // Valider le code département ici
            if (ville.getCodeDepartement().length() != 2) {
                throw new CodeDeptException("Le code département doit avoir 2 caractères.");
            }

            villeDao.insert(ville, departementId);
            return new ResponseEntity<>("Ville insérée avec succès dans le département", HttpStatus.CREATED);
        } catch (DepartementNotFoundException e) {
            return new ResponseEntity<>("Erreur lors de l'insertion de la ville : " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (CodeDeptException e) {
            return new ResponseEntity<>("Erreur lors de l'insertion de la ville : " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Erreur lors de l'insertion de la ville : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/toutes")
    public List<Ville> extraireToutes() {
        return villeDao.extractAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ville> extractVille(@PathVariable Long id) {
        Ville ville = villeDao.extractVille(id);
        return ville != null
                ? new ResponseEntity<>(ville, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/villes")
    public ResponseEntity<String> insererVille(@RequestBody Ville ville) {
        try {
            villeDao.insert(ville);
            return new ResponseEntity<>("Ville insérée avec succès", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erreur lors de l'insertion de la ville : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}