package com.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoTest1 {

	@Test
	public void sum() {
		int a = 10;
		int b = 20;
		Assert.assertEquals(b + a, 30);
	}
	
	@Test
	public void mul() {
		int a = 10;
		int b = 20;
		Assert.assertEquals(b * a, 200);
	}
	
	@Test
	public void sub() {
		int a = 10;
		int b = 20;
		Assert.assertEquals(b - a, 10);
	}
	
	@Test
	public void div() {
		int a = 10;
		int b = 20;
		Assert.assertEquals(b / a, 2);
	}
}
