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
import com.model.pojo.Function;

/**
 *
 * @author Raichand
 */

public class FunctionDAO {

    private Function function;
    private Function newFunction;
    private List<Function> DaoAllFunction;
    //Session session;
    
    public List<Function> AllFunctions() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            session.beginTransaction();
            DaoAllFunction = session.createCriteria(Function.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();

        return DaoAllFunction;
    }

    

   
    public void add(Function newFunction) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            
            session.beginTransaction();
            session.merge(newFunction);
            session.flush();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();

    }

    public void delete(Function function) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(function);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        session.close();

    }

    public void update(Function function) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            session.beginTransaction();
            session.update(function);
            session.flush();

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
}
