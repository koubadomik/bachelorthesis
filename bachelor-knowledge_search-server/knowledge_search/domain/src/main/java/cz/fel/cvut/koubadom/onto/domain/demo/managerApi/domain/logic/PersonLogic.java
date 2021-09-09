package cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.logic;

import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.model.Knowledge;
import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.model.Person;
import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.spi.ports.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class PersonLogic {
    private final PersonRepository personRepository;

    @Autowired
    public PersonLogic(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * @return Collection of all persons that are available in the system (its storage/s)
     */
    public Collection<? extends Person> getAllPersons() {
        return this.personRepository.getAllPersons();
    }

    /**
     * Method that find people by specified concept
     *
     * @param conceptId concept URI in string form
     * @param offset    first row in result in case of paging
     * @param limit     number of returned results
     * @return collection of knowledge (person and metric that says how much person knows the concept)
     * @throws Exception if concept does not exist in the storage
     */
    public Collection<? extends Knowledge> searchPeopleByKnowledge(String conceptId, int offset, int limit) throws Exception {
        return personRepository.searchPeopleByKnowledge(conceptId, offset, limit);
    }
}
