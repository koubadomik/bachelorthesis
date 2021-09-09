package cz.fel.cvut.koubadom.onto.application.config.api.ports;

import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.api.ConceptService;
import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.api.PersonService;
import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.api.impl.ConceptServiceImpl;
import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.api.impl.PersonServiceImpl;
import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.logic.ConceptLogic;
import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.logic.PersonLogic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author domia
 * created on 28.03.2019
 */

@Configuration
public class ApplicationConfiguration {

    @Bean
    public PersonService personService(PersonLogic personLogic) {
        return new PersonServiceImpl(personLogic);
    }

    @Bean
    public ConceptService conceptService(ConceptLogic conceptLogic) {
        return new ConceptServiceImpl(conceptLogic);
    }

}
