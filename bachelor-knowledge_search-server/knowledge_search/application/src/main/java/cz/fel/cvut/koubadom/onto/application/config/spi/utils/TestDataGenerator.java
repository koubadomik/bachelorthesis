package cz.fel.cvut.koubadom.onto.application.config.spi.utils;

import cz.fel.cvut.koubadom.onto.manager.dao.ConceptDao;
import cz.fel.cvut.koubadom.onto.manager.dao.KnowledgeDao;
import cz.fel.cvut.koubadom.onto.manager.dao.PersonDao;
import cz.fel.cvut.koubadom.onto.manager.dao.WorkplaceDao;
import cz.fel.cvut.koubadom.onto.manager.model.ConceptGDB;
import cz.fel.cvut.koubadom.onto.manager.model.KnowledgeGDB;
import cz.fel.cvut.koubadom.onto.manager.model.PersonGDB;
import cz.fel.cvut.koubadom.onto.manager.model.WorkplaceGDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author domia
 * created on 22.05.2019
 */
@Component
public class TestDataGenerator {
    private KnowledgeDao knowledgeDao;
    private ConceptDao conceptDao;
    private WorkplaceDao workplaceDao;
    private PersonDao personDao;

    @Autowired
    public TestDataGenerator(KnowledgeDao knowledgeDao, ConceptDao conceptDao, WorkplaceDao workplaceDao, PersonDao personDao) {
        this.knowledgeDao = knowledgeDao;
        this.conceptDao = conceptDao;
        this.workplaceDao = workplaceDao;
        this.personDao = personDao;
    }

    public void generateTestData() {
        List<PersonGDB> personGDBS = generatePersons();
        List<WorkplaceGDB> workplaceGDBS = generateWorkPlaces();
        setLeaders(workplaceGDBS, personGDBS);
        setWorkingDepartment(workplaceGDBS, personGDBS);

        for (WorkplaceGDB workplaceGDB : workplaceGDBS) {
            workplaceDao.persist(workplaceGDB);
        }

        for (PersonGDB personGDB : personGDBS) {
            if (Objects.isNull(personGDB.getLeading_department())) {
                personDao.persist(personGDB);
            }
        }
        generateConceptHierarchy();
        generateKnowledge(personGDBS);
    }


    private void generateKnowledge(List<PersonGDB> persons) {
        List<ConceptGDB> concepts = conceptDao.findAll();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            knowledgeDao.persist(new KnowledgeGDB(random.nextDouble(),
                    persons.get(random.nextInt(persons.size())), concepts.get(random.nextInt(concepts.size()))));
        }
    }

    private void generateConceptHierarchy() {
        ConceptGDB concept1 = new ConceptGDB("Electrical engineering");

        ConceptGDB concept2 = new ConceptGDB("Informatics");
        ConceptGDB concept3 = new ConceptGDB("Mathematics");
        HashSet<ConceptGDB> electro = new HashSet<>();
        electro.add(concept2);
        electro.add(concept3);
        concept1.setNarrowList(electro);

        ConceptGDB concept4 = new ConceptGDB("Software engineering");
        ConceptGDB concept5 = new ConceptGDB("Cybernetics");
        ConceptGDB concept6 = new ConceptGDB("Artificial Intelligence");
        ConceptGDB concept7 = new ConceptGDB("Networking");
        HashSet<ConceptGDB> informatics = new HashSet<>();
        informatics.add(concept4);
        informatics.add(concept5);
        informatics.add(concept6);
        informatics.add(concept7);
        concept2.setNarrowList(informatics);


        ConceptGDB concept8 = new ConceptGDB("Calculus");
        ConceptGDB concept9 = new ConceptGDB("Discrete Mathematics");
        ConceptGDB concept10 = new ConceptGDB("Statistics and Probability");
        ConceptGDB concept11 = new ConceptGDB("Linear Algebra");
        HashSet<ConceptGDB> math = new HashSet<>();
        math.add(concept8);
        math.add(concept9);
        math.add(concept10);
        math.add(concept11);
        concept3.setNarrowList(math);


        ConceptGDB concept12 = new ConceptGDB("Programming");
        ConceptGDB concept13 = new ConceptGDB("Software Design");
        ConceptGDB concept14 = new ConceptGDB("Data Storage");
        ConceptGDB concept15 = new ConceptGDB("Software Architecture");
        HashSet<ConceptGDB> engi = new HashSet<>();
        engi.add(concept12);
        engi.add(concept13);
        engi.add(concept14);
        engi.add(concept15);
        concept4.setNarrowList(engi);


        ConceptGDB concept16 = new ConceptGDB("Pattern Recognition");
        ConceptGDB concept17 = new ConceptGDB("Critical Path Analysis");
        HashSet<ConceptGDB> cyb = new HashSet<>();
        cyb.add(concept16);
        cyb.add(concept17);
        concept5.setNarrowList(cyb);

        ConceptGDB concept18 = new ConceptGDB("Machine Learning");
        ConceptGDB concept19 = new ConceptGDB("Expert Systems");
        HashSet<ConceptGDB> electroArr = new HashSet<>();
        electroArr.add(concept18);
        electroArr.add(concept19);
        concept6.setNarrowList(electroArr);

        ConceptGDB concept20 = new ConceptGDB("Routing and Switching");
        ConceptGDB concept21 = new ConceptGDB("Configuration");
        HashSet<ConceptGDB> networking = new HashSet<>();
        networking.add(concept20);
        networking.add(concept21);
        concept7.setNarrowList(networking);

        conceptDao.persist(electro);
    }

    private void setWorkingDepartment(List<WorkplaceGDB> workplaces, List<PersonGDB> persons) {
        for (PersonGDB person : persons) {
            if (Objects.isNull(person.getWorking_department())) {
                person.setWorking_department(workplaces.get(new Random().nextInt(workplaces.size())));
            }
        }
    }

    private void setLeaders(List<WorkplaceGDB> workplaces, List<PersonGDB> persons) {
        for (WorkplaceGDB workplace : workplaces) {
            PersonGDB person = persons.get(new Random().nextInt(persons.size()));
            person.setLeading_department(workplace);
            workplace.setLeader(person);
            person.setWorking_department(workplace);
        }
    }

    private List<WorkplaceGDB> generateWorkPlaces() {
        ArrayList<WorkplaceGDB> workplaces = new ArrayList<>();
        workplaces.add(new WorkplaceGDB(13101));
        workplaces.add(new WorkplaceGDB(13102));
        workplaces.add(new WorkplaceGDB(13104));
        workplaces.add(new WorkplaceGDB(13113));
        return workplaces;
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
