package org.example.model;

import java.io.Serializable;

public class ActorFilmId implements Serializable {

    private Actor actor;

    private Film film;

    public ActorFilmId() {
    }

    public ActorFilmId(Actor actor, Film film) {
        this.actor = actor;
        this.film = film;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
