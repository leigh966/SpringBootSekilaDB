package org.tsi.leigh.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "language")
public class Language implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int language_id;

    private String name;

    @OneToMany
    @JoinColumn(name = "language_id", insertable = false, updatable = false)
    @JsonIgnore
    private Set<Film> film;

    public Language(String name)
    {
        this.name = name;
    }

    public Language()
    {

    }

    public int getLanguage_id() {
        return language_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Set<Film> getFilm()
    {
        return film;
    }

    public void setFilm(Set<Film> film)
    {
        this.film = film;
    }
}
