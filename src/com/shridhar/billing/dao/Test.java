package com.shridhar.billing.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

class Calc {
	Calc() {
		System.out.println("calc constructor call");
	}
	public int add(int x, int y) {
		return x+y;
	}
	public int sub(int x, int y) {
		return x-y;
	}
	public int mul(int x, int y) {
		return x*y;
	}
	public int div(int x, int y) {
		return x/y;
	}
}

class ScCalc {
	public ScCalc() {
		// TODO Auto-generated constructor stub
		System.out.println("ScCalc constructor call");
	}
}

class PrCalc {
	public PrCalc() {
		// TODO Auto-generated constructor stub
		System.out.println("PrCalc constructor call");
	}
}

public class Test {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
//		Calc c = new Calc();
		System.out.println("Enter the class name to call");
		Scanner s = new Scanner(System.in);
		String className = s.next();
		Object object = Class.forName(className).newInstance();
		System.out.println("Enter the method name");
		String methodName = s.next();
		Method method = 
				object.getClass().getDeclaredMethod(methodName, int.class, int.class);
		Object result = method.invoke(object, 10, 20);
		System.out.println("Result is "+result);
		System.exit(0);
		
		try {
		int e =10/0;
		String name = null;
		System.out.println(name.toUpperCase());
		int[] x = new int[10];
		System.out.println("I am at 11");
		x[100] = 10;
		System.out.println("I am at 13");
		}
		catch(ArithmeticException e){
			e.printStackTrace();
		}
		catch(NullPointerException e){
			e.printStackTrace();
		}
		catch(ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		String path = "D:\\eclipse-workspace\\Billing\\src\\com\\shridhar\\billing\\dao\\Test.java";
		try {
		FileInputStream fs = new FileInputStream(path);
		}
		catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			System.out.println("I am at 88");
		}
		

	}

}
