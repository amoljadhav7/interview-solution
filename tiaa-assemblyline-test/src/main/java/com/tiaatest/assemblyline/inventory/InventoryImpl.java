package com.tiaatest.assemblyline.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiaatest.assemblyline.entity.AbstractEntity;
import com.tiaatest.assemblyline.entity.ProductType;
import com.tiaatest.assemblyline.factory.AbstractFactory;
import com.tiaatest.assemblyline.factory.AbstractFactoryImpl;

/**
 * 
 * @author Amol
 * 
 * Concrete implementation for Inventory
 * 
 */
@Service
public class InventoryImpl implements Inventory {

	
	private Map<ProductType, List<AbstractEntity<ProductType>>> inventory = null;
	
	@Autowired
	private AbstractFactory factory;
	
	/**
	 *  retrive the stock size
	 */
	public int getStock(ProductType type) {
		
		List<AbstractEntity<ProductType>> productLst = inventory.get(type);
		return productLst.size();
	}
	
	/**
	 *  Create an inventory using attributes contains product type and stock size
	 */

	public void createInventory(Map<ProductType,Integer> attributes) {
		System.out.println("create Inventory");
		inventory = new HashMap<ProductType, List<AbstractEntity<ProductType>>>();
		
		Set<Entry<ProductType,Integer>> entrySet  = attributes.entrySet();
		
		AbstractEntity<ProductType> product = null;
		
		for(Entry<ProductType,Integer> entry : entrySet) {
			
			int stockSize = entry.getValue();
			ProductType productType = entry.getKey();
			for(int count = 0 ; count < stockSize ; count ++) {
				product =  factory.createProduct(productType);
				if(null == inventory.get(entry.getKey())) {
					List<AbstractEntity<ProductType>> entityList =  new ArrayList<AbstractEntity<ProductType>>();	
					entityList.add(product);
					inventory.put(productType, entityList); 
				}else {
					
					List<AbstractEntity<ProductType>> entityList =  inventory.get(productType);
					entityList.add(product);
				}
			}
		}
	}

	public Map<ProductType, List<AbstractEntity<ProductType>>> getInventory() {
		return inventory;
	}

	public void setInventory(Map<ProductType, List<AbstractEntity<ProductType>>> inventory) {
		this.inventory = inventory;
	}



}
