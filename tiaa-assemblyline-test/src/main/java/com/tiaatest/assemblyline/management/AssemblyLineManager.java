package com.tiaatest.assemblyline.management;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tiaatest.assemblyline.entity.AbstractEntity;
import com.tiaatest.assemblyline.entity.EmployeeType;
import com.tiaatest.assemblyline.entity.ProductType;
import com.tiaatest.assemblyline.inventory.Inventory;
import com.tiaatest.assemblyline.work.Workers;

@Component
public class AssemblyLineManager implements AssemblyLine {

	@Autowired
	private Inventory inventory;
	
	@Autowired
	private Workers workManger;
	
	/**
	 * Assembly queue to hold alternate goods(unfurnished)
	 */
	private BlockingDeque<AbstractEntity<ProductType>> assemblyDeque ;
	
		
	/* 
	 * 
	 */
	@Override
	public void createInventory(Map<ProductType,Integer> attributes) {
		inventory.createInventory(attributes);
	}

	/* 
	 * 
	 */
	@Override
	public void createWorkers(Map<EmployeeType,Integer> attributes) {
		workManger.createWorkers(attributes);
	}

	/**
	 * This method will create the assembly queue (conveyer belt) as per requirement like
	 * alternative unfurnished products like 1 machine and 1 bolt
	 * so if person pick ups a item he/she shall pick up 1 item either 1 machine or 1 bolt
	 * This may result in alternate products but if the numbers are odd then the alternate product sequense can be seen upto the 1st 
	 * product is population in queue is completed. e.g. for 6 bolts and 3 machine only 1st three machines and bolts will be alternate 
	 * but last 3 bolts will sequential as no machines are left
	 */
	@Override
	public void buildAsseblyQueue() {
		int capacity = 0;
		int boltStock = inventory.getStock(ProductType.BOLT);
		int machineStock = inventory.getStock(ProductType.MACHINE);
		capacity = boltStock + machineStock;
		assemblyDeque =  new LinkedBlockingDeque<AbstractEntity<ProductType>>(capacity);
		
		Map<ProductType, List<AbstractEntity<ProductType>>> inventoryLcl =   inventory.getInventory();
		
		Set<Entry<ProductType,List<AbstractEntity<ProductType>>>> entrySet =  inventoryLcl.entrySet();
		
		List<AbstractEntity<ProductType>> boltLst = null ;
		
		List<AbstractEntity<ProductType>> machineLst = null;

		 for(Entry<ProductType,List<AbstractEntity<ProductType>>> entr : entrySet) {
			 ProductType type = entr.getKey();
			 
			 if(ProductType.BOLT.equals(type)) {
				  boltLst = entr.getValue();
			 }else if(ProductType.MACHINE.equals(type)) {
				 machineLst = entr.getValue();
			 }
		 }
		 
		 int maxSize = boltStock > machineStock ? boltStock : machineStock;
		 for(int i = 0 ; i < maxSize ; i++) {
			
			 if( i < boltStock) {
				 assemblyDeque.add(boltLst.get(i));
			}
			 if( i < machineStock) {
				 assemblyDeque.add(machineLst.get(i));
			}
			 
		 }
	}

	
	

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Workers getWorkManger() {
		return workManger;
	}

	public void setWorkManger(Workers workManger) {
		this.workManger = workManger;
	}

	@Override
	public BlockingDeque<AbstractEntity<ProductType>> getAssemblyDeque() {
		
		return assemblyDeque;
	}

	@Override
	public void setAssemblyDeque(BlockingDeque<AbstractEntity<ProductType>> assemblyDeque) {
		this.assemblyDeque =	assemblyDeque;	
	}

	
	
}
