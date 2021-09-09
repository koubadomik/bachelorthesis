package cz.fel.cvut.koubadom.onto.knowledge_search.restapiadapter.rest.model;

import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.model.Concept;
import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.model.Identifiable;

import java.io.Serializable;

/**
 * Entity which is intent to the client application instead of Concept with its all attributes
 */
public class ConceptDTO implements Serializable, Identifiable {
    private String prefLabel;
    private String id;


    public ConceptDTO(String prefLabel, String id) {
        this.prefLabel = prefLabel;
        this.id = id;
    }

    /**
     * Copies all attributes that are useful for the client
     *
     * @param concept copied object
     */
    public ConceptDTO(Concept concept) {
        this.prefLabel = concept.getPrefLabel();
        this.id = concept.getId();
    }

    public String getPrefLabel() {
        return prefLabel;
    }

    public void setPrefLabel(String prefLabel) {
        this.prefLabel = prefLabel;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
