package com.tiaatest.assemblyline.work;

import java.util.List;
import java.util.Map;

import com.tiaatest.assemblyline.entity.AbstractEntity;
import com.tiaatest.assemblyline.entity.EmployeeType;

public interface Workers {

	void createWorkers(Map<EmployeeType, Integer> attributes);
	
	public List<AbstractEntity<EmployeeType>> getEmployeeMap(EmployeeType type);

	public void setEmployeeMap(Map<EmployeeType, List<AbstractEntity<EmployeeType>>> employeeMap) ;
	
	public int getWorkEmployeeSize(EmployeeType type);
}