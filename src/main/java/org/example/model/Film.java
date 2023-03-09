package org.example.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Фильм
 */
public class Film {

    /**
     * Идентификатор
     */
    private int id;

    /**
     * Название фильма
     */
    private String title;

    /**
     * Режиссёр
     */
    private Director director;

    /**
     * Список актеров
     */
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
        this.id = id;
        this.title = title;
        this.director = director;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void addActor(Actor actor) {
        getActors().add(actor);
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", director=" + director +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return id == film.id && Objects.equals(title, film.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
