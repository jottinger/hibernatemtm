package com.autumncode.model;


import com.autumncode.db.SessionManager;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.Test;

import java.util.function.Consumer;

public class ModelTest {
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
        doWithSession(session -> {
            A a=new A();
            a.setValue("a value");
            session.save(a);
        });
        doWithSession(session -> {
            Query<A> query=session.createQuery("from A a", A.class);
            System.out.println(query.list());
        });
    }
}
