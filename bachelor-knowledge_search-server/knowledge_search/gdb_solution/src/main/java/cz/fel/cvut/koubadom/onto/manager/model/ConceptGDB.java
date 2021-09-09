package cz.fel.cvut.koubadom.onto.manager.model;

import cz.cvut.kbss.jopa.model.annotations.*;
import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.model.Concept;

import java.io.Serializable;
import java.util.Set;


@OWLClass(iri = "http://www.w3.org/2004/02/skos/core#Concept")
public class ConceptGDB extends AbstractEntity implements Serializable, Concept {

    @OWLAnnotationProperty(iri = "http://www.w3.org/2004/02/skos/core#prefLabel")
    private String prefLabel;

    @OWLAnnotationProperty(iri = "http://www.w3.org/2004/02/skos/core#note")
    private String note;

    @OWLObjectProperty(iri = "http://www.w3.org/2004/02/skos/core#semanticRelation", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ConceptGDB> semanticRelationList;

    @OWLObjectProperty(iri = "http://www.w3.org/2004/02/skos/core#broader", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ConceptGDB> broaderList;

    @OWLObjectProperty(iri = "http://www.w3.org/2004/02/skos/core#broaderTransive", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ConceptGDB> broaderTransitiveRelationList;

    @OWLObjectProperty(iri = "http://www.w3.org/2004/02/skos/core#narrower", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ConceptGDB> narrowList;

    @OWLObjectProperty(iri = "http://www.w3.org/2004/02/skos/core#narrowerTransitive", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ConceptGDB> narrowTransitiveRelationList;


    public ConceptGDB() {
    }

    public ConceptGDB(String prefLabel) {
        this.prefLabel = prefLabel;
    }

    public String getPrefLabel() {
        return prefLabel;
    }

    public void setPrefLabel(String prefLabel) {
        this.prefLabel = prefLabel;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Set<ConceptGDB> getSemanticRelationList() {
        return semanticRelationList;
    }

    public void setSemanticRelationList(Set<ConceptGDB> semanticRelationList) {
        this.semanticRelationList = semanticRelationList;
    }

    public Set<ConceptGDB> getBroaderList() {
        return broaderList;
    }

    public void setBroaderList(Set<ConceptGDB> broaderList) {
        this.broaderList = broaderList;
    }

    public Set<ConceptGDB> getBroaderTransitiveRelationList() {
        return broaderTransitiveRelationList;
    }

    public void setBroaderTransitiveRelationList(Set<ConceptGDB> broaderTransitiveRelationList) {
        this.broaderTransitiveRelationList = broaderTransitiveRelationList;
    }

    public Set<ConceptGDB> getNarrowList() {
        return narrowList;
    }

    public void setNarrowList(Set<ConceptGDB> narrowList) {
        this.narrowList = narrowList;
    }

    public Set<ConceptGDB> getNarrowTransitiveRelationList() {
        return narrowTransitiveRelationList;
    }

    public void setNarrowTransitiveRelationList(Set<ConceptGDB> narrowTransitiveRelationList) {
        this.narrowTransitiveRelationList = narrowTransitiveRelationList;
    }

}
