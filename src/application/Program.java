package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import model.entities.Employee;

public class Program {
	
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		
		System.out.print("Enter full file path: ");
		String strPah = sc.nextLine();
		
		System.out.print("Enter salary: ");
		double salaryMin = sc.nextDouble();
		sc.nextLine();
		
		try (BufferedReader br = new BufferedReader(new FileReader(strPah))){
			
			List<Employee> list = new ArrayList<Employee>();
			String line = br.readLine();
			
			while (line != null) {
				String[] fields = line.split(",");
				String name = fields[0];
				String email = fields[1];
				Double salary = Double.parseDouble(fields[2]);
				list.add(new Employee(name, email, salary));
				
				
				line = br.readLine();
			}			
			
			List<String> email = list.stream()
					.filter(x -> x.getSalary() > salaryMin)
					.map(x -> x.getEmail())
					.sorted()
					.collect(Collectors.toList());
			
			email.forEach(System.out::println);
			
			Double salaryName =  list.stream()
					.filter(x -> x.getName().charAt(0) == 'M')
					.map(x -> x.getSalary())
					.reduce(0.0, (x,y) -> x + y);
			
			System.out.println("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", salaryName) );
			
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		sc.close();
		
	}

}
