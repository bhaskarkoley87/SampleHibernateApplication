package com.bhk.app;

import java.util.ArrayList;
import java.util.List;

import com.bhk.dao.StudentDAO;
import com.bhk.entity.Address;
import com.bhk.entity.Student;

public class Application {

	public static void main(String[] args) throws Exception{
		Address address1 = new Address("ABC Street", "Opp. xyz", "Kolkata", "West Bengal", "123810");	
		List<Address> addressList = new ArrayList<Address>();
		addressList.add(address1);
		Student student = new Student(1l, "Bhaskar", "koley", addressList);
		StudentDAO studentDAO = new StudentDAO();
		studentDAO.save(student);

	}

}
