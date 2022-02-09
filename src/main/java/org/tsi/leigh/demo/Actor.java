package org.tsi.leigh.demo;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Actor
{

    @Id
    @GeneratedValue
    private int actor_id;

    private String first_name;
    private String last_name;

    public Actor(String first_name, String last_name)
    {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Actor(){}


    public String getLast_name()
    {
        return last_name;
    }

    public void getLast_name(String last_name)
    {
        this.last_name = last_name;
    }

    public String getFirst_name()
    {
        return first_name;
    }

    public void setFirst_name(String first_name)
    {
        this.first_name = first_name;
    }
}
