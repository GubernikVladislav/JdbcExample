package org.example.model;

/**
 * Связь фильма с актёром
 */
public class ActorFilm {

    private ActorFilmId id;

    public ActorFilm() {
    }

    public ActorFilm(ActorFilmId id) {
        this.id = id;
    }

    public ActorFilmId getId() {
        return id;
    }

    public void setId(ActorFilmId id) {
        this.id = id;
    }
}
