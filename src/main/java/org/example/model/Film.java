package org.example.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Фильм
 */
@Entity
@Table(name = "FILM")
public class Film extends IdentifiedEntity {

    /**
     * Название фильма
     */
    @Column(name = "TITLE")
    private String title;

    /**
     * Режиссёр
     */
    @ManyToOne
    @JoinColumn(name = "DIRECTOR_ID")
    private Director director;

    /**
     * Список актеров
     */
    @ManyToMany
    @JoinTable(
            name = "ACTOR_FILM",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private Set<Actor> actors;

    public Film() {
    }

    public Film(String title) {
        this.title = title;
    }

    public Film(String title, Director director) {
        this.title = title;
        this.director = director;
    }

    public Film(int id, String title, Director director) {
        super(id);
        this.title = title;
        this.director = director;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
        if (director != null) {
            director.getFilms().add(this);
        }
    }

    public Set<Actor> getActors() {
        if (actors == null) {
            actors = new HashSet<>();
        }
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public void removeActor(Actor actor) {
        getActors().remove(actor);
        actor.getFilms().remove(this);
    }

    @PreRemove
    public void preRemove() {
        getActors().forEach(actor -> actor.removeFilm(this));
        setActors(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Film film = (Film) o;
        return Objects.equals(title, film.title) && Objects.equals(director, film.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, director);
    }

    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", director=" + director +
                ", actors=" + actors +
                '}';
    }
}
