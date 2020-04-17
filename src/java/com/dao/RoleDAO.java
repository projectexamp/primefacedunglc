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
import com.model.pojo.Role;

/**
 *
 * @author Raichand
 */

public class RoleDAO {

    private Role role;
    private Role newRole;
    private List<Role> DaoAllRole;
    //Session session;
    
    public List<Role> AllRoles() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            String hql = "select R from Role R";
            Query query = session.createQuery(hql);
            DaoAllRole = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();

        return DaoAllRole;
    }

    public Integer getId() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "select max(U.id) from Role U";
        Query query = session.createQuery(hql);
        List<Integer> results = query.list();
        Integer roleId = 1;
        if (results.get(0) != null) {
            roleId = results.get(0) + 1;
        }
        session.flush();

        session.close();
        return roleId;
    }

   
    public void add(Role newRole) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            
            session.beginTransaction();
            session.merge(newRole);
            session.flush();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();

    }

    public void delete(Role role) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(role);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        session.close();

    }

    public void update(Role role) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            session.beginTransaction();
            session.update(role);
            session.flush();

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
}
