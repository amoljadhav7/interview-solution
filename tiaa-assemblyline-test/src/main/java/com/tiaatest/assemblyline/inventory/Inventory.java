package com.tiaatest.assemblyline.inventory;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tiaatest.assemblyline.entity.AbstractEntity;
import com.tiaatest.assemblyline.entity.ProductType;

/**
 * 
 * @author Amol
 * 
 * Inventory interface for invertory management
 *
 */

public interface Inventory {
	
	public int getStock(ProductType type);
	
	public void createInventory(Map<ProductType,Integer> attributes);

	public Map<ProductType, List<AbstractEntity<ProductType>>> getInventory() ;
	
	public void setInventory(Map<ProductType, List<AbstractEntity<ProductType>>> inventory);
	

}
