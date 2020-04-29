package temp;

import java.util.*;
public class Test {

    void show() {
        System.out.println("have no value");
    }

    void show(String val) {
        System.out.println("you have input "+ val);
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.show();
        test.show("whoami");
    }

}