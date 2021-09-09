package cz.fel.cvut.koubadom.onto.manager.dao;

import cz.cvut.kbss.jopa.exceptions.EntityNotFoundException;
import cz.cvut.kbss.jopa.model.EntityManager;
import cz.cvut.kbss.jopa.model.EntityManagerFactory;
import cz.fel.cvut.koubadom.onto.manager.dao.util.Queries;
import cz.fel.cvut.koubadom.onto.manager.dto.KnowledgeDTO;
import cz.fel.cvut.koubadom.onto.manager.dto.utils.KnowledgeDTOList;
import cz.fel.cvut.koubadom.onto.manager.model.PersonGDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Repository
public class PersonDao extends DerivableUriDao<PersonGDB> {

    @Autowired
    private ConceptDao conceptDao;

    public PersonDao(EntityManagerFactory emf) {
        super(PersonGDB.class, emf);
    }

    /**
     * Method that find people by specified concept. Generally, it goes from radius 0 to 4 from this concept and it tries to find knowledge objects
     * and people (owners).
     *
     * @param conceptId concept URI in string form
     * @param offset    first row in result in case of paging
     * @param limit     number of returned results
     * @return collection of knowledgeDTO (person, metric and number of transitive concepts)
     * @throws EntityNotFoundException if concept does not exist in the storage
     */
    public List<KnowledgeDTO> searchPeopleByKnowledge(String conceptId, int offset, int limit) throws EntityNotFoundException {
        Objects.requireNonNull(conceptId);
        KnowledgeDTOList result = new KnowledgeDTOList();
        if (!(conceptDao.exists(URI.create(conceptId)))) {
            throw new EntityNotFoundException("Concept not found");
        }
        final EntityManager em = entityManager();
        try {
            final List rows = em.createNativeQuery(
                    Queries.PATH_FINDING)
                                .setParameter("hasName", URI.create("http://onto.fel.cvut.cz/koubadom/properties#name"))
                                .setParameter("hasSurname", URI.create("http://onto.fel.cvut.cz/koubadom/properties#surname"))
                                .setParameter("hasUsername", URI.create("http://onto.fel.cvut.cz/koubadom/properties#username"))
                                .setParameter("knows", URI.create("http://onto.fel.cvut.cz/koubadom/properties#knows"))
                                .setParameter("hasWeight", URI.create("http://onto.fel.cvut.cz/koubadom/properties#weight"))
                                .setParameter("hasConcept", URI.create("http://onto.fel.cvut.cz/koubadom/properties#has_concept"))
                                .setParameter("semanticRelation", URI.create("http://www.w3.org/2004/02/skos/core#semanticRelation"))
                                .setParameter("concept", URI.create(conceptId))
                                .getResultList();
            for (Object row : rows) {
                final Object[] rowArr = (Object[]) row;
                KnowledgeDTO personKnowledge = new KnowledgeDTO();
                PersonGDB person = new PersonGDB();

                person.setUri((URI) rowArr[0]);
                person.setName((String) rowArr[1]);
                person.setSurname((String) rowArr[2]);
                person.setUsername((String) rowArr[3]);

                personKnowledge.setOwner(person);
                personKnowledge.setWeightOfKnowledge((Double) rowArr[4]);
                personKnowledge.addConceptURL((URI) rowArr[5]);
                personKnowledge.addConceptURL((URI) rowArr[6]);
                personKnowledge.addConceptURL((URI) rowArr[7]);
                personKnowledge.addConceptURL((URI) rowArr[8]);

                result.add(personKnowledge);
            }
            return result.getKnowledgeDTOs();
        } finally {
            em.close();
        }
    }


}
