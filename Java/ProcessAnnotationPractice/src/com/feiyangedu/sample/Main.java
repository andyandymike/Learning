package com.feiyangedu.sample;

import java.lang.reflect.Method;

public class Main {

	public static void main(String[] args) throws Exception {
		Person p1 = new Person("Xiao Ming", 25, "100121");
		Person p2 = new Person(null, 15, "8080");
		checkPerson(p1);
		checkPerson(p2);
	}

	static void checkPerson(Person p) throws Exception {
		System.out.println("check " + p + "...");
		// TODO: check person...
		// @NotNull: 非null
		// @Range: 整型检查值min~max, 字符串检查长度介于min~max
		// @ZipCode(value): 检查字符串是否全部由数字构成，且长度恰好为value
		Class cls = Person.class;
		Method[] PersonMethods = cls.getMethods();
		for(Method PersonMethod: PersonMethods) {
			if(PersonMethod.isAnnotationPresent(NotNull.class)) {
				if(PersonMethod.invoke(p) == null) {
					System.out.println("Error: Method " + PersonMethod.getName() + " return value is null.");
					continue;
				}
			}
			if(PersonMethod.isAnnotationPresent(Range.class)) {
				if(PersonMethod.getReturnType() == int.class) {
					Integer returnValue = (Integer) PersonMethod.invoke(p);
					Range range = PersonMethod.getAnnotation(Range.class);
					if(returnValue < range.min() || returnValue > range.max()) {
						System.out.println("Error: Method " + PersonMethod.getName() + " return value out of range.");
						continue;
					}
				}
				if(PersonMethod.getReturnType() == String.class) {
					String returnValue = PersonMethod.invoke(p).toString();
					Range range = PersonMethod.getAnnotation(Range.class);
					if(returnValue.length() < range.min() || returnValue.length() > range.max()) {
						System.out.println("Error: Method " + PersonMethod.getName() + " return value out of range.");
						continue;
					}
				}
			}
			if(PersonMethod.isAnnotationPresent(ZipCode.class)) {
				String returnValue = (String) PersonMethod.invoke(p);
				ZipCode zipcode = PersonMethod.getAnnotation(ZipCode.class);
				if(returnValue.length() != zipcode.value()) {
					System.out.println("Error: Method " + PersonMethod.getName() + " return value length invalid.");
					continue;
				}
				try {
					Integer.parseInt(returnValue);
				} catch(NumberFormatException e){
					System.out.println("Error: Method " + PersonMethod.getName() + " return value not a number.");
					continue;
				}
			}
		}
	}

}
