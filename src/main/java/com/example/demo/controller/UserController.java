package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.repository.UserRepository;
@CrossOrigin(origins ="http://localhost:3000")
@RestController
public class UserController {
	
	@Autowired
	UserRepository user;
	
	@PostMapping("/empAdd")
	public String saveEmp(@RequestBody Employee emp) 
	{
		//System.out.println(emp.toString()+"Heyyyy");
		Employee e=user.save(emp);
		
		if(e!=null)
		{
			return "Employee Added Success......!!";
		}
		else
		{
			return "Some problem is here.....!";
		}
		
	}
	
	@GetMapping("/empDel/{id}")
	public String deleteEmp(@PathVariable int id)
	{
		
		boolean b=user.existsById(id);
		
		if(b)
		{
			user.deleteById(id);
			return "Id deleted.....!";
		}
		else
		{
			return "Id hasn't deleted....!";
		}
		
		
	}
	
	@GetMapping("/{id}")
	public Employee viewAll(@PathVariable int id)
	{
		
		return (Employee)user.findById(id).get();
	}
	
	@GetMapping("/updateEmp/{id}/{name}")
	public String updateEmp(@PathVariable("id") int id,@PathVariable("name") String name)
	{
		boolean b=user.existsById(id);
		if(b)
		{
			Employee e=user.findById(id).get();
			e.setId(id);
			e.setName(name);
			user.save(e);
			return "Employee updated....!";
			
		}
		else
		{
			return "Employee id not found...!";
		}
		
	}
	
	@GetMapping("/hii")
	public String hello(@RequestParam("id") int id)
	{
		return "hiiiiiiiii"+id;
	}
	
	@GetMapping("/viewAll")
	public List<Employee> getAllEmp()
	{
		
		return user.findAll();
	}
	
	
	
	
}
