package com.autumncode.model;


import com.autumncode.db.SessionManager;
import com.autumncode.exonity.Customer1;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class ExonityModelTest {
    public void doWithSession(Consumer<Session> command) {
        try (Session session = getSession()) {
            Transaction tx = session.beginTransaction();

            command.accept(session);
            if (tx.isActive() &&
                    !tx.getRollbackOnly()) {
                tx.commit();
            } else {
                tx.rollback();
            }
        }
    }

    private Session getSession() {
        return SessionManager.openSession();
    }

    @Test
    public void modelTest() {
        Long id[]={0L};
        doWithSession(session -> {
            Customer1 c = new Customer1();
            c.setName("customer 1");
            session.save(c);
            id[0]=c.getId();
        });
        doWithSession(session -> {
           Customer1 c=session.find(Customer1.class, id[0]);
           c.addDocument("document1");
        });
        doWithSession(session -> {
            Customer1 c=session.find(Customer1.class, id[0]);
            System.out.println(c);
        });
    }
}
