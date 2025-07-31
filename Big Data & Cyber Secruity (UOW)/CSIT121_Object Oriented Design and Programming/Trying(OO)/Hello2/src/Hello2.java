
//Name: Jeslyn Ho Ka Yan
//Tutorial group:121T05
//JDK version: 11.016
//Declaration: This is my work and I have not copied anyone's work, I am willing to accept whatever penalty given to me.



import java.util.ArrayList;
import java.util.Random;


public class Hello2 {
    private static int getint() {
        int randomValue;
        do {
            randomValue = (int) (Math.random() * 10);     //get a random integer between 0 and 9
        } while (randomValue == 0);
        return randomValue;
    }

    private static double getDouble() {         // get a random integer between 0 and 100
        double randomDouble;
        do {
            randomDouble = (double) (Math.random() * 10);     //get a random integer between 0 and 9
        } while (randomDouble == 0.0);
        return randomDouble;
    }

    private static double getDoublePec() {         // get a random integer between 0 and 100
        return (double) (Math.random() * 100);
    }

    private static ShapeColor getColor() {      //get a random ShapeColor from the enum
        int randomIndex = new Random().nextInt(ShapeColor.values().length);
        return ShapeColor.values()[randomIndex];
    }

    private static boolean isTriangle (int a, int b, int c) {   //check if three integers can form a valid triangle
        return (a + b > c) && (b + c > a) && (c + a > b);
    }

    private static TwoD getTwoD() {
        int randomType = new Random().nextInt(4); // Generate a new random type for each shape

        int a = getint();
        int b = getint();
        int c = getint();
        int d = getint();
        int h = getint();
        ShapeColor sc = getColor();

        switch (randomType)
        {
            case 0:
                return new Circle(sc, a);
            case 1:
                return new Square(sc, a);
            case 2:
                return new Rectangle(sc, a, b);
            case 3:
                if (isTriangle(a, b, c)) {
                    return new Triangle(sc, a, b, c);
                }
            case 4:
                return new Trapezoid(sc, a, b,c,d,h);
            default:
                // Handle other cases or return null
                break;
        }
        return null;
    }

    private static ThreeD getThreeD() {
        int randomType = new Random().nextInt(3); // Generate a new random type for each shape

        double a = getDouble();
        ShapeColor sc = getColor();

        switch (randomType)
        {
            case 0:
                return new Tetrahedron(sc, a);

            case 1:
                return new Cube(sc, a);

            default:
                return new Sphere(sc, a);
        }
    }
    private static void process2DShape(Shape shape) {
        // Generate a new random color
        ShapeColor newColor = getColor();

        // Recolor the shape with the new color
        ((ForTwoD) shape).recolor(newColor);
    }


    private static void process3DShape(Shape shape) {
        // Generate a new random resize
        double percentage = getDoublePec();
        ((ForThreeD) shape).resize(percentage);

        System.out.println(String.format("Size reduced by %.3f%%: %s (%s,%.3f)",
                percentage, shape.getClass().getSimpleName(), shape.getShapeColor(), ((ThreeD) shape).getA() ));


    }




    private static void displayList(ArrayList<Shape> alist) {
        int shapeCount = 1;
        for (Shape shape : alist) {
            if (shape != null) {

                System.out.print("Shape " + shapeCount +": ");

                if (shape instanceof Circle) {

                    System.out.println(shape.getClass().getName() + " (2D (" +shape.getShapeColor() + "," + ((TwoD) shape).getA()+"))");
                    process2DShape(shape);
                    System.out.print(shape);
                }
                else if (shape instanceof Rectangle) {

                    System.out.println(shape.getClass().getName() + " (2D (" +shape.getShapeColor() + "," + ((TwoD) shape).getA()+","+ ((TwoD) shape).getB()+"))");
                    process2DShape(shape);
                    System.out.print(shape);
                }
                else if (shape instanceof Square) {

                    System.out.println(shape.getClass().getName() + " (Rectangle (2D (" +shape.getShapeColor() + "," + ((TwoD) shape).getA()+"))");
                    process2DShape(shape);
                    System.out.print(shape);
                }
                else if (shape instanceof Triangle) {
                    System.out.println(shape.getClass().getName() + "(2D (" + shape.getShapeColor() + "," + ((TwoD) shape).getA() + "," +  ((TwoD) shape).getB()+ "," +  ((TwoD) shape).getC()+"))");
                    process2DShape(shape);
                    System.out.print(shape);
                }
                else if (shape instanceof Trapezoid) {
                    System.out.println(shape.getClass().getName() + "(2D (" + shape.getShapeColor() + "," + ((TwoD) shape).getA() + "," +  ((TwoD) shape).getB()+ "," +  ((TwoD) shape).getC()+ "," +  ((TwoD) shape).getD()+ "," + ((Trapezoid) shape).getH()+"))");
                    process2DShape(shape);
                    System.out.print(shape);
                }
                else if (shape instanceof Tetrahedron) {

                    System.out.println(shape.getClass().getName() + " (3D (" + shape.getShapeColor() + "," + String.format("%.3f", ((ThreeD) shape).getA()) + "))");
                    System.out.print(shape);
                    process3DShape(shape);
                    System.out.print(((Tetrahedron)shape).toString(true));

                }
                else if (shape instanceof Cube) {

                    System.out.println(shape.getClass().getName() + " (3D (" + shape.getShapeColor() + "," + String.format("%.3f", ((ThreeD) shape).getA()) + "))");
                    System.out.print(shape);
                    process3DShape(shape);
                    System.out.print(((Cube)shape).toString(true));

                }
                else if (shape instanceof Sphere) {

                    System.out.println(shape.getClass().getName() + " (3D (" + shape.getShapeColor() + "," + String.format("%.3f", ((ThreeD) shape).getA()) + "))");
                    System.out.print(shape);
                    process3DShape(shape);
                    System.out.print(((Sphere)shape).toString(true));

                }


                System.out.println("=============================");
                shapeCount++;
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<Shape> shapeList = new ArrayList<>();

        int k = 1; // so that at least 1 shape is printed
        while (k>0)
        {
            if (k==1)
                shapeList.add(getTwoD());
            else
                shapeList.add(getThreeD());
            k = new Random().nextInt(3);

            //System.out.println(String.format("K=%d",k));
        }

        displayList(shapeList);
    }
}
//============================================================
enum ShapeColor {
    Blue("blue"),
    Yellow("yellow"),
    Red("red"),
    Green("green"),
    White("white");

    private final String color;

    ShapeColor(String color) {
        this.color = color;
    }

    public String getcolor() {
        return color;
    }
}
//============================================================
interface ForTwoD {
    double perimeter();
    void recolor(ShapeColor sc);
}

interface Shape {
    double area();
    ShapeColor getShapeColor(); // Added method to get shape color
}

interface ForThreeD {
    double volume();
    void resize(double percentage);
}

//============================================================
abstract class TwoD implements ForTwoD, Shape {
    protected ShapeColor sc;
    protected int a;
    protected int b;
    protected int c;
    protected int d;


    public TwoD() {
        // Default constructor
        this.a=0;
        this.b=0;
        this.c=0;
        this.d=0;
    }

    public TwoD(ShapeColor sc, int a) {	// copy constructor
        this.sc = sc;
        this.a = a;
    }
    public TwoD(ShapeColor sc, int a, int b) {	// copy constructor
        this.sc = sc;
        this.a = a;
        this.b = b;
    }
    public TwoD(ShapeColor sc, int a, int b, int c) {	// copy constructor
        this.sc = sc;
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public TwoD(ShapeColor sc, int a, int b, int c, int d) {	// copy constructor
        this.sc = sc;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }


    public int getA() {	// accessor methods
        return a;
    }

    public int getB() {	// accessor methods
        return b;
    }

    public int getC() {	// accessor methods
        return c;
    }

    public int getD() {	// accessor methods
        return d;
    }
    public ShapeColor getShapeColor() {
        return sc;
    }

    public void Set(ShapeColor sc, int a ){ 	// mutator method
        this.sc = sc;
        this.a = a;
    }

    public void Set(ShapeColor sc, int a, int b){ 	// mutator method
        this.sc = sc;
        this.a = a;
        this.b = b;
    }
    public void Set(ShapeColor sc, int a, int b, int c){ 	// mutator method
        this.sc = sc;
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public void Set(ShapeColor sc, int a, int b, int c, int d ){ 	// mutator method
        this.sc = sc;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public void recolor(ShapeColor sc){
        this.sc  = sc;
    }

    public String toString(){
        return String.format ("%s (2D (%s,%d))%n", getClass().getSimpleName(),sc,a);
    }



}
//============================================================

class Circle extends TwoD {


    public Circle() {
        super();
    }

    public Circle(ShapeColor sc, int radius) {
        super(sc, radius);
    }

    public Circle(Circle c) {
        super(c.sc, c.a); // Use the objectRandomColor from the original Circle

    }

    public double area() {
        return Math.PI * getRadius() * getRadius();
    }

    public double perimeter() {
        return 2 * Math.PI * getRadius();
    }

    public int getRadius() {
        return getA();
    }
    public void Set(ShapeColor sc, int radius  ){ 	// mutator method
        this.sc = sc;
        this.a = radius;
    }

    public String toString() {
        return  String.format( "Updated color: %s%nArea: %.3f%nParameter: %.3f%nI am a %s shape with color changed to %s%n",this.sc,this.area(),perimeter(), this.getClass(), this.sc );

    }
}

//============================================================
class Square extends TwoD {


    public Square() {
        super();
    }

    public Square(ShapeColor sc, int length) {
        super(sc, length);
    }

    public Square(Square sq) {
        super(sq.sc, sq.a); // Use the objectRandomColor from the original Circle

    }

    public double area() {
        return super.getA() * super.getA();
    }

    public double perimeter() {
        return 4*super.getA();
    }

    public int getLength() {
        return super.getA();
    }
    public void Set(ShapeColor sc, int length  ){ 	// mutator method
        super.sc = sc;
        super.a = length;
    }

    public String toString() {
        return  String.format( "Updated color: %s%nArea: %.3f%nParameter: %.3f%nI am a %s shape with color changed to %s%n",this.sc,this.area(),perimeter(), new String("Rectangle"), this.sc );

    }
}

//============================================================
class Rectangle extends Square {


    public Rectangle() {
        super();
    }

    public Rectangle(ShapeColor sc, int length) {
        super(sc, length);
    }

    public Rectangle(ShapeColor sc, int length, int width) {
        super(sc, length);
        super.b=width;

    }

    public Rectangle(Rectangle r) {
        super(r.sc, r.a); // Use the objectRandomColor from the original Circle
        super.b=r.getB();

    }

    public double area() {
        return super.getA() * super.getB() ;
    }

    public double perimeter() {
        return 2*super.getA() + 2*super.getB();
    }

    public int getWidth() {
        return super.getB();
    }
    public void Set(ShapeColor sc, int length, int width  ){ 	// mutator method
        super.sc = sc;
        super.a = length;
        super.b = width;
    }

    public String toString() {
        return  String.format( "Updated color: %s%nArea: %.3f%nParameter: %.3f%nI am a %s shape with color changed to %s%n",this.sc,this.area(),perimeter(), this.getClass().getName(), this.sc );

    }
}
//=========================================================
class Triangle extends TwoD {

    public Triangle() {
        super();
    }

    public Triangle(ShapeColor sc, int a, int b, int c) {
        super(sc, a, b, c);
    }

    public Triangle(Triangle t) {
        super(t.sc,t.a,t.b,t.c); // Use the objectRandomColor from the original Circle

    }

    public double area() {
        double s = (getA() + getB() + getC()) / 2.0;
        return Math.sqrt(s * (s - getA()) * (s - getB()) * (s - getC()));
    }

    public double perimeter() {
        return getA() + getB() + getC();
    }

    public int getA() {
        return a;
    }
    public int getB() {
        return b;
    }
    public int getC() {
        return c;
    }

    public void Set(ShapeColor sc, int a, int b, int c){ 	// mutator method
        this.sc = sc;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public String toString() {
        return  String.format( "Updated color: %s%nArea: %.3f%nParameter: %.3f%nI am a %s shape with color changed to %s%n",this.sc,this.area(),perimeter(), this.getClass(), this.sc );

    }
}


//=========================================================
class Trapezoid extends TwoD {
    private int h;

    public Trapezoid() {
        super();
    }

    public Trapezoid(ShapeColor sc, int a, int b, int c, int d, int h) {
        super(sc, a, b, c,d);
        this.h=h;
    }

    public Trapezoid(Triangle t) {
        super(t.sc,t.a,t.b,t.c,t.d); // Use the objectRandomColor from the original Circle
    }

    public double area() {
        return 0.5 * (a + b) * h;
    }

    public double perimeter() {
        return a + b + c + d;
    }

    public int getA() {
        return a;
    }
    public int getB() {
        return b;
    }
    public int getC() {
        return c;
    }

    public int getD() {
        return d;
    }

    public int getH() {
        return h;
    }

    public void Set(ShapeColor sc, int a, int b, int c, int d, int h){ 	// mutator method
        this.sc = sc;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.h = h;

    }

    public String toString() {
        return  String.format( "Updated color: %s%nArea: %.3f%nParameter: %.3f%nI am a %s shape with color changed to %s%n",this.sc,this.area(),perimeter(), this.getClass(), this.sc );

    }
}

//============================================================
abstract class ThreeD implements ForThreeD, Shape {
    protected ShapeColor sc;
    protected double a;


    public ThreeD() {
        // Default constructor
    }

    public ThreeD(ShapeColor sc, double a) {	// copy constructor
        this.sc = sc;
        this.a = a;
    }

    public ThreeD(ThreeD td) {
        this.sc = td.sc;
        this.a = td.a;
    }

    public double getA() {	// accessor methods
        return a;
    }

    public ShapeColor getShapeColor() { // Newly added
        return sc;
    }

    public void Set(ShapeColor sc, double a ){ 	// mutator method
        this.sc = sc;
        this.a = a;
    }

    public void resize(double percentage){
        this.a  = a * (1-percentage/100) ;
    }

    public String toString(){

        return String.format ("%s (3D (%s,%.3f))%n", getClass().getSimpleName(),sc,a);
    }

}
//============================================================
class Tetrahedron extends ThreeD {

    public Tetrahedron() {
        super();
    }

    public Tetrahedron(ShapeColor sc, double a) {
        super(sc, a);
    }

    public Tetrahedron(Tetrahedron t) {
        super(t);
    }

    public double area() {
        return Math.sqrt(3) * getA() * getA();
    }

    public double volume() {
        return (Math.pow(getA(), 3)) / (6 * Math.sqrt(2));
    }

    public double getA() {
        return super.getA();
    }
    public void Set(ShapeColor sc, double a ){ 	// mutator method
        this.sc = sc;
        this.a = a;
    }

    public String toString() {
        return  String.format( "Surface area = %.3f%nVolume = %.3f%n",this.area(),this.volume());

    }

    public String toString(boolean updated){ //Additional to print out the word Updated

        if (!updated)
            return String.format( "Surface area = %.3f%nVolume = %.3f%n",this.area(),this.volume());
        else
            return String.format( "Updated surface area = %.3f%nUpdated volume = %.3f%nI am a %s shape%n",this.area(),this.volume(),this.getClass().getName());
    }
}

//============================================================
class Cube extends ThreeD {

    public Cube() {
        super();
    }

    public Cube(ShapeColor sc, double a) {
        super(sc, a);
    }

    public Cube(Cube c) {
        super(c);
    }

    public double area() {
        return 6 * getA() * getA();
    }

    public double volume() {
        return getA() * getA()* getA();
    }

    public double getA() {
        return super.getA();
    }
    public void Set(ShapeColor sc, double a ){ 	// mutator method
        this.sc = sc;
        this.a = a;
    }

    public String toString() {
        return  String.format( "Surface area = %.3f%nVolume = %.3f%n",this.area(),this.volume());

    }

    public String toString(boolean updated){ //Additional to print out the word Updated

        if (!updated)
            return String.format( "Surface area = %.3f%nVolume = %.3f%n",this.area(),this.volume());
        else
            return String.format( "Updated surface area = %.3f%nUpdated volume = %.3f%nI am a %s shape%n",this.area(),this.volume(),this.getClass().getName());
    }
}
//============================================================

class Sphere extends ThreeD {

    public Sphere() {
        super();
    }

    public Sphere(ShapeColor sc, double a) {
        super(sc, a);
    }

    public Sphere(Sphere s) {
        super(s);
    }

    public double area() {
        return 4 * Math.PI * Math.pow(getA(), 2);
    }

    public double volume() {
        return (4.0 / 3.0) * Math.PI * Math.pow(getA(), 3);
    }

    public double getA() {
        return super.getA();
    }
    public void Set(ShapeColor sc, double a ){ 	// mutator method
        this.sc = sc;
        this.a = a;
    }

    public String toString() {
        return  String.format( "Surface area = %.3f%nVolume = %.3f%n",this.area(),this.volume());

    }

    public String toString(boolean updated){ //Additional to print out the word Updated

        if (!updated)
            return String.format( "Surface area = %.3f%nVolume = %.3f%n",this.area(),this.volume());
        else
            return String.format( "Updated surface area = %.3f%nUpdated volume = %.3f%nI am a %s shape%n",this.area(),this.volume(),this.getClass().getName());
    }
}
//============================================================











