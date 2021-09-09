package cz.fel.cvut.koubadom.onto.manager.model;

import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLDataProperty;
import cz.cvut.kbss.jopa.model.annotations.OWLObjectProperty;
import cz.cvut.kbss.jopa.model.annotations.ParticipationConstraints;
import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.model.Knowledge;

import java.io.Serializable;


@OWLClass(iri = "http://onto.fel.cvut.cz/koubadom/classes#Knowledge")
public class KnowledgeGDB extends AbstractEntity implements Serializable, Knowledge {

    private static String INFO = "source: GraphDB type: direct access";

    @ParticipationConstraints(nonEmpty = true)
    @OWLDataProperty(iri = "http://onto.fel.cvut.cz/koubadom/properties#weight")
    private Double metric;

    @ParticipationConstraints(nonEmpty = true)
    @OWLObjectProperty(iri = "http://onto.fel.cvut.cz/koubadom/properties#is_known_by")
    private PersonGDB owner;

    @ParticipationConstraints(nonEmpty = true)
    @OWLObjectProperty(iri = "http://onto.fel.cvut.cz/koubadom/properties#has_concept")
    private ConceptGDB concept;


    public KnowledgeGDB() {
    }

    public KnowledgeGDB(Double metric, PersonGDB owner, ConceptGDB concept) {
        this.metric = metric;
        this.owner = owner;
        this.concept = concept;
    }

    public PersonGDB getOwner() {
        return owner;
    }

    public void setOwner(PersonGDB owner) {
        this.owner = owner;
    }

    public ConceptGDB getConcept() {
        return concept;
    }

    public void setConcept(ConceptGDB concept) {
        this.concept = concept;
    }

    @Override
    public Double getMetric() {
        return metric;
    }


    public void setMetric(Double metric) {
        this.metric = metric;
    }

    @Override
    public String getInfo() {
        return INFO;
    }
}
