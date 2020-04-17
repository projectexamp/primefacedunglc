/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;

import com.dao.FunctionDAO;
import com.dao.RoleDAO;
import com.model.pojo.Role;
import com.model.pojo.Function;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author ABC
 */
@ManagedBean(name = "roleBean")
@RequestScoped
public class RoleBean implements Serializable {

    private List< Role> roles;
    RoleDAO roleDao = new RoleDAO();
    FunctionDAO functionDao = new FunctionDAO();
    Role role = new Role();
    Role newRole = new Role();
    private List< Function> listFunctions;

    public List< Role> getRoles() {
        roles = roleDao.AllRoles();
        return roles;
    }

    public void addRole() {
        Set<Function> functions = new HashSet<>();
        if (newRole.getFunctionStr() != null && newRole.getFunctionStr().size() > 0) {
            List<String> listFunctionID = newRole.getFunctionStr();
            for (String funcID : listFunctionID) {
                Function f = new Function();
                f.setFunctionID(Long.parseLong(funcID));
                functions.add(f);
            }
            newRole.setFunction(functions);
        }
        if (newRole.getRoleID() > 0) {
            UpdateRole(newRole);
        } else {
            roleDao.add(newRole);
            System.out.println("User successfully saved.");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Role successfully saved.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);

        }
        newRole = new Role();
    }

    public void changeRole(Role r) {
        this.role = r;
    }

    public void resetNewRole() {
        this.newRole = new Role();
    }

    public void UpdateRole(Role r) {
        String Name = r.getRoleName();
        FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Name", Name);
        RequestContext.getCurrentInstance().showMessageInDialog(message1);
        roleDao.update(r);
        System.out.println("Role Info successfully saved.");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User updated successfully .");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
        role = new Role();
    }

    public void deleteRole(Role r) {

        roleDao.delete(r);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public List<Role> getListRoles() {
        return roleDao.AllRoles();
    }

    public void editRole(Role r) {
        this.newRole = r;
    }

    public List<Function> getListFunctions() {
        return functionDao.AllFunctions();
    }

    public void setListFunctions(List<Function> listFunctions) {
        this.listFunctions = listFunctions;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getNewRole() {
        return newRole;
    }

    public void setNewRole(Role newRole) {
        this.newRole = newRole;
    }
}
