package com.tiaatest.assemblyline.factory;

import java.util.concurrent.atomic.AtomicInteger;

import com.tiaatest.assemblyline.entity.Employee;
import com.tiaatest.assemblyline.entity.EmployeeType;

public class EmployeeFactory {

	static AtomicInteger i = new AtomicInteger(0);
	
	public static Employee createEntity(EmployeeType type) {
		
		Employee emp = null;
		
		if(type.equals(EmployeeType.LINEWORKER)) {
			emp =  new Employee(i.incrementAndGet(), EmployeeType.LINEWORKER);			
		}else if(type.equals(EmployeeType.SUPERVISOR)) {
			emp =  new Employee(i.incrementAndGet(), EmployeeType.SUPERVISOR);			
		}else if(type.equals(EmployeeType.INVENTORYMANGER)) {
			emp =  new Employee(i.incrementAndGet(), EmployeeType.INVENTORYMANGER);			
		}
				
		return emp;
	}
}
