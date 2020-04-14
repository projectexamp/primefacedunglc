/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;

import com.dao.RoleDAO;
import com.dao.UserDAO;
import com.model.pojo.User;
import com.model.pojo.Role;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.PrimeFacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.component.dialog.Dialog;
/**
 *
 * @author ABC
 */
@ManagedBean(name = "userBean")
@RequestScoped
public class UserSFManagedBean implements Serializable {

    private List< User> usersList;
    private List< User> searchList;
    private List< User> searchByRecordNoList;
    private List< Role> listRoles;
    UserDAO userDao = new UserDAO();
    RoleDAO roleDao = new RoleDAO();
    User user = new User();
    User newuser = new User();

    public List< User> getUsers() {
        usersList = userDao.AllUsers();
        return usersList;
    }

    public void addUser() {

        if (newuser.getId() != null && newuser.getId() > 0) {
            UpdateUser(newuser);
        } else {
            Integer userId = 0;
            userId = userDao.getId();
            newuser.setId(userId);
            newuser.setRecordNo(Integer.toString(userId));
            Set<Role> roles = new HashSet<>();
            if(newuser.getRoletring()!= null && newuser.getRoletring().size()>0){
                List<String> listRoleID = newuser.getRoletring();
                for(String roleID: listRoleID){
                    Role r = new Role();
                    r.setRoleID(Long.parseLong(roleID));
                    roles.add(r);
                }
                newuser.setRole(roles);
                
            }
            userDao.add(newuser);
            System.out.println("User successfully saved.");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User successfully saved.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);

        }
        newuser = new User();
    }

    public void changeUser(User user) {
        this.user = user;
    }
    public void resetNewUser() {
        this.newuser = new User();
    }
    public void UpdateUser(User us) {
        String Name = us.getName();
        FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Name", Name);
        RequestContext.getCurrentInstance().showMessageInDialog(message1);
        
         Set<Role> roles = new HashSet<>();
            if(us.getRoletring()!= null && us.getRoletring().size()>0){
                List<String> listRoleID = us.getRoletring();
                for(String roleID: listRoleID){
                    Role r = new Role();
                    r.setRoleID(Long.parseLong(roleID));
                    roles.add(r);
                }
                us.setRole(roles);
                
            }
        userDao.update(us);
        System.out.println("User Info successfully saved.");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User updated successfully .");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
        user = new User();
    }

    public void deleteUser(User user) {
        String Name = user.getName();
        //FacesMessage message3= new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete Item",contactName);  
        // RequestContext.getCurrentInstance().showMessageInDialog(message3);  
        userDao.delete(user);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public void searchbyRecordno() {
        searchByRecordNoList = userDao.SearchByRecordNo(user.getRecordNo());
        if (searchByRecordNoList != null && searchByRecordNoList.size() > 0) {
            this.setUser(searchByRecordNoList.get(0));
        }
        int count = searchByRecordNoList.size();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Number of Record Selected:", Integer.toString(count));
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
    
    public List<Role> getListRoles(){
        return roleDao.AllRoles();
    }
    
    public void setlistRoles(List<Role> roles){
        this.listRoles = roles;
    }
    
    public void editUser(User us) {
        this.newuser = us;
    }

    public void buttonAction() {
        addMessage("Welcome to PrimeFaces!!");
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
//    public void view() {
//        PrimeFacesContext.getCurrentInstance().openDynamic("viewCars");
//    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public User getNewuser() {
        return newuser;
    }

    public void setNewuser(User newuser) {
        this.newuser = newuser;
    }

    public List< User> getUsersList() {
        return usersList;
    }

    public void setUsersList(List< User> usersList) {
        this.usersList = usersList;
    }

    public List< User> getSearchList() {
        return searchList;
    }

    public void setSearchList(List< User> searchList) {
        this.searchList = searchList;
    }

    public List< User> getSearchByRecordNoList() {
        return searchByRecordNoList;
    }

    public void setSearchByRecordNoList(List< User> searchByRecordNoList) {
        this.searchByRecordNoList = searchByRecordNoList;
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage(" Edited Record No", ((User) event.getObject()).getRecordNo());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        User editeduser = (User) event.getObject();
        userDao.update(editeduser);
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        usersList.remove((User) event.getObject());
    }
}
