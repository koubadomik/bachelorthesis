package cz.fel.cvut.koubadom.onto.domain.demo.managerApi.api;

import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.model.Knowledge;
import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.model.Person;

import java.util.Collection;


public interface PersonService {

    /**
     * @return Collection of all persons that are available in the system (its storage/s)
     */
    Collection<? extends Person> getAllPersons();

    /**
     * Method that find people by specified concept
     *
     * @param conceptId concept URI in string form
     * @param offset    first row in result in case of paging
     * @param limit     number of returned results
     * @return collection of knowledge (person and metric that says how much person knows the concept)
     * @throws Exception if concept does not exist in the storage
     */
    public Collection<? extends Knowledge> searchPeopleByKnowledge(String conceptId, int offset, int limit) throws Exception;

}
