package com.tiaatest.assemblyline.factory;

import org.springframework.stereotype.Component;

import com.tiaatest.assemblyline.entity.AbstractEntity;
import com.tiaatest.assemblyline.entity.EmployeeType;
import com.tiaatest.assemblyline.entity.ProductType;

@Component("factory")
public  class AbstractFactoryImpl implements AbstractFactory {
	
	/*
	 * 
	 */
	public AbstractEntity<ProductType> createProduct(ProductType type){
		AbstractEntity<ProductType> product = null;
		if(type.equals(ProductType.BOLT)) {
			product= BoltFactory.createEntity();
		}else if(type.equals(ProductType.MACHINE)) {
			product = MachineFactory.createEntity();
		}
		return product;
	}
	
	/* 
	 * 
	 */
	public AbstractEntity<EmployeeType> createEmployee(EmployeeType type){
		AbstractEntity<EmployeeType> employee = null;
		if(type.equals(EmployeeType.LINEWORKER)) {
			employee= EmployeeFactory.createEntity(type);
		}else if(type.equals(EmployeeType.SUPERVISOR)){
			employee= EmployeeFactory.createEntity(type);
		}else if(type.equals(EmployeeType.INVENTORYMANGER)) {
			employee= EmployeeFactory.createEntity(type);
		}
		return employee;
	}
	

}
