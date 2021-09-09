package cz.fel.cvut.koubadom.onto.domain.demo.managerApi.api;

import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.model.Concept;

import java.util.Collection;


public interface ConceptService {
    /**
     * @return Collection of all concepts that are available in the system (its storage/s)
     */
    Collection<? extends Concept> getAllConcepts();

}
