package com.Utils;

public class BaseObjects {

	private DriverHelper helper = null;

	public DriverHelper driverHelper() {
		if (helper == null) {
			helper = new DriverHelper();
		}
		return helper;
	}

}
