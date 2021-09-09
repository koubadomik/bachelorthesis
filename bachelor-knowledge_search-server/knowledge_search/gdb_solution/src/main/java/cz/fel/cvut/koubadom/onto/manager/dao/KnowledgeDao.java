package cz.fel.cvut.koubadom.onto.manager.dao;

import cz.cvut.kbss.jopa.model.EntityManagerFactory;
import cz.fel.cvut.koubadom.onto.manager.model.KnowledgeGDB;
import org.springframework.stereotype.Repository;

@Repository
public class KnowledgeDao extends BaseDao<KnowledgeGDB> {
    public KnowledgeDao(EntityManagerFactory emf) {
        super(KnowledgeGDB.class, emf);
    }


}
