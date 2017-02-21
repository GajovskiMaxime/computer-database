package entities;

import interfaces.builders.ICompanyBuilder;
import interfaces.entities.ICompany;
import interfaces.validations.ICompanyValidation;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public class Company implements ICompany{
	
	private int 	id;
	private String 	name;
	

	/**
	 * Company constructor with inner  builder param.
	 * @param builder
	 */
	public Company(Company.Builder builder){
		this.id 	= builder.id;
		this.name 	= builder.name;
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
	public void setName(String name) {
		this.name = name;
	}
	
	
	@Override
	public String toString() {
		return display();
	}

	/**
	 * Inner Builder for the Company Class.
	 * @return a ICompany obj. with the Company impl. class.
	 * @see Company
	 */
	
	public static class Builder implements ICompanyBuilder<Builder>{
		
		private int 	id;
		private String 	name;
		
		
		public Builder(){
			
		}

		public Builder id(int id) {
			this.id = id;
			return this;
		}
		
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		
		public ICompany build(){
			ICompany company = new Company(this);
			ICompanyValidation.checkCompany(company);
			return company;
		}

		
	}
}
