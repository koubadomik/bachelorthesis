package cz.fel.cvut.koubadom.onto.domain.demo.managerApi.domain.model;


public interface Workplace extends Identifiable {

    Integer getDepartmentId();

    void setDepartmentId(Integer departmentId);

    Person getLeader();

}
