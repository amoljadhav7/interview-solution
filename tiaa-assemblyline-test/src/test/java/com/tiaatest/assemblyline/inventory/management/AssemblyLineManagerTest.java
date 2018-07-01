package com.tiaatest.assemblyline.inventory.management;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tiaatest.assemblyline.entity.AbstractEntity;
import com.tiaatest.assemblyline.entity.EmployeeType;
import com.tiaatest.assemblyline.entity.ProductType;
import com.tiaatest.assemblyline.management.AssemblyLine;
import com.tiaatest.assemblyline.management.TaskManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:*applicationContext.xml"})
public class AssemblyLineManagerTest {

	@Autowired
	private AssemblyLine assemblyLineManager;
	
	@Autowired
	private TaskManager taskManager;
	
	private Map<ProductType, Integer> productAttrMap ;
	
	private Map<EmployeeType, Integer> employeeAttrMap ;
	
	@Before
	public void setUp() throws Exception {
		System.out.println("Before test method");
		productAttrMap = new HashMap<ProductType,Integer>();
		employeeAttrMap = new HashMap<EmployeeType,Integer>();
	}
	
	@After
	public void tearDown() throws Exception {
		System.out.println("after test mathod");
		productAttrMap = null;
		employeeAttrMap = null;
	}

	@Test
	public void testInvertory() {
		System.out.println("Test testInvertory");
		productAttrMap.put(ProductType.MACHINE, 3);
		productAttrMap.put(ProductType.BOLT,6);
		assemblyLineManager.createInventory(productAttrMap);
	
	}
	
	@Test
	public void testWorkers() {
		System.out.println("Test workers");
		employeeAttrMap.put(EmployeeType.LINEWORKER, 3);
		employeeAttrMap.put(EmployeeType.SUPERVISOR, 1);
		employeeAttrMap.put(EmployeeType.INVENTORYMANGER, 1);
		assemblyLineManager.createWorkers(employeeAttrMap);
		
	}
	/**
	 * Test the scenario 6 bolts and 3 machines and 3 workers
	 * 
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Test
	public void testAssemblyOverAllOne() throws InterruptedException, ExecutionException {
		System.out.println("Test testAssemblyOverAllOne started ==>");
		
		productAttrMap.put(ProductType.MACHINE, 3);
		productAttrMap.put(ProductType.BOLT,6);
		assemblyLineManager.createInventory(productAttrMap);
		employeeAttrMap.put(EmployeeType.LINEWORKER, 3);
		employeeAttrMap.put(EmployeeType.SUPERVISOR, 1);
		employeeAttrMap.put(EmployeeType.INVENTORYMANGER, 1);
		assemblyLineManager.createWorkers(employeeAttrMap);
		assemblyLineManager.buildAsseblyQueue();
		BlockingDeque<AbstractEntity<ProductType>> que = assemblyLineManager.getAssemblyDeque();
		System.out.println(que.toString());
		taskManager.createThreadPool();
		taskManager.assignWork();
		
	}
	
	/**
	 * Test the scenario 10 bolts and 5 machines and 3 workers
	 * 
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Test
	public void testAssemblyOverAllTwo() throws InterruptedException, ExecutionException {
		System.out.println("Test testAssemblyOverAllTwo started ==>");
		
		productAttrMap.put(ProductType.MACHINE, 2);
		productAttrMap.put(ProductType.BOLT,4);
		assemblyLineManager.createInventory(productAttrMap);
		employeeAttrMap.put(EmployeeType.LINEWORKER, 3);
		employeeAttrMap.put(EmployeeType.SUPERVISOR, 1);
		employeeAttrMap.put(EmployeeType.INVENTORYMANGER, 1);
		assemblyLineManager.createWorkers(employeeAttrMap);
		
		assemblyLineManager.buildAsseblyQueue();
		
		BlockingDeque<AbstractEntity<ProductType>> que = assemblyLineManager.getAssemblyDeque();
		System.out.println(que.toString());
		taskManager.createThreadPool();
		taskManager.assignWork();
		
	}
}
