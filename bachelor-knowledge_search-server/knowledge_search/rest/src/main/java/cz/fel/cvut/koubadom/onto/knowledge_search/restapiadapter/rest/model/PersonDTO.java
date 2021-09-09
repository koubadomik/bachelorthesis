package cz.fel.cvut.koubadom.onto.knowledge_search.restapiadapter.rest.model;

import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.model.Identifiable;
import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.model.Person;

import java.io.Serializable;

/**
 * Entity which is intent to the client application instead of Person with its all attributes
 */
public class PersonDTO implements Serializable, Identifiable {

    private String name;
    private String surname;
    private String username;
    private String id;

    /**
     * Copies all attributes that are useful for the client
     *
     * @param person copied object
     */
    public PersonDTO(Person person) {
        this.name = person.getName();
        this.surname = person.getSurname();
        this.username = person.getUsername();
        this.id = person.getId();
    }

    public PersonDTO(String name, String surname) {
        this.name = name;
        this.surname = surname;
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

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
