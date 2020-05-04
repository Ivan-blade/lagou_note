package temp;

import java.util.*;
public class Person {

    private int id;

    private String name;

    public void setId(int id) {
        if(id > 0) this.id = id;
        else System.out.println("this id is invalid!");
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}