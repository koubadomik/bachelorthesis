package cz.fel.cvut.koubadom.onto.knowledge_search.restapiadapter.rest.controllers;

import cz.fel.cvut.koubadom.onto.domain.demo.managerApi.api.ConceptService;
import cz.fel.cvut.koubadom.onto.knowledge_search.restapiadapter.rest.model.ConceptDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/concepts")
public class ConceptController {

    private final ConceptService conceptService;

    public ConceptController(ConceptService conceptService) {
        this.conceptService = conceptService;
    }


    @GetMapping("")
    List<ConceptDTO> getAllConcepts() {
        return conceptService.getAllConcepts().stream()
                             .map(ConceptDTO::new)
                             .collect(Collectors.toList());
    }

}
