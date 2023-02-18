package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner commandScanner = new Scanner(System.in);
        String command = "";

        while (!command.equals("0")) {

            if (command.equals("1")) {
                create();
            } else if (command.equals("2")) {
                getUser();
            }

            printMenu();
            command = commandScanner.nextLine();
        }
    }

    private static void create() {
        User user = new User();
        System.out.println("Введите имя:");
        Scanner nameScanner = new Scanner(System.in);
        user.setName(nameScanner.nextLine());

        System.out.println("Введите возраст:");
        Scanner ageScanner = new Scanner(System.in);
        user.setAge(ageScanner.nextInt());

        UserDao userDao = new UserDaoImpl();
        userDao.create(user);
        System.out.println("Пользователь добавлен");
    }

    private static void getUser() {

        System.out.println("Введите имя:");
        Scanner nameScanner = new Scanner(System.in);
        String name = nameScanner.nextLine();

        UserDao userDao = new UserDaoImpl();
        User user = userDao.getByName(name);
        System.out.println("Найден пользователь: " + user);
    }

    private static void printMenu() {
        System.out.println("Введите команду:");
        System.out.println("1) Добавить пользователя.");
        System.out.println("2) Получить пользователя.");
        System.out.println("0) Выход.");
    }
}