package org.example.database;

import org.example.tables.User;
import org.hibernate.Session;

import java.util.Optional;

public class PojoBuilder {

    private static final Session session = SqlWorker.getSession();

    public static Optional<User> getUser(int id) {

        Optional<User> user = Optional.ofNullable(session.get(User.class, id));

        if (user.isEmpty())
            System.err.println("User with pk \"" + id + "\" doesn't exist");

        return user;
    }
}
