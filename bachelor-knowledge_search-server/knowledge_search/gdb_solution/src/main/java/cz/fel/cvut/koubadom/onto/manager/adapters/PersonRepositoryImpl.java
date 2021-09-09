package cz.fel.cvut.koubadom.onto.manager.adapters;

import cz.cvut.kbss.jopa.exceptions.EntityNotFoundException;
import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.model.Knowledge;
import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.model.Person;
import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.spi.ports.PersonRepository;
import cz.fel.cvut.koubadom.onto.manager.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;


public class PersonRepositoryImpl implements PersonRepository {

    private final PersonDao personDao;

    @Autowired
    public PersonRepositoryImpl(PersonDao personDao) {
        this.personDao = personDao;
    }


    @Override
    public Collection<? extends Knowledge> searchPeopleByKnowledge(String conceptId, int offset, int limit) throws EntityNotFoundException {
        return personDao.searchPeopleByKnowledge(conceptId, offset, limit);
    }

    @Override
    public Collection<? extends Person> getAllPersons() {
        return personDao.findAll();
    }


}
