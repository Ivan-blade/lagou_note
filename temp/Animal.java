package temp;

import java.util.*;

import sun.net.www.content.text.plain;
public class Animal {

    private String name;

    private String color;

    public Animal() {};

    public Animal(String name,String color) {
        setName(name);
        setColor(color);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public void show () {
        System.out.println("该动物名为："+name+" 毛色：" + color);
    }
}