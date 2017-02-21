package interfaces.builders;

import interfaces.entities.ICompany;

/**
 * @author	Gajovski Maxime
 * @date	21 févr. 2017
 */
public interface ICompanyBuilder<K> {
	

	public K 		id(int id);
	public K		name(String name);
	public ICompany build();
}
