package com.excilys.mgajovski.computer_database.entities;


import java.time.LocalDate;

import com.excilys.mgajovski.computer_database.utils.Utils;


/**
 * 
 * Computer impl. class implements IComputer
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 * @see 	IComputer
 */
public class Computer{
	
	private Long 		id;
	private String 		name;
	private LocalDate	introducedDate;
	private LocalDate	discontinuedDate;
	private Company		company;
	
	private Computer(){
		
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public LocalDate getIntroducedDate() {
		return introducedDate;
	}

	public void setIntroducedDate(LocalDate introducedDate) {
		this.introducedDate = introducedDate;
	}

	public LocalDate getDiscontinuedDate() {
		return discontinuedDate;
	}
	
	public void setDiscontinuedDate(LocalDate discontinued) {
		this.discontinuedDate = discontinued;
	}
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return 	"Class : " + this.getClass().getSimpleName() + "\n" + 
					"\t" + "id : " 					+ getId() 				+ "\n" + 
					"\t" + "name : " 				+ getName() 			+ "\n" + 
					"\t" + "introduced date : " 	+ (getIntroducedDate() == null ? Utils.UNDEFINED : getIntroducedDate()) + "\n" + 
					"\t" + "discontinued date : " 	+ (getDiscontinuedDate() == null ? Utils.UNDEFINED : getDiscontinuedDate()) + "\n" + 
					"\t" + "company name : " 		+ (getCompany() == null ? Utils.UNDEFINED : getCompany().getName()) + "\n";
	}

	public static Builder builder(){
		return new Builder();
	}
	
	/**
	 * Inner Builder for the Computer Class.
	 * @return a IComputer obj. with the Computer impl. class.
	 * @see Computer
	 */
	public static class Builder{
		
		private Computer computer = new Computer();
		
		public Builder(){
			
		}
		
		public Builder id(Long id){
			computer.id = id;
			return this;
		}
		
		public Builder name(String name){
			computer.name = name;
			return this;
		}

		public Builder company(Company company){
			computer.company = company;
			return this;
		}
		
		public Builder introduced(LocalDate introducedDate){
			computer.introducedDate = introducedDate;
			return this;
		}
		
		public Builder discontinued(LocalDate discontinuedDate){
			computer.discontinuedDate = discontinuedDate;
			return this;
		}
		
		public Computer build(){
			return computer;
		}


	}
}
