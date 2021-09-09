package cz.fel.cvut.koubadom.onto.manager.dto;

import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.model.Concept;
import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.model.Knowledge;
import cz.fel.cvut.koubadom.onto.manager.model.PersonGDB;

import java.net.URI;
import java.util.HashSet;
import java.util.Objects;


public class KnowledgeDTO implements Knowledge {
    private PersonGDB person;
    private Double weightOfKnowledge;
    private HashSet<URI> transitiveConceptsURLs;

    public KnowledgeDTO() {
        transitiveConceptsURLs = new HashSet<>();
    }

    public KnowledgeDTO(PersonGDB person, Double weightOfKnowledge, HashSet<URI> transitiveConceptsURLs) {
        this.person = person;
        this.weightOfKnowledge = weightOfKnowledge;
        this.transitiveConceptsURLs = transitiveConceptsURLs;
    }

    public boolean addConceptURL(URI uri) {
        if (Objects.nonNull(uri)) {
            return this.transitiveConceptsURLs.add(uri);
        } else {
            return false;
        }
    }


    @Override
    public String getInfo() {
        return "Source: GraphDB " + "Info: " +
                getTransitiveConceptsURLs().size() + " intermediate concepts";
    }


    @Override
    public <T extends Concept> T getConcept() {
        return null;
    }

    @Override
    public Double getMetric() {
        return getWeightOfKnowledge() / (getTransitiveConceptsURLs().size() + 1);
    }

    @Override
    public String getId() {
        return null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KnowledgeDTO that = (KnowledgeDTO) o;
        return Objects.equals(person, that.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person);
    }

    public PersonGDB getOwner() {
        return person;
    }

    public void setOwner(PersonGDB person) {
        this.person = person;
    }

    public HashSet<URI> getTransitiveConceptsURLs() {
        return transitiveConceptsURLs;
    }

    public Double getWeightOfKnowledge() {
        return weightOfKnowledge;
    }

    public void setWeightOfKnowledge(Double weightOfKnowledge) {
        this.weightOfKnowledge = weightOfKnowledge;
    }


}
