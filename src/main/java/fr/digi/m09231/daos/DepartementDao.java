package fr.digi.m09231.daos;

import fr.digi.m09231.services.Departement;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Component
public class DepartementDao {

    @PersistenceContext
    private EntityManager em;

    public List<Departement> extractAll() {
        TypedQuery<Departement> query = em.createQuery("SELECT d FROM Departement d", Departement.class);
        return query.getResultList();
    }

    public Departement extractDepartement(Long id) {
        return em.find(Departement.class, id);
    }

    @Transactional
    public void insert(Departement nouveauDepartement) {
        em.persist(nouveauDepartement);
    }

    @Transactional
    public void supprimerDepartement(Long id) {
        Departement departement = extractDepartement(id);
        if (departement != null) {
            em.remove(departement);
        }
    }

    @Transactional
    public void modifierDepartement(Departement nouveauDepartement) {
        em.merge(nouveauDepartement);
    }
}