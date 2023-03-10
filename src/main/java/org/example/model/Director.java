package org.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Режисёр
 */
@Entity
@Table(name = "DIRECTOR")
public class Director extends IdentifiedEntity {

    /**
     * ФИО
     */
    @Column(name = "NAME")
    private String name;

    /**
     * Список фильмов
     */
    @OneToMany(mappedBy = "director")
    private Set<Film> films;

    public Director() {
    }

    public Director(String name) {
        this.name = name;
    }

    public Director(int id, String name) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Director director = (Director) o;
        return Objects.equals(name, director.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        return "Director{" +
                "name='" + name + '\'' +
                ", films=" + films +
                '}';
    }
}
