package cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.model;

/**
 * Entity that represents anything that could be known bx person.
 */
public interface Concept extends Identifiable {

    public String getPrefLabel();

    public void setPrefLabel(String prefLabel);

    public String getNote();

    public void setNote(String note);
}
