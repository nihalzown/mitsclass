// Aim: Program to perform Abstract Class operations (Number of Sides).
abstract class NumberOfSidesTask {
    abstract void numberOfSides();
}

class Rectangle extends NumberOfSidesTask {
    void numberOfSides() {
        System.out.println("Rectangle has 4 sides.");
    }
}

class Triangle extends NumberOfSidesTask {
    void numberOfSides() {
        System.out.println("Triangle has 3 sides.");
    }
}

class Hexagon extends NumberOfSidesTask {
    void numberOfSides() {
        System.out.println("Hexagon has 6 sides.");
    }
}

public class NumberOfSides {
    public static void main(String[] args) {
        NumberOfSidesTask rectangle = new Rectangle();
        NumberOfSidesTask triangle = new Triangle();
        NumberOfSidesTask hexagon = new Hexagon();

        rectangle.numberOfSides();
        triangle.numberOfSides();
        hexagon.numberOfSides();
    }
}