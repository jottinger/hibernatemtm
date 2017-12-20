package com.autumncode.model;


import com.autumncode.db.SessionManager;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;
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
            A a = new A();
            a.setValue("a value");
            session.save(a);
        });
        doWithSession(session -> {
            Query<A> query = session.createQuery("from A a", A.class);
            System.out.println(query.list());
        });
    }

    @Test
    public void removeFromSetTest() {
        doWithSession(session -> {
            Query<C> query = session.createQuery("from C a", C.class);
            for (C c : query.list()) {
                session.remove(c);
            }
        });
        doWithSession(session -> {
            C c = new C();
            c.setValue("C1");
            c.setDSet(new HashSet<>());
            for (int i = 1; i < 10; i++) {
                D d = new D();
                d.setValue("D" + i);
                session.save(d);
                c.getDSet().add(d);
            }
            session.save(c);
        });
        doWithSession(session -> {
            Query<C> query = session.createQuery("from C a", C.class);
            System.err.println(query.list());
        });
        doWithSession(session -> {
            Query<C> query = session.createQuery("from C a", C.class);
            C c = query.getSingleResult();
            System.err.println(c);
            Set<D> ds = c.getDSet();
            D dToRemove = new D();
            dToRemove.setId(3);
            dToRemove.setValue("D3");
            ds.remove(dToRemove);
        });

        doWithSession(session -> {
            Query<C> query = session.createQuery("from C a", C.class);
            System.err.println(query.list());
        });
    }
}
