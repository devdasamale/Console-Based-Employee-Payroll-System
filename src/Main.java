import java.util.ArrayList;
import java.util.Scanner;

abstract class Employee {
    String name;
    int id;
    String empType;

    Employee(String name, int id, String empType) {
        this.name = name;
        this.id = id;
        this.empType = empType;
    }

    abstract Double calculateSalary();

    @Override
    public String toString() {
        return name + "\t\t" + id + "\t\t" + calculateSalary() + "\t\t" + empType;
    }
}

class FullTimeEmployee extends Employee {
    Double monthlySalary;

    FullTimeEmployee(String name, int id, Double monthlySalary, String empType) {
        super(name, id, empType);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public Double calculateSalary() {
        return monthlySalary;
    }
}

class PartTimeEmployee extends Employee {
    int hoursWorked;
    Double hourlyRate;

    PartTimeEmployee(String name, int id, String empType,  int hoursWorked, Double hourlyRate) {
        super(name, id, empType);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public Double calculateSalary() {
        return hourlyRate * hoursWorked;
    }
}

class PayRollSystem {
    ArrayList<Employee> employeeList = new ArrayList<>();

    void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    void removeEmployee(int id) {
        employeeList.removeIf(employee -> employee.id == id);
    }

    void displayEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees in the system.");
        } else {
            System.out.println("All Employees : ");
            System.out.println("-------------------------------------------------------");
            System.out.println("Name \t  Id  \t  Salary \t Employee Type");
            System.out.println("--------------------------------------------------------");
            for (Employee employee : employeeList) {
                System.out.println(employee);
            }
            System.out.println("---------------------------------------------------------");
        }
    }
}

public class Main {

    static PayRollSystem payRollSystem = new PayRollSystem();

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin";

    public static int generateOTP() {
        return (int) (Math.random() * 9000) + 1000;
    }

    static{
        payRollSystem.addEmployee(new FullTimeEmployee("Devdas", 101, 75000.0,"Full Time" ));
        payRollSystem.addEmployee(new FullTimeEmployee("Bhumika", 102, 70000.0,"Full Time" ));
        payRollSystem.addEmployee(new PartTimeEmployee("Alex", 201,"Part Time", 80, 200.0));
        payRollSystem.addEmployee(new PartTimeEmployee("Prisha", 202,"Part Time", 54, 200.0));
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("Welcome to Employee Payroll System");
        System.out.println("------------------------------------");
        System.out.println("Enter Username: ");
        String username = scanner.next();
        System.out.println("Enter Password: ");
        String password = scanner.next();

        if(!username.equals(ADMIN_USERNAME) && !password.equals(ADMIN_PASSWORD)){
            System.out.println("Invalid credentials");
            return;
        }

        int generatedOTP = generateOTP();
        System.out.println("Your OTP is " + generatedOTP );
        System.out.println("Enter OTP");
        int otp = scanner.nextInt();
        if(generatedOTP != otp){
            System.out.println("Invalid OTP !");
            return;
        }
        System.out.println("Logged in successfully...");
        System.out.println("");


        while (true) {
            System.out.println("------ Choose Option ----------");
            System.out.println("1. Add Full-Time Employee");
            System.out.println("2. Add Part-Time Employee");
            System.out.println("3. Display Employees");
            System.out.println("4. Remove Employee");
            System.out.println("5. Exit/Logout");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Name: ");
                    String name = scanner.next();
                    System.out.print("Enter ID: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter Monthly Salary: ");
                    Double monthlySalary = scanner.nextDouble();
                    String empType = "Full Time";
                    payRollSystem.addEmployee(new FullTimeEmployee(name, id, monthlySalary,empType ));
                    System.out.println("Full Time employee added successfully...");
                    System.out.println();
                }
                case 2 -> {
                    System.out.print("Enter Name: ");
                    String name = scanner.next();
                    System.out.print("Enter ID: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter Hours Worked: ");
                    int hoursWorked = scanner.nextInt();
                    System.out.print("Enter Hourly Rate: ");
                    Double hourlyRate = scanner.nextDouble();
                    String empType = "Part Time";
                    payRollSystem.addEmployee(new PartTimeEmployee(name, id,empType, hoursWorked, hourlyRate));
                    System.out.println("Part Time employee added successfully...");
                    System.out.println();
                }
                case 3 -> payRollSystem.displayEmployees();

                case 4 -> {
                    System.out.print("Enter Employee ID to remove: ");
                    int id = scanner.nextInt();
                    payRollSystem.removeEmployee(id);
                    System.out.println("Employee removed...");
                }
                case 5 -> System.out.println("Logged out successfully...");

                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
