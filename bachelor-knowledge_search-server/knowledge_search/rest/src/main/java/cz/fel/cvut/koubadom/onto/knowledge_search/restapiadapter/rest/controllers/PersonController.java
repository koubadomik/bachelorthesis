package cz.fel.cvut.koubadom.onto.knowledge_search.restapiadapter.rest.controllers;

import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.api.PersonService;
import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.model.Knowledge;
import cz.fel.cvut.koubadom.onto.knowledge_search.restapiadapter.rest.model.KnowledgeDTO;
import cz.fel.cvut.koubadom.onto.knowledge_search.restapiadapter.rest.model.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("persons")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("")
    List<PersonDTO> getAllPersons() {
        return personService.getAllPersons().stream().map(PersonDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/queryKnowledge/{concept}")
    Collection<KnowledgeDTO> searchPeopleByKnowledge(@PathVariable("concept") String conceptId) {
        Collection<? extends Knowledge> personKnowledgeMetrics;
        try {
            personKnowledgeMetrics = personService.searchPeopleByKnowledge("http://www.w3.org/2004/02/skos/core#" + conceptId, 0, 0);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Concept not found"
            );
        }
        return personKnowledgeMetrics.stream()
                                     .map(p -> new KnowledgeDTO(new PersonDTO(p.getOwner()), p.getMetric(), p.getInfo()))
                                     .collect(Collectors.toList());
    }

}
