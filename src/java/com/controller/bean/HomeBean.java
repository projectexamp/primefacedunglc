/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;

import com.dao.FunctionDAO;
import com.dao.RoleDAO;
import com.dao.UserDAO;
import com.model.pojo.User;
import com.model.pojo.Role;
import com.model.pojo.Function;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author ABC
 */
@ManagedBean(name = "homeBean")
@RequestScoped
public class HomeBean implements Serializable {

    private List< Function> listFunction = new ArrayList<>();
    UserDAO userDao = new UserDAO();
    private User us = new User();
    public List<Function> getListFunction() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Map<String, Object> map = facesContext.getExternalContext().getSessionMap();
        if(map.get("username") != null){
            us = userDao.getUserbyUserName(map.get("username").toString());
            for(Role r : us.getRole()){
                for(Function f: r.getFunction()){
                    listFunction.add(f);
                }
            }
        }
        
        return listFunction;
    }

    public void setListFunction(List<Function> listFunction) {
        this.listFunction = listFunction;
    }

}
