package entities;

import java.util.Date;

import interfaces.ICompany;
import interfaces.IComputer;
import interfaces.IComputerValidation;


/**
 * 
 * Computer impl. class implements IComputer
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 * @see 	IComputer
 */
public class Computer implements IComputer{
	
	private int 		id;
	private String 		name;
	private Date		introducedDate;
	private Date		discontinued;
	private ICompany	company;
	

	/**
	 * Computer constructor with inner builder param.
	 * @param builder
	 */
	public Computer(Computer.Builder builder){
		this.name 			= builder.name;
		this.company 		= builder.company;
		this.introducedDate = builder.introduced;
		this.discontinued	= builder.discontinued;
	}
	
	
	@Override
	public int getId() {
		return id;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public Date getIntroducedDate() {
		return introducedDate;
	}


	@Override
	public void setIntroducedDate(Date introduced) {
		this.introducedDate = introduced;
	}


	@Override
	public Date getDiscontinuedDate() {
		return discontinued;
	}


	@Override
	public void setDiscontinuedDate(Date discontinued) {
		this.discontinued = discontinued;
	}


	@Override
	public ICompany getCompany() {
		return company;
	}

	@Override
	public void setCompany(ICompany company) {
		this.company = company;
	}


	/**
	 * Inner Builder for the Computer Class.
	 * @return a IComputer obj. with the Computer impl. class.
	 * @see Computer
	 */
	public static class Builder{
		private String 		name;
		private Date		introduced;
		private Date		discontinued;
		private ICompany	company;
		
		public Builder(){
			
		}
		
		public Builder name(String name){
			this.name = name;
			return this;
		}

		public Builder company(ICompany company){
			this.company = company;
			return this;
		}
		
		public Builder introced(Date introduced){
			this.introduced = introduced;
			return this;
		}
		
		public Builder discontinued(Date discontinued){
			this.discontinued = discontinued;
			return this;
		}
		
		public IComputer build(){
			IComputer computer = new Computer(this);
			IComputerValidation.checkComputer(computer);
			return computer;
		}

	}
}
