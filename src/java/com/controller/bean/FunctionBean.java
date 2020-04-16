/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;

import com.dao.FunctionDAO;
import com.model.pojo.Function;
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
@ManagedBean(name = "functionBean")
@RequestScoped
public class FunctionBean implements Serializable {

    private List< Function> functions;
    FunctionDAO functionDao = new FunctionDAO();
    Function function = new Function();
    Function newFunction = new Function();

    public List< Function> getFunctions() {
        functions = functionDao.AllFunctions();
        return functions;
    }

    public void addFunction() {

        if (newFunction.getFunctionID() > 0) {
            UpdateFunction(newFunction);
        } else {
            functionDao.add(newFunction);
            System.out.println("User successfully saved.");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Function successfully saved.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);

        }
        newFunction = new Function();
    }

    public void changeFunction(Function r) {
        this.function = r;
    }
    public void resetNewFunction() {
        this.newFunction = new Function();
    }
    public void UpdateFunction(Function r) {
        String Name = r.getFunctionName();
        FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Name", Name);
        RequestContext.getCurrentInstance().showMessageInDialog(message1);
        functionDao.update(r);
        System.out.println("Function Info successfully saved.");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User updated successfully .");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
        function = new Function();
    }

    public void deleteFunction(Function r) {
       
        functionDao.delete(r);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    
    
    public List<Function> getListFunctions(){
        return functionDao.AllFunctions();
    }
    
    
    public void editFunction(Function r) {
        this.newFunction = r;
    }

 
    
//    public void view() {
//        PrimeFacesContext.getCurrentInstance().openDynamic("viewCars");
//    }

   

//    public void onRowEdit(RowEditEvent event) {
//        FacesMessage msg = new FacesMessage(" Edited Record No", ((User) event.getObject()).getRecordNo());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//        Function editedFunction = (Function) event.getObject();
//        functionDao.update(editedFunction);
//    }
//
//    public void onCancel(RowEditEvent event) {
//        FacesMessage msg = new FacesMessage("Edit Cancelled");
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//        usersList.remove((User) event.getObject());
//    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public Function getNewFunction() {
        return newFunction;
    }

    public void setNewFunction(Function newFunction) {
        this.newFunction = newFunction;
    }
}
