package com.tiaatest.assemblyline.factory;

import java.util.concurrent.atomic.AtomicInteger;

import com.tiaatest.assemblyline.entity.AbstractEntity;
import com.tiaatest.assemblyline.entity.Bolt;
import com.tiaatest.assemblyline.entity.ProductType;

public class BoltFactory {
	
	static AtomicInteger i = new AtomicInteger(0);
	
	public static AbstractEntity<ProductType> createEntity() {
		
		return new Bolt(i.incrementAndGet(), ProductType.BOLT);
		
	}

}
