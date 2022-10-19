package com.halitozgur.lablog.exception;


/**
 * Inconsistent date exception: checks if the end date is after start date.
 * @author User
 *
 */
public class InconsistentDateException extends Exception{

	public InconsistentDateException(String string) {
		super(string);
	}

}
