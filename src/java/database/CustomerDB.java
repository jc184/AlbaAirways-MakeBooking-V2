/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import entities.Customer;
import javax.persistence.EntityTransaction;

/**
 *
 * @author james
 */
public class CustomerDB {

    public static Customer selectCustomer(String emailAddress) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        String qString = "SELECT c FROM Customer c WHERE c.emailAddress = :emailAddress";
        TypedQuery<Customer> tq = em.createQuery(qString, Customer.class);
        tq.setParameter("emailAddress", emailAddress);
        Customer result = null;
        try {
            result = tq.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } finally {
            em.close();
        }
        return result;
    }

    public static Customer selectCustomer(String emailAddress, String loginPassword) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        String qString = "SELECT c FROM Customer c WHERE (c.emailAddress = :emailAddress) AND (c.loginPassword = :loginPassword)";
        TypedQuery<Customer> tq = em.createQuery(qString, Customer.class);
        tq.setParameter("emailAddress", emailAddress);
        tq.setParameter("loginPassword", loginPassword);
        Customer result = null;
        try {
            result = tq.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } finally {
            em.close();
        }
        return result;
    }

    public static List<Customer> selectCustomers() {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        String qString = "SELECT c from Customer c";
        TypedQuery<Customer> q = em.createQuery(qString, Customer.class);
        List<Customer> results = null;
        try {
            results = q.getResultList();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }

        return results;
    }

    public static boolean checkCustomerExists(String emailAddress, String loginPasswd) {
        for (Customer c : selectCustomers()) {
            if (c.getEmailAddress().equals(emailAddress) && (c.getLoginPassword().equals(loginPasswd))) {
                return true;
            }
        }
        return false;
    }

    public static void insertCustomer(Customer customer) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.persist(customer);
            tx.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close();
        }
    }

    public static void updateCustomer(Customer customer) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.merge(customer);
            tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            tx.rollback();
        } finally {
            em.close();
        }
    }
}
