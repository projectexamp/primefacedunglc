/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;

import com.dao.RoleDAO;
import com.model.pojo.Role;
import java.io.Serializable;
import java.util.List;
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
    Role role = new Role();
    Role newRole = new Role();

    public List< Role> getRoles() {
        roles = roleDao.AllRoles();
        return roles;
    }

    public void addRole() {

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

    
    
    public List<Role> getListRoles(){
        return roleDao.AllRoles();
    }
    
    
    public void editRole(Role r) {
        this.newRole = r;
    }

 
    
//    public void view() {
//        PrimeFacesContext.getCurrentInstance().openDynamic("viewCars");
//    }

   

//    public void onRowEdit(RowEditEvent event) {
//        FacesMessage msg = new FacesMessage(" Edited Record No", ((User) event.getObject()).getRecordNo());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//        Role editedRole = (Role) event.getObject();
//        roleDao.update(editedRole);
//    }
//
//    public void onCancel(RowEditEvent event) {
//        FacesMessage msg = new FacesMessage("Edit Cancelled");
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//        usersList.remove((User) event.getObject());
//    }

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
