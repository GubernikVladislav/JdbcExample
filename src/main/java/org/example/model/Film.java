package org.example.model;

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
     * Идентификатор режисера
     * <p>
     * {@link Director}
     */
    private Integer director;

    public Film(String title) {
        this.title = title;
    }

    public Film(String title, Integer director) {
        this.title = title;
        this.director = director;
    }

    public Film(int id, String title, Integer director) {
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

    public Integer getDirector() {
        return director;
    }

    public void setDirector(Integer director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", director=" + director +
                '}';
    }
}
