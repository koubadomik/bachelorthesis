package cz.fel.cvut.koubadom.onto.domain.demo.managerApi.api.impl;

import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.api.PersonService;
import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.logic.PersonLogic;
import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.model.Knowledge;
import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class PersonServiceImpl implements PersonService {

    private final PersonLogic personLogic;

    @Autowired
    public PersonServiceImpl(PersonLogic personLogic) {
        this.personLogic = personLogic;
    }

    @Override
    public Collection<? extends Person> getAllPersons() {
        return this.personLogic.getAllPersons();
    }

    @Override
    public Collection<? extends Knowledge> searchPeopleByKnowledge(String conceptId, int offset, int limit) throws Exception {
        return personLogic.searchPeopleByKnowledge(conceptId, offset, limit);
    }

}
