package org.tsi.leigh.demo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "film")
public class Film implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int film_id;

    private String title;
    private String description;
    private int release_year;
    private int language_id;
    @ManyToOne
    @JoinColumn(name = "language_id", insertable = false, updatable = false)
    private Language language;

    private Integer original_language_id;
    @ManyToOne
    @JoinColumn(name = "original_language_id", insertable = false, updatable = false)
    private Language original_language;

    private int rental_duration;
    private float rental_rate;
    private Integer length;
    private float replacement_cost;
    private String rating;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "film_actor",
            joinColumns = {
                    @JoinColumn(name = "film_id", referencedColumnName = "film_id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "actor_id", referencedColumnName = "actor_id",
                            nullable = false, updatable = false)})
    private Set<Actor> actor = new HashSet<>();


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "film_category",
            joinColumns = {
                    @JoinColumn(name = "film_id", referencedColumnName = "film_id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "category_id", referencedColumnName = "category_id",
                            nullable = false, updatable = false)})
    private Set<Category> category = new HashSet<>();

    public Film(){}

    public Film
            (
                    String title,
                    String description,
                    int language_id,
                    Integer original_language_id,
                    int rental_duration,
                    float rental_rate,
                    Integer length,
                    float replacement_cost,
                    String rating
           )
    {
        this.title = title;
        this.description = description;
        this.language_id = language_id;
        this.original_language_id = original_language_id;
        this.rental_duration = rental_duration;
        this.rental_rate = rental_rate;
        this.length = length;
        this.replacement_cost = replacement_cost;
        this.rating = rating;
    }

    public int getFilm_id()
    {
        return film_id;
    }

    public void setFilm_id(int film_id)
    {
        this.film_id = film_id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getRelease_year()
    {
        return release_year;
    }

    public void setRelease_year(int release_year)
    {
        this.release_year = release_year;
    }

    public int getLanguage_id()
    {
        return language_id;
    }

    public void setLanguage_id(int language_id)
    {
        this.language_id = language_id;
    }

    public Integer getOriginal_language_id()
    {
        return original_language_id;
    }

    public void setOriginal_language_id(Integer original_language_id)
    {
        this.original_language_id = original_language_id;
    }

    public int getRental_duration()
    {
        return rental_duration;
    }

    public void setRental_duration(int rental_duration)
    {
        this.rental_duration = rental_duration;
    }

    public float getRental_rate()
    {
        return rental_rate;
    }

    public void setRental_rate(float rental_rate)
    {
        this.rental_rate = rental_rate;
    }

    public Integer getLength()
    {
        return length;
    }

    public void setLength(Integer length)
    {
        this.length = length;
    }

    public float getReplacement_cost()
    {
        return replacement_cost;
    }

    public void setReplacement_cost(float replacement_cost)
    {
        this.replacement_cost = replacement_cost;
    }

    public String getRating()
    {
        return rating;
    }

    public void setRating(String rating)
    {
        this.rating = rating;
    }

    public Set<Actor> getActor()
    {
        return actor;
    }

    public void setActor(Set<Actor> actor)
    {
        this.actor = actor;
    }

    public Language getOriginal_language()
    {
        return original_language;
    }

    public void setOriginal_language(Language original_language)
    {
        this.original_language = original_language;
    }

    public Language getLanguage()
    {
        return language;
    }

    public void setLanguage(Language language)
    {
        this.language = language;
    }

    public Set<Category> getCategory()
    {
        return category;
    }

    public void setCategory(Set<Category> category)
    {
        this.category = category;
    }
}
