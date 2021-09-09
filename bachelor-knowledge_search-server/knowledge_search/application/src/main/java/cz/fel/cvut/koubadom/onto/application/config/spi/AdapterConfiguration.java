package cz.fel.cvut.koubadom.onto.application.config.spi;

import cz.cvut.kbss.jopa.model.EntityManagerFactory;
import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.spi.ports.ConceptRepository;
import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.spi.ports.PersonRepository;
import cz.fel.cvut.koubadom.onto.manager.adapters.ConceptRepositoryImpl;
import cz.fel.cvut.koubadom.onto.manager.adapters.PersonRepositoryImpl;
import cz.fel.cvut.koubadom.onto.manager.dao.ConceptDao;
import cz.fel.cvut.koubadom.onto.manager.dao.KnowledgeDao;
import cz.fel.cvut.koubadom.onto.manager.dao.PersonDao;
import cz.fel.cvut.koubadom.onto.manager.dao.WorkplaceDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author domia
 * created on 28.03.2019
 */
@Configuration
public class AdapterConfiguration {

    @Bean
    public PersonDao personDao(EntityManagerFactory emf) {
        return new PersonDao(emf);
    }

    @Bean
    public PersonRepository personRepository(PersonDao personDao) {
        return new PersonRepositoryImpl(personDao);
    }

    @Bean
    public ConceptDao conceptDao(EntityManagerFactory emf) {
        return new ConceptDao(emf);
    }

    @Bean
    public ConceptRepository conceptRepository(ConceptDao conceptDao) {
        return new ConceptRepositoryImpl(conceptDao);
    }


    @Bean
    public KnowledgeDao knowledgeDao(EntityManagerFactory emf) {
        return new KnowledgeDao(emf);
    }

    @Bean
    public WorkplaceDao workplaceDao(EntityManagerFactory emf) {
        return new WorkplaceDao(emf);
    }


}
