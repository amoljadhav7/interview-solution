package com.tiaatest.assemblyline.entity;

/**
 * 
 * @author Amol
 * Abstract class for Products
 *
 */

public abstract class AbstractEntity<T> {
	
	private T type;
	
	private int uuid;

	public AbstractEntity(int productid, T type) {
		this.uuid = productid;
		this.type = type;
	}

	public T getType() {
		return type;
	}

	public void setType(T type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "AbstractEntity [type=" + type + ", uuid=" + uuid + "]";
	}

	public int getUUID() {
		return uuid;
	}

	public void setUUID(int uuid) {
		this.uuid = uuid;
	}
}
