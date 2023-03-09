package org.example.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Актёр
 */
public class Actor {

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
    private Set<ActorFilm> films;

    public Actor() {
    }

    public Actor(String name) {
        this.name = name;
    }

    public Actor(int id, String name) {
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

    public Set<ActorFilm> getFilms() {
        if (films == null) {
            films = new HashSet<>();
        }
        return films;
    }

    public void setFilms(Set<ActorFilm> films) {
        this.films = films;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return id == actor.id && Objects.equals(name, actor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
