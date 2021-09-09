package cz.fel.cvut.koubadom.onto.manager.dao;

import cz.cvut.kbss.jopa.model.EntityManagerFactory;
import cz.fel.cvut.koubadom.onto.manager.model.WorkplaceGDB;
import org.springframework.stereotype.Repository;


@Repository
public class WorkplaceDao extends DerivableUriDao<WorkplaceGDB> {
    public WorkplaceDao(EntityManagerFactory emf) {
        super(WorkplaceGDB.class, emf);
    }

}
