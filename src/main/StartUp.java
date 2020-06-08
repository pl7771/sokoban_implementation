package main;

import cui.ConsoleApplication;
import domein.DomeinController;

public class StartUp {
	public static void main(String[] args) {
		
		new ConsoleApplication(new DomeinController()).start();
		
	}	
}