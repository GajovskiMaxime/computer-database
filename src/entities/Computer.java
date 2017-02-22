package entities;

import java.time.LocalDate;

/**
 * 
 * Computer impl. class implements IComputer
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 * @see 	IComputer
 */
public class Computer{
	
	private int 		id;
	private String 		name;
	private LocalDate	introducedDate;
	private LocalDate	discontinuedDate;
	private Company		company;
	
	private Computer(){
		
	}
	
	public int getId() {
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
		return null;
		//TODO 
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
		
		public Builder id(int id){
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
