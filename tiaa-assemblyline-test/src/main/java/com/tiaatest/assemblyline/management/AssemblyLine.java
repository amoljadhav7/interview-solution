package com.tiaatest.assemblyline.management;

import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

import com.tiaatest.assemblyline.entity.AbstractEntity;
import com.tiaatest.assemblyline.entity.EmployeeType;
import com.tiaatest.assemblyline.entity.ProductType;
import com.tiaatest.assemblyline.inventory.Inventory;
import com.tiaatest.assemblyline.work.Workers;
/**
 * 
 * @author Amol
 * Assembly line functionality to build queue , inventory and work employees
 *
 */
public interface AssemblyLine {

	public void createInventory(Map<ProductType, Integer> attributes);

	public void createWorkers(Map<EmployeeType, Integer> attributes);

	public void buildAsseblyQueue();
	
	public BlockingDeque<AbstractEntity<ProductType>> getAssemblyDeque();

	public void setAssemblyDeque(BlockingDeque<AbstractEntity<ProductType>> assemblyDeque) ;
	
	public Inventory getInventory();

	public void setInventory(Inventory inventory);

	public Workers getWorkManger();

	public void setWorkManger(Workers workManger);
	
	
}