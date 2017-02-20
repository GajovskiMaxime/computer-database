package entities;

import interfaces.ICompany;

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
		this.name = builder.name;
	}
	
	@Override
	public int getId() {
		return id;
	}
	
	@Override
	public String getName() {
		return name;
	}
	

	/**
	 * Inner Builder for the Company Class.
	 * @return a ICompany obj. with the Company impl. class.
	 * @see Company
	 */
	
	public static class Builder{
		
		private String 	name;
		
		public Builder(){
			
		}
		
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		
		public ICompany build(){
			return new Company(this);
		}
		
	}
}
