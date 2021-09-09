package cz.fel.cvut.koubadom.onto.knowledge_search.restapiadapter.rest.model;

import java.io.Serializable;

/**
 * Entity which is intent to the client application instead of Knowledge with its all attributes
 */
public class KnowledgeDTO implements Serializable {
    private PersonDTO person;
    private Double metric;
    private String info;


    public KnowledgeDTO(PersonDTO person, Double metric, String info) {
        this.person = person;
        this.metric = metric;
        this.info = info;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public Double getMetric() {
        return metric;
    }

    public void setMetric(Double metric) {
        this.metric = metric;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
