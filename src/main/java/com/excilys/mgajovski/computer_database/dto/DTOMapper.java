package com.excilys.mgajovski.computer_database.dto;

import java.util.List;

/**
 * @author Gajovski Maxime
 * @date 6 avr. 2017
 */
//@Component
public interface DTOMapper<K, I extends DTO<K>> {

  I transformToDTO(K k);
  List<I> transformListToDTOList(List<K> k);
  
  K transformFromDTO(I i);
  List<K> transformListFromDTOList(List<I> i);
}
