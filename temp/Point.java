package temp;

public class Point {

    int row;
    int col;

    public void show() {
        System.out.println("row is "+row+" col is "+ col);
    }

    public void setRow(int row) {
        this.row = row;
    }

    public static void main(String[] args) {

        Point point = new Point();

        point.row = 3;
        point.col = 5;

        System.out.println("row is "+point.row+" col is "+point.col);
    }

}