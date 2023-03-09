package org.example.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    /**
     * Список фильмов
     */
    private Set<Film> films;

    public Director() {
    }

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

    public Set<Film> getFilms() {
        if (films == null) {
            films = new HashSet<>();
        }

        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    @Override
    public String toString() {
        return "Director{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Director director = (Director) o;
        return id == director.id && Objects.equals(name, director.name) && Objects.equals(films, director.films);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, films);
    }
}
