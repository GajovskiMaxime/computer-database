package entities;

import entities.Company.Builder;
import interfaces.builders.ICompanyBuilder;
/**
 * @author	Gajovski Maxime
 * @date	21 févr. 2017
 */
public class CompanyFactory {
	
	public static ICompanyBuilder<Builder> create(){
		return new Company.Builder();
	}
}
