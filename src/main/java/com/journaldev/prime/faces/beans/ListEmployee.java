package com.journaldev.prime.faces.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.List;
import com.journaldev.hibernate.data.Employee;
import com.journaldev.spring.service.EmployeeService;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

@ManagedBean
@SessionScoped
public class ListEmployee {

    private EmployeeService employeeService = new EmployeeService();

    private List<Employee> listEmps = new ArrayList<Employee>();

    private Employee employee = new Employee();

    @PostConstruct
    public void init() {
        Employee e = new Employee();
        e.setEmployeeName("DUNG");
        listEmps.add(e);
        listEmps = employeeService.getAll();//dang loi
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public List<Employee> getListEmps() {
        return listEmps;
    }

    public void setListEmps(List<Employee> listEmps) {
        this.listEmps = listEmps;
    }

}
