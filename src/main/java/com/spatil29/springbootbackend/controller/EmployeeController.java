package com.spatil29.springbootbackend.controller;

import com.spatil29.springbootbackend.exception.ResourceNotFoundException;
import com.spatil29.springbootbackend.model.Employee;
import com.spatil29.springbootbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*@RestController annotation are internally anotated with @Controller and @ResponseBody annotation.
As we add @RestController annotation then this class become Rest controller capable to land HTTP request.
All rest api are defined in this class
@RequestMapping used for defining common base url
 */

//ALL REST API return in controller
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @GetMapping
    public List<Employee> getAllEmployee()
    {
        return employeeRepository.findAll();
    }

    //build create employee REST API
    /*bUILDING create employee POST request. It basically handles Http Post request it get Json object from the client.
    It will convert emp JSON object to employee java object and save emp java object in db and will return emp saved obj to client
    @PostMapping : handle detected post request. no need to specipy url as it is added in base url
    @RequestBody : converts JSON into java object
     */

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee)
    {
        return employeeRepository.save(employee);
    }

    /*ResponseEntity is generic class so we need to provide type <Employee> is provided. i.e  and it is very useful for getting Response from REST API.

     */
    //build employee by Id REST API
    /*@GetMapping : added because to convert method into rest api.
    @PathVariable : used to bind value of {id} to method argument.

     */
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id)
    {
        //Fetch employee object from db.findById is a optional clause and optional clause provides orElseThrow method. if record is not existed with given ID then rest api should throw exception and rest api should return error msg to client.
        //Inorder to handle this use case orElseThrow method is used.This method creates custom exception and throws custom exception. orElseThrow method takes exceptionSupplier as argument and exceptionSupplier is a functional interface and you need to provide lambda expression implementation for this interface
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id:" + id));
        //ok method internally provides 200 status code and we just need to pass the body of method
        return ResponseEntity.ok(employee);
    }
    /*details of update employee i.e the object is  coming from client. client call REST API and sends updated emp info in the http request and that info is binded to Employee object.
    We need to get emp object from db, update the parameter that client send in that object and then save that object again to db.
    employeeDetails contains update from user.
     */
    //build update employee REST API
    /*
    Difference between @PostMapping and @PutMapping in general PUT vs POST
    POST -> creating a resource
    PUT -> updating a already created resource
    * */
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee employeeDetails)
    {
        //fetch emp obj from db
        Employee updateEmployee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee does not exist with id:" + id));
        //update info to obj
        updateEmployee.setFirstName(employeeDetails.getFirstName());
        updateEmployee.setLastName(employeeDetails.getLastName());
        updateEmployee.setEmailId(employeeDetails.getEmailId());

        //save emp obj to db after updation
        employeeRepository.save(updateEmployee);
        //return st passed updated info to client
        return ResponseEntity.ok(updateEmployee);
    }

    //delete employee rest api
    /*
    @DeleteMapping : to implement delete functionality in REST API.
    HttpCode status 204 : recieved when employee is successfully deleted
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable long id)
    {   //to check whether record exist in db or not
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Employee does not exist with id:" + id));
        //delete employee record from db
        employeeRepository.delete(employee);
        //HttpStatus.NO_CONTENT : because we are not passing anything to client

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
