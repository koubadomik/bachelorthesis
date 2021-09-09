package cz.fel.cvut.koubadom.onto.manager.dao;

import cz.cvut.kbss.jopa.exceptions.EntityNotFoundException;
import cz.fel.cvut.koubadom.onto.manager.dto.KnowledgeDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class PersonDaoTest extends BaseDaoTestRunner {

    @Autowired
    private PersonDao personDao;


    @Test(expected = EntityNotFoundException.class)
    public void searchPeopleByKnowledgeThrowsExceptionIfConceptNotFound() {
        List<KnowledgeDTO> knowledgeDTOS = personDao.searchPeopleByKnowledge("http://www.w3.org/2004/02/skos/core#frgtr", 0, 0);
    }


}