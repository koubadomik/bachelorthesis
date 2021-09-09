package cz.fel.cvut.koubadom.onto.domain.demo.managerApi.spi.ports;

import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.model.Concept;

import java.util.Collection;

/**
 * Interface that is intended to be implemented by another module of this system which will be responsible for storage/API
 * integration. Interface provides methods for business logic of this application to operate with Concepts.
 */
public interface ConceptRepository {

    /**
     * @return Collection of all concepts that are available in the system (its storage/s)
     */
    Collection<? extends Concept> getAllConcepts();
}
