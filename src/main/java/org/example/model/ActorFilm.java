package org.example.model;

/**
 * Связь актеров с фильмами
 */
public class ActorFilm {

    /**
     * Идентификатор актера
     * <p>
     * {@link Actor}
     */
    private int actor;

    /**
     * Идентификатор фильма
     * <p>
     * {@link Film}
     */
    private int film;

    public ActorFilm(int actor, int film) {
        this.actor = actor;
        this.film = film;
    }

    public int getActor() {
        return actor;
    }

    public void setActor(int actor) {
        this.actor = actor;
    }

    public int getFilm() {
        return film;
    }

    public void setFilm(int film) {
        this.film = film;
    }

    @Override
    public String toString() {
        return "ActorFilm{" +
                "actor=" + actor +
                ", film=" + film +
                '}';
    }
}
