class person {
    String name;
    
    person(String name) {
        this.name = name;
    }

    static void modify_name(person p , String newname) {
        p.name = newname;
    }
}

public class exp2_1{
    public static void main(String[] args) {
        person p = new person("Alice");
        System.out.println("original name :"+p.name);
        person.modify_name(p, "Bob");
        System.out.println("new name :"+p.name);
        
    }
}