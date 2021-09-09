package cz.fel.cvut.koubadom.onto.domain.demo.managerApi.api.impl;

import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.api.ConceptService;
import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.logic.ConceptLogic;
import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.model.Concept;
import org.springframework.stereotype.Component;

import java.util.Collection;


@Component
public class ConceptServiceImpl implements ConceptService {

    private final ConceptLogic conceptLogic;

    public ConceptServiceImpl(ConceptLogic conceptLogic) {
        this.conceptLogic = conceptLogic;
    }

    @Override
    public Collection<? extends Concept> getAllConcepts() {
        return conceptLogic.getAllConcepts();
    }
}
