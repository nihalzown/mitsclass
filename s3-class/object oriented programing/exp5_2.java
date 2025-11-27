public class exp5_2 {

    public static void main(String[] args) {
        try {
            loadClass("anything");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        }

        try {
            checkNumber(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument: " + e.getMessage());
        }
    }

    public static void loadClass(String className) throws ClassNotFoundException {
        Class.forName(className);
    }

    public static void checkNumber(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Number must be non-negative");
        }
    }
}