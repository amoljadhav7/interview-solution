package com.tiaatest.assemblyline.management;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import com.tiaatest.assemblyline.entity.AbstractEntity;
import com.tiaatest.assemblyline.entity.Employee;
import com.tiaatest.assemblyline.entity.EmployeeType;
import com.tiaatest.assemblyline.entity.ProductType;

/**
 * 
 * @author Amol
 * Representation of task assigned  
 *
 */
public class Task implements Callable<Task> {

	public BlockingDeque<AbstractEntity<ProductType>> getQueue() {
		return queue;
	}

	public void setQueue(BlockingDeque<AbstractEntity<ProductType>> queue) {
		this.queue = queue;
	}

	public boolean isTaskCompleted() {
		return isTaskCompleted;
	}

	public void setTaskCompleted(boolean isTaskCompleted) {
		this.isTaskCompleted = isTaskCompleted;
	}

	public int getItemEntityCount() {
		return itemEntityCount;
	}

	public void setItemEntityCount(int itemEntityCount) {
		this.itemEntityCount = itemEntityCount;
	}

	public long getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(long timeTaken) {
		this.timeTaken = timeTaken;
	}

	private List<AbstractEntity<ProductType>> result;
	
	private AbstractEntity<EmployeeType> emp;

	private BlockingDeque<AbstractEntity<ProductType>> queue;
	
	private volatile boolean isTaskCompleted = false;
	
	private volatile int itemEntityCount = 0;
	
	private long timeTaken = 0; 
	
	public Task() {
		
	}
	
	public Task(AbstractEntity<EmployeeType> employee, BlockingDeque<AbstractEntity<ProductType>> productQueue) {
		emp = employee;
		queue = productQueue;
	}
	
	/**
	 * Task 
	 * 
	 */
	@Override
	public Task call() throws Exception {
		List<AbstractEntity<ProductType>> entityList  =  new ArrayList<AbstractEntity<ProductType>>();
		
		for(;;) {
			AbstractEntity<ProductType> entity = queue.poll();
			if(null == entity) {
				entity = queue.poll(100, TimeUnit.MILLISECONDS);
				if(null == entity) {
					// queue is empty hence return from the task
					result = entityList;
					return this;
				}
			}
			
			int boltItemsNo = 0;
			int machineItems = 0;
			if(!isTaskCompleted && entityList.size() < 3) {
				for(AbstractEntity<ProductType> existingEntity :entityList) {
					if(existingEntity.getType().equals(ProductType.BOLT)) {
						boltItemsNo++;
					}else if(existingEntity.getType().equals(ProductType.MACHINE)) {
						machineItems++;
					}
				}
				if(boltItemsNo < 2 && entity.getType().equals(ProductType.BOLT)) {
					entityList.add(entity);
					itemEntityCount ++;
				}else if(boltItemsNo == 2 && entity.getType().equals(ProductType.BOLT)) {
					queue.addLast(entity);
					
				}
					
				if(machineItems < 1 && entity.getType().equals(ProductType.MACHINE)) {
					entityList.add(entity);
					itemEntityCount ++;
				}else if(machineItems == 1 && entity.getType().equals(ProductType.MACHINE)) {
					queue.addLast(entity);
				}
				
				if(itemEntityCount == 3) {
					result = entityList;
					assembleJob();
					isTaskCompleted = true;
				}
			}
			
			if( isTaskCompleted && entityList.size() == 3 && itemEntityCount ==3) {
				result = entityList;
				break;
			}
			
		}
		return this;
	}

	public List<AbstractEntity<ProductType>> getResult() {
		return result;
	}

	public void setResult(List<AbstractEntity<ProductType>> result) {
		this.result = result;
	}

	public AbstractEntity<EmployeeType> getEmp() {
		return emp;
	}

	public void setEmp(AbstractEntity<EmployeeType> emp) {
		this.emp = emp;
	}
	
	/**
	 * Assemble job for 1 min
	 * @throws InterruptedException
	 */
	public void assembleJob() throws InterruptedException {
		long sytemTimeInMilliSecondStart = System.currentTimeMillis();
		Thread.sleep(1000);
		
		long sytemTimeInMilliSecondEnd = System.currentTimeMillis();
		
		timeTaken = sytemTimeInMilliSecondEnd - sytemTimeInMilliSecondStart;
	}
	
}
