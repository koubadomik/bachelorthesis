package cz.fel.cvut.koubadom.onto.application.config.api.adapters;

import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.api.ConceptService;
import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.api.PersonService;
import cz.fel.cvut.koubadom.onto.knowledge_search.restapiadapter.rest.controllers.ConceptController;
import cz.fel.cvut.koubadom.onto.knowledge_search.restapiadapter.rest.controllers.PersonController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author domia
 * created on 28.03.2019
 */
@Configuration
public class RestConfiguration {

    @Bean
    public PersonController personController(PersonService personService) {
        return new PersonController(personService);
    }

    @Bean
    public ConceptController conceptController(ConceptService conceptService) {
        return new ConceptController(conceptService);
    }

}
