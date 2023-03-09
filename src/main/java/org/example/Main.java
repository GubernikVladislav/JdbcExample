package org.example;

import org.example.dao.DocumentDao;
import org.example.dao.UserDao;
import org.example.entity.Document;
import org.example.entity.User;

public class Main {

    public static void main(String[] args) {
        User user = new User();
        user.setName("Vlad");

        UserDao userDao = new UserDao();
        userDao.create(user);

        Document document = new Document();
        document.setNumber(11);
        document.setType("Паспорт");
        document.setUser(user);

        DocumentDao documentDao = new DocumentDao();
        documentDao.create(document);

        printUser(userDao, user);

        documentDao.delete(document);

        printUser(userDao, user);
    }

    private static void printUser(UserDao userDao, User user) {
        User createdUser = userDao.getUser(user.getId());
        System.out.println(createdUser.getDocuments());
    }

}