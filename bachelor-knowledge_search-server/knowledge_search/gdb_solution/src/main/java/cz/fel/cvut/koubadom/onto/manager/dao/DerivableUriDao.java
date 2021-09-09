package cz.fel.cvut.koubadom.onto.manager.dao;

import cz.cvut.kbss.jopa.model.EntityManager;
import cz.cvut.kbss.jopa.model.EntityManagerFactory;
import cz.fel.cvut.koubadom.onto.manager.model.util.HasDerivableUri;

/**
 * Data access object for classes with derivable URI.
 * <p>
 * Makes sure that the URI is generated before the instance is persisted.
 *
 * @param <T> Entity type managed by this DAO
 */
abstract class DerivableUriDao<T extends HasDerivableUri> extends BaseDao<T> {

    public DerivableUriDao(Class<T> type, EntityManagerFactory emf) {
        super(type, emf);
    }

    /**
     * Generates URI and then calls persist.
     *
     * @param entity Entity to persist
     * @param em     Current entity manager
     */
    @Override
    protected void persist(T entity, EntityManager em) {
        assert entity != null;
        entity.generateUri();
        super.persist(entity, em);
    }
}
