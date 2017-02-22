package entities;

/**
 * Classe Company
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public class Company {
	
	private int 	id;
	private String 	name;
	
	private Company(){
		
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
	
	public static Builder builder() {
		return new Builder();
	}
	
	@Override
	public String toString() {
		//TODO
		return null;
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

		public Builder id(int id) {
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
