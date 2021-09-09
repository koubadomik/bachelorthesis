package cz.fel.cvut.koubadom.onto.manager.model;

import cz.cvut.kbss.jopa.model.annotations.*;
import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.model.Workplace;
import cz.fel.cvut.koubadom.onto.manager.model.util.HasDerivableUri;

import java.io.Serializable;
import java.net.URI;

@OWLClass(iri = "http://onto.fel.cvut.cz/koubadom/classes#Department")
public class WorkplaceGDB implements Serializable, Workplace, HasDerivableUri {

    @Id
    private URI uri;

    @ParticipationConstraints(nonEmpty = true)
    @OWLAnnotationProperty(iri = "http://onto.fel.cvut.cz/koubadom/properties#id")
    private Integer departmentId;

    @ParticipationConstraints(nonEmpty = true)
    @OWLObjectProperty(iri = "http://onto.fel.cvut.cz/koubadom/properties#is_led_by", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private PersonGDB leader;

    public WorkplaceGDB() {
    }

    public WorkplaceGDB(Integer departmentId) {
        this.departmentId = departmentId;
        generateUri();
    }

    @Override
    public String getId() {
        generateUri();
        return uri.toString();
    }

    @Override
    public void generateUri() {
        if (uri != null) {
            return;
        }
        assert departmentId != null;
        this.uri = URI.create("http://onto.fel.cvut.cz/koubadom/individuals/departments#" + departmentId);
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public PersonGDB getLeader() {
        return leader;
    }

    public void setLeader(PersonGDB leader) {
        this.leader = leader;
    }

    @Override
    public URI getUri() {
        return uri;
    }
}