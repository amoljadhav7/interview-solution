package com.tiaatest.assemblyline.work;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tiaatest.assemblyline.entity.AbstractEntity;
import com.tiaatest.assemblyline.entity.EmployeeType;
import com.tiaatest.assemblyline.factory.AbstractFactory;

@Component
public class WorkersManager implements Workers {
	
		
	@Autowired
	private AbstractFactory factory ;
	
	private Map <EmployeeType,List<AbstractEntity<EmployeeType>>> employeeMap;
	
	/**
	 * Create worker Employees
	 */
	@Override
	public void createWorkers(Map<EmployeeType,Integer> attributes) {
				System.out.println("Create workers");
				employeeMap = new HashMap<EmployeeType, List<AbstractEntity<EmployeeType>>>();
				
				Set<Entry<EmployeeType,Integer>> entrySet  = attributes.entrySet();
				
				AbstractEntity<EmployeeType> employee = null;
				
				for(Entry<EmployeeType,Integer> entry : entrySet) {
					
					int employeeSize = entry.getValue();
					EmployeeType EmployeeType = entry.getKey();
					for(int count = 0 ; count < employeeSize ; count ++) {
						employee =  factory.createEmployee(EmployeeType);
						if(null == employeeMap.get(entry.getKey())) {
							List<AbstractEntity<EmployeeType>> entityList =  new ArrayList<AbstractEntity<EmployeeType>>();	
							entityList.add(employee);
							employeeMap.put(EmployeeType, entityList); 
						}else {
							
							List<AbstractEntity<EmployeeType>> entityList =  employeeMap.get(EmployeeType);
							entityList.add(employee);
						}
					}
					
					
				}	
			
	}

	@Override
	public List<AbstractEntity<EmployeeType>> getEmployeeMap(EmployeeType type) {
		return employeeMap.get(type);
	}
	@Override
	public void setEmployeeMap(Map<EmployeeType, List<AbstractEntity<EmployeeType>>> employeeMap) {
		this.employeeMap = employeeMap;
	}
	@Override
	public int getWorkEmployeeSize(EmployeeType type) {
		List<AbstractEntity<EmployeeType>> entityList =  employeeMap.get(type);
		 return entityList.size() ;
	}
}
