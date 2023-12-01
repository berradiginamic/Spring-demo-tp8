package fr.digi.m09231.controleur;

import fr.digi.m09231.daos.DepartementDao;
import fr.digi.m09231.services.Departement;
import fr.digi.m09231.services.Ville;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departements")
public class DepartementControleur {

    private final DepartementDao departementDao;

    public DepartementControleur(DepartementDao departementDao) {
        this.departementDao = departementDao;
    }

    @GetMapping("/tous")
    public List<Departement> extraireTous() {
        return departementDao.extractAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departement> extractDepartement(@PathVariable Long id) {
        Departement departement = departementDao.extractDepartement(id);
        return departement != null
                ? new ResponseEntity<>(departement, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/departements")
    public ResponseEntity<String> insererDepartement(@RequestBody Departement departement) {
        try {
            // Ajoutez ici vos contrôles d'insertion pour le département
            departementDao.insert(departement);
            return new ResponseEntity<>("Département inséré avec succès", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erreur lors de l'insertion du département : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerDepartement(@PathVariable Long id) {
        Departement departement = departementDao.extractDepartement(id);
        if (departement != null) {
            departementDao.supprimerDepartement(id);
            return new ResponseEntity<>("Département supprimé avec succès", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Aucun département trouvé avec cet ID", HttpStatus.NOT_FOUND);
        }
    }
}