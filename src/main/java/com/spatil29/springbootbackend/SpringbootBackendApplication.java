package com.spatil29.springbootbackend;

import com.spatil29.springbootbackend.model.Employee;
import com.spatil29.springbootbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBackendApplication.class, args);
	}

	@Autowired
	private EmployeeRepository employeeRepository;
	@Override
	public void run(String... args) throws Exception {
		//object will get saved in db
		Employee employee = new Employee();
		employee.setFirstName("Ramesh");
		employee.setLastName("Pandit");
		employee.setEmailId("ramesh@gmail.com");
		employeeRepository.save(employee);

		Employee employee1 = new Employee();
		employee1.setFirstName("John");
		employee1.setLastName("Doe");
		employee1.setEmailId("johndoe@gmail.com");
		employeeRepository.save(employee1);

		Employee employee2 = new Employee();
		employee2.setFirstName("Jane");
		employee2.setLastName("Doe");
		employee2.setEmailId("janedoe@gmail.com");
		employeeRepository.save(employee2);

		Employee employee3 = new Employee();
		employee3.setFirstName("Rohit");
		employee3.setLastName("Joshi");
		employee3.setEmailId("rohit@gmail.com");
		employeeRepository.save(employee3);

	}
}
