package cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.model;

import java.util.Set;


public interface Person extends Identifiable {

    public String getName();

    public void setName(String name);

    public String getSurname();

    public void setSurname(String surname);

    public String getUsername();

    public void setUsername(String username);

    public Set<? extends Knowledge> getKnowledges();

    public <T extends Workplace> T getWorking_department();

    public <T extends Workplace> T getLeading_department();
}
