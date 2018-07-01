package com.tiaatest.assemblyline.management;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiaatest.assemblyline.entity.AbstractEntity;
import com.tiaatest.assemblyline.entity.EmployeeType;
import com.tiaatest.assemblyline.entity.ProductType;

@Service
public class TaskManagerImpl implements TaskManager {

	@Autowired
	private AssemblyLine assembly ;
	
	private ExecutorService exeServ;
	
	/**
	 *  create thread pool using executers framework based on employee capacity
	 */
	@Override
	public void createThreadPool() {
		exeServ = Executors.newFixedThreadPool(assembly.getWorkManger().getWorkEmployeeSize(EmployeeType.LINEWORKER));	
	}

	@Override
	public void assignWork() throws InterruptedException, ExecutionException {
		System.out.println("Assembly Task Started time" );
		long currentMilliseconds = System.currentTimeMillis();
		List<AbstractEntity<EmployeeType>> employeeLst = assembly.getWorkManger().getEmployeeMap(EmployeeType.LINEWORKER);
		List<Future<Task>> futureLst = new ArrayList<Future<Task>>();
		for(AbstractEntity<EmployeeType> employee : employeeLst) {
			futureLst.add(exeServ.submit(new Task(employee,assembly.getAssemblyDeque())));			
		}
		int completedtask = 0;
		for( Future<Task> future : futureLst) {
			Task task = future.get();
			List<AbstractEntity<ProductType>> entityLst = task.getResult();
			System.out.println("Future Task Details ------>");
			System.out.println(task.getEmp()+ "----> Employee ");
			if(task.isTaskCompleted()) {
				completedtask = completedtask+1;
			}
			System.out.println("Is Task completed ==>" + task.isTaskCompleted());
			for(AbstractEntity<ProductType> product : entityLst) {
				System.out.println(product.toString());
			}
			System.out.println("Time taken in milliseconds to complete the task ==>" + task.getTimeTaken() );
			System.out.println("Future Task Details ends ------>");
		}
		
		System.out.println("Total tasks completed ==> " + completedtask );
		System.out.println("Total time required for assemblyt in milliseconds ==> " + (System.currentTimeMillis() - currentMilliseconds));
	}

}
