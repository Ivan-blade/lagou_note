package temp;


public class Dog extends Animal{

    private int num;

    public Dog () {};

    public Dog(String name, String color, int num) {
        super(name,color);
        setNum(num);
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int num() {
        return num;
    }

    @Override
    public void show() {
        super.show();
        System.out.println("牙齿数量为："+ num);
    }
}