package com.excilys.mgajovski.computer_database.entities;


/**
 * Classe Company
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public class Company {
	
	private Long 	id;
	private String 	name;
	
	private Company(){
		
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
	
	public static Builder builder() {
		return new Builder();
	}
	
	@Override
	public String toString() {
		return 	"Class : " + this.getClass().getSimpleName() + "\n" + 
					"\t" + "id : " + getId() + "\n" + 
					"\t" + "name : " + getName() + "\n";
	}
	
	
	/**
	 * Inner Builder for the Company Class.
	 * @return a ICompany obj. with the Company impl. class.
	 * @see Company
	 */
	
	public static class Builder{
		
		private Company company = new Company();
		
		public Builder(){
			
		}

		public Builder id(Long id) {
			company.id = id;
			return this;
		}
		
		public Builder name(String name) {
			company.name = name;
			return this;
		}
		
		public Company build(){
			return company;
		}
	}
}
