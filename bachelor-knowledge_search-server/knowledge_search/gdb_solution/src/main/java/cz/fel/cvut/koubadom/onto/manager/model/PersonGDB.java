package cz.fel.cvut.koubadom.onto.manager.model;

import cz.cvut.kbss.jopa.model.annotations.*;
import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.model.Person;
import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.model.Workplace;
import cz.fel.cvut.koubadom.onto.manager.model.util.HasDerivableUri;

import java.io.Serializable;
import java.net.URI;
import java.util.Objects;
import java.util.Set;


@OWLClass(iri = "http://onto.fel.cvut.cz/koubadom/classes#Person")
public class PersonGDB implements Serializable, Person, HasDerivableUri {

    @Id
    private URI uri;

    @ParticipationConstraints(nonEmpty = true)
    @OWLDataProperty(iri = "http://onto.fel.cvut.cz/koubadom/properties#name")
    private String name;

    @ParticipationConstraints(nonEmpty = true)
    @OWLDataProperty(iri = "http://onto.fel.cvut.cz/koubadom/properties#surname")
    private String surname;

    @ParticipationConstraints(nonEmpty = true)
    @OWLDataProperty(iri = "http://onto.fel.cvut.cz/koubadom/properties#username")
    private String username;

    @OWLObjectProperty(iri = "http://onto.fel.cvut.cz/koubadom/properties#knows", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<KnowledgeGDB> knowledges;

    //    @ParticipationConstraints(nonEmpty = true)
    @OWLObjectProperty(iri = "http://onto.fel.cvut.cz/koubadom/properties#works_for", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private WorkplaceGDB working_department;

    @OWLObjectProperty(iri = "http://onto.fel.cvut.cz/koubadom/properties#leads", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private WorkplaceGDB leading_department;

    public PersonGDB() {
    }

    public PersonGDB(String name, String surname, String username) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        generateUri();
    }

    @Override
    public void generateUri() {
        if (uri != null) {
            return;
        }
        assert name != null;
        assert surname != null;
        this.uri = URI.create("http://onto.fel.cvut.cz/koubadom/individuals/persons#" + username);
    }

    @Override
    public String getId() {
        generateUri();
        return uri.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonGDB personGDB = (PersonGDB) o;
        return Objects.equals(getId(), personGDB.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(uri);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<KnowledgeGDB> getKnowledges() {
        return knowledges;
    }

    public void setKnowledges(Set<KnowledgeGDB> knowledges) {
        this.knowledges = knowledges;
    }

    public Workplace getWorking_department() {
        return working_department;
    }

    public void setWorking_department(WorkplaceGDB working_department) {
        this.working_department = working_department;
    }

    public Workplace getLeading_department() {
        return leading_department;
    }

    public void setLeading_department(WorkplaceGDB leading_department) {
        this.leading_department = leading_department;
    }

    @Override
    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }
}
