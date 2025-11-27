import java.util.Scanner;
class Employee{
	String name, dept;
	int id;
	Employee(String name,int id,String dept){
		this.name=name;
		this.id=id;
		this.dept=dept;
	}
	void display(){
		System.out.println("Name: "+name);
		System.out.println("Id: "+id);
		System.out.println("Department: "+dept);
	}
}
class Manager extends Employee{
	String projectName;
	int numberOfTeamMembers;
	Manager(String name,int id, String dept, String projectName, int numberOfTeamMembers){
		super(name,id,dept);
		this.projectName=projectName;
		this.numberOfTeamMembers=numberOfTeamMembers;
	}
	void display(){
		System.out.println("Manager Details");
		super.display();
		System.out.println("Project Name: "+projectName);
		System.out.println("Number of team members: "+numberOfTeamMembers);
	}
}
class Intern extends Employee{
	String uni;
	int duration;
	int stipend;
	Intern(String name,int id,String dept,int stipend,String uni,int duration){
		super(name,id,dept);
		this.uni=uni;
		this.stipend=stipend;
		this.duration=duration;
	}
	void display(){
		System.out.println("Intern Details");
		super.display();
		System.out.println("University: "+uni);
		System.out.println("Duration: "+duration);
		System.out.println("Stipend: "+stipend);
	}
}
public class exp3_2{
	public static void main(String[]args){
		Scanner scan= new Scanner(System.in);
		
		System.out.print("Enter Name: ");
		String name=scan.next();
		System.out.print("Enter Id: ");
		int id=scan.nextInt();
		System.out.print("Enter Departmnet: ");
		String dept=scan.next();
		System.out.print("Enter Project Name: ");
		String projectName=scan.next();
		System.out.print("Enter number of team members: ");
		int numberOfTeamMembers=scan.nextInt();
		
		Manager obj1 = new Manager(name,id,dept,projectName,numberOfTeamMembers);
		System.out.println();
		obj1.display();
		
		System.out.println();
		
		System.out.print("Enter Name: ");
		name=scan.next();
		System.out.print("Enter Id: ");
		id=scan.nextInt();
		System.out.print("Enter Departmnet: ");
		dept=scan.next();
		System.out.print("Enter University: ");
		String uni=scan.next();
		System.out.print("Enter Duration: ");
		int duration=scan.nextInt();
		System.out.print("Enter stipend: ");
		int stipend =scan.nextInt();
		
		Intern obj2 = new Intern(name,id,dept,stipend,uni,duration);
		System.out.println();
		obj2.display();
		scan.close();
	}
}