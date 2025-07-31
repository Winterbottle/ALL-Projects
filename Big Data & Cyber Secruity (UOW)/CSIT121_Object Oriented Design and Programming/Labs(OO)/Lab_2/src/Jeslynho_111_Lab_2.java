//Name: Jeslyn Ho Ka Yan
//Tutorial group:121T05
//JDK version: 11.016
//Declaration: This is my work and I have not copied anyone's work

import java.util.ArrayList;
import java.util.Random;

class Point {
    private int x;
    private int y;


    // Default constructor, initializes with random coordinates
    public Point() {
        Random random = new Random();
        int min = -100;
        int max = 100; // 101 because nextInt is exclusive on the upper bound
        this.x = random.nextInt(max - min) + min;
        this.y = random.nextInt(max - min) + min;
    }

    //constructor
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    //copy Constructor
    public Point(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    // Accessor methods
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    // mutator methods
    public void setPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

//Caculate Distance
    public double distance(Point p) {
        double dx = this.x - p.x;
        double dy = this.y - p.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // To make the distance method more readable, provide an alias for it.
    public double getDistance(Point p) {
        return distance(p);
    }


    public String toString() {
        return String.format("Point(%d, %d)",x,y);
    }
}
//===========================================================================


class Line{
    private Point p1;
    private Point p2;

    //constructor
    public Line(Point p1, Point p2){
        this.p1=p1;
        this.p2=p2;
    }

    // Accessor methods
    public Point getP1(){
        return p1;
    }

    public Point getP2(){
        return p2;
    }
    // mutator methods
    public void setLine(Point p1, Point p2){
        this.p1=p1;
        this.p2=p2;
    }

    // Copy constructor
    public Line(Line aLine) {
        this.p1 = new Point(aLine.p1);
        this.p2 = new Point(aLine.p2);
    }
    // Method to calculate and return the distance
    public double getDis() {
        return p1.getDistance(p2);
    }

    public String toString() {
        return String.format("%nPoint1:%s%nPoint2:%s%nLine(%s, %s, Distance = %.4f)",p1,p2,p1 ,p2,getDis());

    }

}


//===========================================================================
public class Jeslynho_111_Lab_2 {


    public static void main(String[] args) {
        System.out.println("----------------------");
        ArrayList<Line> lines= new ArrayList<Line>();
        for(int i=0; i<5;i++) {
            Point point1 = new Point();
            Point point2 = new Point();

            Line line = new Line(point1, point2);
            lines.add(line);
        }

        for (int i = 0; i < lines.size(); i++) {
            System.out.println("Attempt" + (i) +lines.get(i) );
            System.out.println("----------------------");
        }


    }
}







