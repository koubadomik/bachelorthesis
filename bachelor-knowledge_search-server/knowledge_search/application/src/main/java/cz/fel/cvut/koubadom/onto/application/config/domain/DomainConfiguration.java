package cz.fel.cvut.koubadom.onto.application.config.domain;

import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.logic.ConceptLogic;
import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.logic.PersonLogic;
import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.spi.ports.ConceptRepository;
import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.spi.ports.PersonRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author domia
 * created on 01.05.2019
 */
@Configuration
public class DomainConfiguration {
    @Bean
    public PersonLogic personLogic(PersonRepository personRepository) {
        return new PersonLogic(personRepository);
    }

    @Bean
    public ConceptLogic conceptLogic(ConceptRepository conceptRepository) {
        return new ConceptLogic(conceptRepository);
    }

}
