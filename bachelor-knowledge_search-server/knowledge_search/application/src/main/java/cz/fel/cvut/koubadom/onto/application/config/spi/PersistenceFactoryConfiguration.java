package cz.fel.cvut.koubadom.onto.application.config.spi;

import cz.cvut.kbss.jopa.Persistence;
import cz.cvut.kbss.jopa.model.EntityManagerFactory;
import cz.cvut.kbss.jopa.model.JOPAPersistenceProvider;
import cz.cvut.kbss.ontodriver.config.OntoDriverProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static cz.cvut.kbss.jopa.model.JOPAPersistenceProperties.*;
import static cz.cvut.kbss.jopa.model.PersistenceProperties.JPA_PERSISTENCE_PROVIDER;

/**
 * Sets up persistence and provides {@link EntityManagerFactory} as Spring bean.
 */
@Configuration
@PropertySource("classpath:config.properties")
public class PersistenceFactoryConfiguration {

    private static final String USERNAME_PROPERTY = "";
    private static final String PASSWORD_PROPERTY = "";
    private static final Map<String, String> DEFAULT_PARAMS = initParams();

    private final Environment environment;

    private EntityManagerFactory emf;

    @Autowired
    public PersistenceFactoryConfiguration(Environment environment) {
        this.environment = environment;
    }

    private static Map<String, String> initParams() {
        final Map<String, String> map = new HashMap<>();
        map.put(OntoDriverProperties.ONTOLOGY_LANGUAGE, "en");
        map.put(SCAN_PACKAGE, "cz.fel.cvut.koubadom.onto.manager.model");
        map.put(JPA_PERSISTENCE_PROVIDER, JOPAPersistenceProvider.class.getName());
        return map;
    }

    static Map<String, String> getDefaultParams() {
        return Collections.unmodifiableMap(DEFAULT_PARAMS);
    }

    @Bean
    @Primary
    public EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    @PostConstruct
    private void init() {
        // Allow Apache HTTP client used by RDF4J to use a larger connection pool
        // Temporary, should be configurable via JOPA
        System.setProperty("http.maxConnections", "20");
        final Map<String, String> properties = new HashMap<>(DEFAULT_PARAMS);
        properties.put(ONTOLOGY_PHYSICAL_URI_KEY, environment.getProperty("repositoryUrl"));
        properties.put(DATA_SOURCE_CLASS, environment.getProperty("driver"));
        if (environment.containsProperty(USERNAME_PROPERTY)) {
            properties.put(OntoDriverProperties.DATA_SOURCE_USERNAME, environment.getProperty(USERNAME_PROPERTY));
            properties.put(OntoDriverProperties.DATA_SOURCE_PASSWORD, environment.getProperty(PASSWORD_PROPERTY));
        }
        this.emf = Persistence.createEntityManagerFactory("inbasPU", properties);
    }

    @PreDestroy
    private void close() {
        if (emf.isOpen()) {
            emf.close();
        }
    }
}
