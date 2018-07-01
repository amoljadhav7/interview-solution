package com.tiaatest.assemblyline.entity;

import java.util.ArrayList;
import java.util.List;

public class Machine extends AbstractEntity<ProductType> {
	
	private List<Bolt> bolts = new ArrayList<Bolt>();

	public Machine(int productid, ProductType type) {
		super(productid, type);
	}

	public List<Bolt> getBolts() {
		return bolts;
	}

	public void setBolts(List<Bolt> bolts) {
		this.bolts = bolts;
	}

		
}
