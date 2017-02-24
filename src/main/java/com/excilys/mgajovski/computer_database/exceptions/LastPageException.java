package com.excilys.mgajovski.computer_database.exceptions;


/**
 * @author	Gajovski Maxime
 * @date	23 févr. 2017
 */
public class LastPageException extends RuntimeException{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LastPageException(){
		super();
	}
	
	public LastPageException(String msg){
		super(msg);
	}
	
}
