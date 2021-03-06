package cz.fel.cvut.koubadom.onto.manager.dao;

import cz.cvut.kbss.jopa.model.EntityManager;
import cz.cvut.kbss.jopa.model.EntityManagerFactory;
import cz.fel.cvut.koubadom.onto.manager.exception.PersistenceException;
import cz.fel.cvut.koubadom.onto.manager.model.PersonGDB;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class BaseDaoTest extends BaseDaoTestRunner {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private EntityManagerFactory emf;

    @Test
    public void existsForExistingInstanceReturnsTrue() {
        PersonGDB person = new PersonGDB("Dominik", "Kouda", "koudadom");
        personDao.persist(person);
        assertTrue(personDao.exists(person.getUri()));
    }

    @Test
    public void findAllReturnsAllExistingInstances() {
        //arrange
        final List<PersonGDB> persons = new ArrayList<>();
        persons.add(new PersonGDB("Dominik", "Kouda", "koudadom"));
        persons.add(new PersonGDB("Dominik2", "Kouda1", "koudadom1"));
        persons.add(new PersonGDB("Dominik1", "Kouda2", "koudadom2"));
        personDao.persist(persons);
        //act
        final List<PersonGDB> result = personDao.findAll();
        //assert
        assertEquals(persons.size(), result.size());
        boolean found = false;
        for (PersonGDB person : persons) {
            for (PersonGDB person2 : result) {
                if (person.equals(person2)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found);
        }
    }


    @Test
    public void existsReturnsFalseForNullUri() {
        assertFalse(personDao.exists(null));
    }

    @Test
    public void existsReturnsFalseForNullUriWithEntityManager() {
        final EntityManager em = emf.createEntityManager();
        try {
            assertFalse(personDao.exists(null, em));
        } finally {
            em.close();
        }
    }

    @Test
    public void removeCollectionRemovesEveryInstanceInIt() {
        final List<PersonGDB> persons = generatePersons();
        personDao.persist(persons);

        personDao.remove(persons);
        persons.forEach(p -> assertNull(personDao.find(p.getUri())));
    }

    @Test
    public void removeEmptyCollectionDoesNothing() {
        final List<PersonGDB> persons = generatePersons();
        personDao.persist(persons);

        personDao.remove(Collections.emptyList());
        persons.forEach(p -> assertNotNull(personDao.find(p.getUri())));
    }

    @Test(expected = PersistenceException.class)
    public void persistThrowsPersistenceExceptionWhenExceptionIsThrownByPersistenceProvider() {
        final PersonGDB p = new PersonGDB();
        p.setName("Catherine");
        p.setSurname("Halsey");
        // No username -> IC violation
        personDao.persist(p);
    }

    @Test(expected = PersistenceException.class)
    public void persistCollectionThrowsPersistenceExceptionWhenExceptionIsThrownByPersistenceProvider() {
        final List<PersonGDB> persons = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < random.nextInt(5); i++) {
            final PersonGDB p = new PersonGDB();
            p.setName("Catherine" + i);
            p.setSurname("Halsey" + i);
            // no username
            persons.add(p);
        }
        final EntityManager em = emf.createEntityManager();
        try {
            personDao.persist(persons);
        } finally {
            assertFalse(em.createNativeQuery("ASK { ?x a ?person . }", Boolean.class).setParameter("person",
                    URI.create("http://onto.fel.cvut.cz/koubadom/classes#Person")).getSingleResult());
            em.close();
        }
    }

    //
    @Test
    public void persistCollectionDoesNothingWhenCollectionIsEmpty() {
        final List<PersonGDB> persons = new ArrayList<>();
        personDao.persist(persons);
        final EntityManager em = emf.createEntityManager();
        try {
            assertFalse(em.createNativeQuery("ASK { ?x a ?person . }", Boolean.class).setParameter("person",
                    URI.create("http://onto.fel.cvut.cz/koubadom/classes#Person")).getSingleResult());
        } finally {
            em.close();
        }
    }

    @Test(expected = PersistenceException.class)
    public void updateThrowsPersistenceExceptionWhenExceptionIsThrownByPersistenceProvider() {
        PersonGDB person = new PersonGDB("Dominik", "Kouda52", "koudadom");
        personDao.persist(person);
        person.setUsername(null);
        personDao.update(person);
    }

    @Test(expected = PersistenceException.class)
    public void removeCollectionThrowsPersistenceExceptionWhenExceptionIsThrownByPersistenceProvider() {
        final List<PersonGDB> persons = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < random.nextInt(5); i++) {
            final PersonGDB p = new PersonGDB();
            p.setName("Catherine" + i);
            p.setSurname("Halsey" + i);
            p.setUsername("halsey" + i + "@unsc.org");
            // no username
            persons.add(p);
        }
        personDao.persist(persons);
        persons.forEach(p -> p.setUri(null));
        final EntityManager em = emf.createEntityManager();
        try {
            personDao.remove(persons);
        } finally {
            persons.forEach(p -> {
                p.generateUri();
                assertNotNull(em.find(PersonGDB.class, p.getUri()));
            });
            em.close();
        }
    }

    private List<PersonGDB> generatePersons() {
        ArrayList<PersonGDB> persons = new ArrayList<>();
        persons.add(new PersonGDB("Dominik", "Havi??", "havisdom"));
        persons.add(new PersonGDB("Marek", "Rada", "marrad"));
        persons.add(new PersonGDB("Josef", "Vagner", "josvag"));
        persons.add(new PersonGDB("V??clav", "Ko??a", "v??cko??"));
        persons.add(new PersonGDB("Jan", "??tampach", "jan??ta"));
        persons.add(new PersonGDB("Pavel", "Steiner", "pavste"));
        persons.add(new PersonGDB("Vlastimil", "Antal", "vlaant"));
        persons.add(new PersonGDB("Petr", "Spask??", "petspa"));
        persons.add(new PersonGDB("Ivan", "Hausmatz", "ivahau"));
        persons.add(new PersonGDB("Daniel", "Kubiczek", "dankub"));
        persons.add(new PersonGDB("Karel", "Sob??slavsk??", "karsob"));
        persons.add(new PersonGDB("Milan", "Majer", "milmaj"));
        persons.add(new PersonGDB("Anton??n", "Van??k", "antvan"));
        persons.add(new PersonGDB("Ond??ej", "??ern??", "ond??er"));
        persons.add(new PersonGDB("Ji????", "Parolek", "ji??par"));
        persons.add(new PersonGDB("Roman", "Kov????", "romkov"));
        persons.add(new PersonGDB("Vladim??r", "Pac????ek", "vlapac"));
        persons.add(new PersonGDB("Ludv??k", "Kindler", "ludkin"));
        persons.add(new PersonGDB("Ivo", "Vl??ek", "ivovl??"));
        persons.add(new PersonGDB("Martin", "B??rta", "marb??r"));
        persons.add(new PersonGDB("Franti??ek", "Sedl????ek", "frased"));
        persons.add(new PersonGDB("Jaroslav", "Krajn??k", "jarkra"));
        persons.add(new PersonGDB("Tom????", "Forejtar", "tomfor"));
        persons.add(new PersonGDB("Mat??j", "??er??", "mat??er"));
        persons.add(new PersonGDB("Stanislav", "T??borsk??", "stat??b"));
        persons.add(new PersonGDB("Radek", "Grim", "radgri"));
        persons.add(new PersonGDB("Michal", "Handzl??k", "michan"));
        persons.add(new PersonGDB("Miroslav", "??varc", "mir??va"));
        persons.add(new PersonGDB("Old??ich", "Fuciman", "oldfuc"));
        persons.add(new PersonGDB("Ladislav", "Sekanina", "ladsek"));
        persons.add(new PersonGDB("Jind??ich", "Sanko", "jinsan"));
        return persons;
    }
}