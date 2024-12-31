package MultithreadingQuestions.ParallelDataAggregationUsingStreams;

import java.util.*;
import java.util.stream.Collectors;

class Employee {
    private final String name;
    private final int age;
    private final int salary;

    public Employee(String name, int age, int salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return name + " (Age: " + age + ", Salary: " + salary + ")";
    }
}
class Department {
    private final String name;
    private final List<Employee> employees;

    public Department(String name, List<Employee> employees) {
        this.name = name;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
public class ParallelDataAggregationUsingStream2 {
    private static List<Department> generateSampleData() {
        return Arrays.asList(
                new Department("Engineering", Arrays.asList(
                        new Employee("Alice", 30, 100000),
                        new Employee("Bob", 40, 120000),
                        new Employee("Chaman", 35, 110000)
                )),
                new Department("HR", Arrays.asList(
                        new Employee("Puja", 50, 80000),
                        new Employee("Sristi", 45, 85000)
                )),
                new Department("Marketing", Arrays.asList(
                        new Employee("Sharukh", 28, 90000),
                        new Employee("Salman", 32, 95000)
                ))
        );
    }
    public static void main(String[] args) {
        List<Department> departments = generateSampleData();

        // Calculate total salary of all employees
        int totalSalary = departments.parallelStream().flatMap(department -> department.getEmployees().parallelStream()).mapToInt(Employee::getSalary).sum();

        // Calculate average salary in each department
        Map<String,Double> avgSalaryByDept = departments.parallelStream().collect(Collectors.toConcurrentMap(Department::getName,dept -> dept.getEmployees().parallelStream().mapToInt(Employee::getSalary).average().orElse(0.0)));

        // Find the oldest employee in each department

        Map<String, Optional<Employee>> oldestEmployeeByDept = departments.parallelStream().collect(
                Collectors.toConcurrentMap(
                        Department::getName, dept -> dept.getEmployees()
                                .parallelStream().sorted(Comparator.comparingInt(Employee::getAge).reversed()).findFirst()));

        // Find the highest-paid employee in the organization
        Optional<Employee> highestPaidEmployee = departments.parallelStream()
                .flatMap(department -> department.getEmployees().parallelStream()).max(Comparator.comparingInt(Employee::getSalary));

        System.out.println("Total Salary: " + totalSalary);

        System.out.println("Average Salary by Department: " + avgSalaryByDept);

        System.out.println("Oldest Employee by Department: ");
        oldestEmployeeByDept.forEach((dept, emp) ->
                System.out.println(dept + ": " + emp.orElse(null))
        );
        System.out.println("Highest Paid Employee: " + highestPaidEmployee.orElse(null));

    }
}
