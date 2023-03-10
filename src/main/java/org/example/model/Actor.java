package org.example.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Актёр
 */
@Entity
@Table(name = "ACTOR")
public class Actor extends IdentifiedEntity {

    /**
     * ФИО
     */
    @Column(name = "NAME")
    private String name;

    /**
     * Список фильмов
     */
    @ManyToMany(mappedBy = "actors")
    private Set<Film> films;

    public Actor() {
    }

    public Actor(String name) {
        this.name = name;
    }

    public Actor(int id, String name) {
        super(id);
        this.name = name;
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

    public void removeFilm(Film film) {
        getFilms().remove(film);
        film.getActors().remove(this);
    }

    @PreRemove
    public void preRemove() {
        getFilms().forEach(film -> {
            film.removeActor(this);
            getFilms().remove(film);
        });
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Actor actor = (Actor) o;
        return Objects.equals(name, actor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
}
