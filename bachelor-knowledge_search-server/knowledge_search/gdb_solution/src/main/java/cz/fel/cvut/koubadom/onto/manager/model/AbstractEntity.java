package cz.fel.cvut.koubadom.onto.manager.model;

import cz.cvut.kbss.jopa.model.annotations.Id;
import cz.cvut.kbss.jopa.model.annotations.MappedSuperclass;
import cz.fel.cvut.koubadom.onto.manager.model.util.HasUri;

import java.net.URI;

/**
 * Abstract superclass for all entities with generated identifier.
 */
@MappedSuperclass
public abstract class AbstractEntity implements HasUri {

    @Id(generated = true)
    protected URI uri;

    @Override
    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public String getId() {
        return uri.toString();
    }
}
