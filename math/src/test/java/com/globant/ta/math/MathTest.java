package com.globant.ta.math;

import junit.framework.Assert;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MathTest {

	@DataProvider(name = "addDataProvider")
	public Object[][] addDataProvider() {
		return new Object[][] { { 1, 4, 5 }, { 1, 6, 7 }, { 1, 2, 3 },
				{ 1, -1, 0 }, { 10, -5, 5 }, { -9, 5, -4 } };
	}
	
	@DataProvider(name = "multiplicationDataProvider")
	public Object[][] multiplicationDataProvider() {
		return new Object[][] { { 1, 4, 4 }, { 2, 6, 12 }, { 3, 2, 6 },
				{ 4, 9, 36 }, { 5, -5, -25 }, { -6, 8, -48 } };
	}
	
	@DataProvider(name = "powDataProvider")
	public Object[][] powDataProvider() {
		return new Object[][] { { 2, 3, 8 }, { 4, 2, 16 }, { 5, 0, 1 },
				{ 3, 3, 27 }, { 5, -2, 0.04 } };
	}
	
	@DataProvider(name = "squareDataProvider")
	public Object[][] squareDataProvider() {
		return new Object[][] { { 4, 2 }, { 9, 3 }, {25, 5} };
	}
	
	@DataProvider(name = "sinDataProvider")
	public Object[][] sinDataProvider() {
		return new Object[][] { { 10, -0.5440211108893698 }, { 64, 0.9200260381967906 },
				{ 36, -0.9917788534431158 } };
	}
	
	@DataProvider(name = "cosDataProvider")
	public Object[][] cosDataProvider() {
		return new Object[][] { { 10, -0.8390715290764524 }, { 64, 0.39185723042955 },
				{ 36, -0.12796368962740468 } };
	}

	@Test(dataProvider = "addDataProvider", 
			description = "Test the sum of two arguments", 
			groups = { "basicOperations" })
	public void addExact(int x, int y, int result) {
		Assert.assertEquals(result, Math.addExact(x, y));
	}

	@Test(description = "Test the sum of two arguments larger than integer", 
			groups = { "basicOperations" }, 
			expectedExceptions = { ArithmeticException.class })
	public void addExactWithIntegerOverflow() {
		int x = 1;
		int y = Integer.MAX_VALUE;
		Math.addExact(x, y);
	}
	
	@Test(dataProvider = "multiplicationDataProvider", 
			description = "Test the multiplication of two arguments", 
			groups = { "basicOperations" })
	public void multiplyExact(int x, int y, int result){
		Assert.assertEquals(result, Math.multiplyExact(x, y));
	}
	
	@Test(dataProvider = "powDataProvider", 
			description = "Test the return of the first parameter raised to the power of the second parameter", 
			groups = { "basicOperations" })
	public void pow(int a, int b, double result){
		Assert.assertEquals(result, Math.pow(a, b));
	}
	
	@Test(dataProvider = "squareDataProvider", 
			description = "Test the return of the square root of the parameter given to it", 
			groups = { "basicOperations" })
	public void sqrt(double a, double result){
		Assert.assertEquals(result, Math.sqrt(a));
	}
	
	@Test(dataProvider = "sinDataProvider", 
			description = "Test the return of the sine value of some angle value in radians", 
			groups = { "trigonometricOperations" })
	public void sin(double a, double result){
		Assert.assertEquals(result, Math.sin(a));
	}
	
	@Test(dataProvider = "cosDataProvider", 
			description = "Test the return of the cosine value of some angle value in radians", 
			groups = { "trigonometricOperations" })
	public void cos(double a, double result){
		Assert.assertEquals(result, Math.cos(a));
	}

}
