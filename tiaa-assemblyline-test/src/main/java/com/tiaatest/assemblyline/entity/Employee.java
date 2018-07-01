package com.tiaatest.assemblyline.entity;

/**
 * 
 * @author Amol
 * 
 * Employee Class for all type of employees
 *
 */

public class Employee extends AbstractEntity<EmployeeType> {
	
	public Employee(int uuid, EmployeeType type) {
		super(uuid, type);
	}
			
}
