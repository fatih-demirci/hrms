package com.kodlamaio.hrms.core.utilities;

public class SaveNormalizer {
	
	
	public static String setString(String name){

		if(name.isEmpty()) {
			return null;
		}
		name = name.toLowerCase();
		String firstChar = Character.toString(name.toUpperCase().charAt(0));
		String result = firstChar;
		for(int i=1;i<name.length();i++) {
			result+=Character.toString(name.charAt(i));
		}
		return result;
		
	}
	


}
