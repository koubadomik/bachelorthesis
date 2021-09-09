package cz.fel.cvut.koubadom.onto.manager.adapters;

import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.model.Concept;
import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.spi.ports.ConceptRepository;
import cz.fel.cvut.koubadom.onto.manager.dao.ConceptDao;

import java.util.Collection;


public class ConceptRepositoryImpl implements ConceptRepository {

    private final ConceptDao conceptDao;

    public ConceptRepositoryImpl(ConceptDao conceptDao) {
        this.conceptDao = conceptDao;
    }

    @Override
    public Collection<? extends Concept> getAllConcepts() {
        return conceptDao.findAll();
    }

}
