package cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.logic;

import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.model.Concept;
import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.spi.ports.ConceptRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;


@Component
public class ConceptLogic {
    private final ConceptRepository conceptRepository;

    public ConceptLogic(ConceptRepository conceptRepository) {
        this.conceptRepository = conceptRepository;
    }

    /**
     * @return Collection of all concepts that are available in the system (its storage/s)
     */
    public Collection<? extends Concept> getAllConcepts() {
        return conceptRepository.getAllConcepts();
    }
}
