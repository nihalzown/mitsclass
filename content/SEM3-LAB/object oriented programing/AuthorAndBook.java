// Aim: Program to perform Abstract Class operations (AuthorAndBookTask and Book).
public class AuthorAndBook {
    private String name;
    private String email;
    private char gender;

    public AuthorAndBook(String name, String email, char gender) {
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public char getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "AuthorAndBook[name=" + name + ", email=" + email + ", gender=" + gender + "]";
    }
}

class Book {
    private String name;
    private AuthorAndBook author;
    private double price;
    private int qtyInStock;

    public Book(String name, AuthorAndBook author, double price, int qtyInStock) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.qtyInStock = qtyInStock;
    }

    public String getName() {
        return name;
    }

    public AuthorAndBook getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public int getQtyInStock() {
        return qtyInStock;
    }

    @Override
    public String toString() {
        return "Book[name=" + name + ", " + author + ", price=" + price + ", qtyInStock=" + qtyInStock + "]";
    }

    public static void main(String[] args) {
        AuthorAndBook author = new AuthorAndBook("J.K. Rowling", "jkrowling@example.com", 'F');
        Book book = new Book("Harry Potter", author, 29.99, 100);
        System.out.println(book);
    }
}