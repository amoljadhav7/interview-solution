package com.tiaatest.assemblyline.management;

import java.util.concurrent.ExecutionException;

public interface TaskManager {
	
	public void createThreadPool();
	
	public void assignWork() throws InterruptedException, ExecutionException;
}
