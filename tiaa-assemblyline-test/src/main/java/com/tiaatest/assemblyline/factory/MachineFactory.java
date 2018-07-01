package com.tiaatest.assemblyline.factory;

import java.util.concurrent.atomic.AtomicInteger;

import com.tiaatest.assemblyline.entity.Machine;
import com.tiaatest.assemblyline.entity.ProductType;

public class MachineFactory {

	static AtomicInteger i = new AtomicInteger(0);
	
	public static Machine createEntity() {
		
		return new Machine(i.incrementAndGet(), ProductType.MACHINE);
		
	}

}
