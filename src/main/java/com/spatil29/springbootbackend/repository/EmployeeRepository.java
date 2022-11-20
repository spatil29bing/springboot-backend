package com.spatil29.springbootbackend.repository;

import com.spatil29.springbootbackend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
/*Creating Spring Data JPARepository. no need to add @Repository annotation as it will be taken care by JpaRepository*/

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    //all CRUD methods are written here
}
