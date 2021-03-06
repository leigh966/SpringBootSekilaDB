package org.tsi.leigh.demo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.aspectj.weaver.ast.Not;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "actor")
public class Actor implements Serializable
{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int actor_id;

    private String first_name;
    private String last_name;

    @ManyToMany(mappedBy = "actor", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Film> films = new HashSet<>();

    public Actor(String first_name, String last_name)
            throws NotSavedException
    {
        setFirst_name(first_name);
        setLast_name(last_name);
    }

    public Actor(){}

    public void setActor_id(int actor_id) {
        this.actor_id = actor_id;
    }


    public String getLast_name()
    {
        return last_name;
    }

    public void setLast_name(String last_name)
            throws NotSavedException
    {
        final int MAX_LAST_NAME_LENGTH = 30;
        if(last_name.length() < 1) throw new NotSavedException("cannot set last name to nothing");
        if(last_name.length() > MAX_LAST_NAME_LENGTH) throw new NotSavedException("last_name too long");
        else {
            this.last_name = last_name.toUpperCase();
        }
    }

    public String getFirst_name()
    {
        return first_name;
    }

    public void setFirst_name(String first_name)
            throws NotSavedException
    {
        final int MAX_FIRST_NAME_LENGTH = 20;
        if(first_name.length() < 1) throw new NotSavedException("Cannot set first name to nothing");
        if(first_name.length() > MAX_FIRST_NAME_LENGTH) throw new NotSavedException("first_name too long");
        else {
            this.first_name = first_name.toUpperCase();
        }
    }

    public Set<Film> getFilms()
    {
        return films;
    }

    public void setFilms(Set<Film> films)
    {
        this.films = films;
    }

    public int getActor_id()
    {
        return actor_id;
    }

}
