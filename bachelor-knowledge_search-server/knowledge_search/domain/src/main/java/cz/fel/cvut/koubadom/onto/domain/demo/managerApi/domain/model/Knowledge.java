package cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.model;


/**
 * Entity that represents connection between person and concept.
 */
public interface Knowledge extends Identifiable {
    /**
     * @return metric that represent how much persons "knows" specified concept.
     */
    Double getMetric();

    /**
     * @return info that is anything which is relevant to this knowledge (source of the knowledge, metric calculation...)
     */
    String getInfo();

    <T extends Person> T getOwner();

    <T extends Concept> T getConcept();
}
