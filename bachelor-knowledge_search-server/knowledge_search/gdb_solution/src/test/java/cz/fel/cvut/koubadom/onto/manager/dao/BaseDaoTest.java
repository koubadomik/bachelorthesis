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
        persons.add(new PersonGDB("Dominik", "Haviš", "havisdom"));
        persons.add(new PersonGDB("Marek", "Rada", "marrad"));
        persons.add(new PersonGDB("Josef", "Vagner", "josvag"));
        persons.add(new PersonGDB("Václav", "Koča", "váckoč"));
        persons.add(new PersonGDB("Jan", "Štampach", "janšta"));
        persons.add(new PersonGDB("Pavel", "Steiner", "pavste"));
        persons.add(new PersonGDB("Vlastimil", "Antal", "vlaant"));
        persons.add(new PersonGDB("Petr", "Spaský", "petspa"));
        persons.add(new PersonGDB("Ivan", "Hausmatz", "ivahau"));
        persons.add(new PersonGDB("Daniel", "Kubiczek", "dankub"));
        persons.add(new PersonGDB("Karel", "Soběslavský", "karsob"));
        persons.add(new PersonGDB("Milan", "Majer", "milmaj"));
        persons.add(new PersonGDB("Antonín", "Vaněk", "antvan"));
        persons.add(new PersonGDB("Ondřej", "Černý", "ondčer"));
        persons.add(new PersonGDB("Jiří", "Parolek", "jiřpar"));
        persons.add(new PersonGDB("Roman", "Kovář", "romkov"));
        persons.add(new PersonGDB("Vladimír", "Pacáček", "vlapac"));
        persons.add(new PersonGDB("Ludvík", "Kindler", "ludkin"));
        persons.add(new PersonGDB("Ivo", "Vlček", "ivovlč"));
        persons.add(new PersonGDB("Martin", "Bárta", "marbár"));
        persons.add(new PersonGDB("František", "Sedláček", "frased"));
        persons.add(new PersonGDB("Jaroslav", "Krajník", "jarkra"));
        persons.add(new PersonGDB("Tomáš", "Forejtar", "tomfor"));
        persons.add(new PersonGDB("Matěj", "Šerý", "matšer"));
        persons.add(new PersonGDB("Stanislav", "Táborský", "statáb"));
        persons.add(new PersonGDB("Radek", "Grim", "radgri"));
        persons.add(new PersonGDB("Michal", "Handzlík", "michan"));
        persons.add(new PersonGDB("Miroslav", "Švarc", "miršva"));
        persons.add(new PersonGDB("Oldřich", "Fuciman", "oldfuc"));
        persons.add(new PersonGDB("Ladislav", "Sekanina", "ladsek"));
        persons.add(new PersonGDB("Jindřich", "Sanko", "jinsan"));
        return persons;
    }
}