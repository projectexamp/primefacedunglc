/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import java.util.List;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import com.util.HibernateUtil;
import com.model.pojo.User;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Raichand
 */
public class UserDAO {

    private List<User> DaoAllUsers;
    //Session session;

    public List<User> AllUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            String hql = "select U from User U";
            Query query = session.createQuery(hql);
            DaoAllUsers = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();

        return DaoAllUsers;
    }

    public Integer getId() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "select max(U.id) from User U";
        Query query = session.createQuery(hql);
        List<Integer> results = query.list();
        Integer userId = 1;
        if (results.get(0) != null) {
            userId = results.get(0) + 1;
        }
        session.flush();

        session.close();
        return userId;
    }
    
    public User getUserbyUserName(String userName) {
       Session session = HibernateUtil.getSessionFactory().openSession();
        List<User> userList = new ArrayList<>();

        try {

            session.beginTransaction();
            Query qu = session.createQuery("From User U where U.userName =:userName");//User is the entity not the table
            qu.setParameter("userName", userName);
            userList = qu.list();
            if(userList!=null && userList.size()>0){
                return userList.get(0);
            }
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();

        } finally {

            session.close();
        }
        return null;

    }

    public List<User> SearchByRecordNo(String RecordNo) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<User> daoSearchList = new ArrayList<>();

        try {

            session.beginTransaction();
            Query qu = session.createQuery("From User U where U.recordNo =:recordNo");//User is the entity not the table

            qu.setParameter("recordNo", RecordNo);
            daoSearchList = qu.list();
            int count = daoSearchList.size();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();

        } finally {

            session.close();
        }
        return daoSearchList;

    }

    public void add(User newuser) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String Id = Integer.toString(newuser.getId());
            // begin a transaction
            session.beginTransaction();
            session.merge(newuser);
            session.flush();
            System.out.println("NewUser saved, id: "
                    + newuser.getId());
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();

    }

    public void delete(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String name = user.getName();

            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        session.close();

    }

    public void update(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            session.beginTransaction();
            session.saveOrUpdate(user);
            session.flush();

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }

    public boolean login(String userName, String passWord) {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<User> us = new ArrayList<>();

        try {
            session.beginTransaction();
            Query qu = session.createQuery("From User U where U.userName =:userName and U.passWord = :passWord");//User is the entity not the table

            qu.setParameter("userName", userName);
            qu.setParameter("passWord", passWord);
            us = qu.list();
            session.getTransaction().commit();
            if(us.size()>0) return true;
            else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "LoginDAO!",
                        "Wrong password message test!"));
                return false;
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Database Error",
                    "Unable to connect database"));
            System.out.println("Error in login() -->" + ex.getMessage());
            return false;

        } finally {

            session.close();
        }

    }
    
    
    
      

}
