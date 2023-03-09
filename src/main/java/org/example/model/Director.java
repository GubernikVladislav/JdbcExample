package org.example.model;

/**
 * Режисёр
 */
public class Director {

    /**
     * Идентификатор
     */
    private int id;

    /**
     * ФИО
     */
    private String name;

    public Director(String name) {
        this.name = name;
    }

    public Director(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Director{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
