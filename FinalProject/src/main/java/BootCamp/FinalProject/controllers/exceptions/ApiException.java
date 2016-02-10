package BootCamp.FinalProject.controllers.exceptions;

@SuppressWarnings("serial")
public class ApiException extends Exception {
		
	public ApiException(String msg) {
		super(msg);
	}
}
