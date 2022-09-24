package pl.org.smartsolution;

import jakarta.persistence.*;

@Entity(name = "tuser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //point primary key
    private int id;
    private String name;
    //@Column (name="nazwisko") ; zmianna nawzy kolumny
    private String surename;

    @Enumerated(EnumType.STRING)
    // Enum made of String
    private Sex sex;

    public User(int id, String name, String surename, Sex sex) {
        this.id = id;
        this.name = name;
        this.surename = surename;
        this.sex = sex;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public enum Sex{
        MEN,
        WOMEN,
        CHILD
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surename='" + surename + '\'' +
                ", sex=" + sex +
                '}'+"\n";
    }
}
