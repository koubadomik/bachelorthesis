package cz.fel.cvut.koubadom.onto.manager.dao;

import cz.cvut.kbss.jopa.model.EntityManager;
import cz.cvut.kbss.jopa.model.EntityManagerFactory;
import cz.fel.cvut.koubadom.onto.manager.model.ConceptGDB;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ConceptDao extends BaseDao<ConceptGDB> {
    public ConceptDao(EntityManagerFactory emf) {
        super(ConceptGDB.class, emf);
    }

    @Override
    protected List<ConceptGDB> findAll(EntityManager em) {
        return em.createNativeQuery("SELECT ?x WHERE { ?x a ?type . }", type).setParameter("type", typeUri)
                 .getResultList();
    }

}
