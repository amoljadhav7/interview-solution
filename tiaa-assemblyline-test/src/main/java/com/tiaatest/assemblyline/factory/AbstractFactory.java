package com.tiaatest.assemblyline.factory;

import com.tiaatest.assemblyline.entity.AbstractEntity;
import com.tiaatest.assemblyline.entity.EmployeeType;
import com.tiaatest.assemblyline.entity.ProductType;
/**
 * 
 * @author Amol
 * Abstract Factory for Entities
 *
 */
public interface AbstractFactory {

	AbstractEntity<ProductType> createProduct(ProductType type);

	AbstractEntity<EmployeeType> createEmployee(EmployeeType type);

}