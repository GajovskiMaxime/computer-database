package com.excilys.mgajovski.computer_database.validations.primitives;

import com.excilys.mgajovski.computer_database.exceptions.mapping.IdException;
import com.excilys.mgajovski.computer_database.utils.ErrorTags;

/**
 * @author Gajovski Maxime
 * @date 27 févr. 2017
 */
public final class LongValidation {

  public static ErrorTags idIsNotInitialized(long id) {
    if (id != 0) {
      return ErrorTags.ID_IS_INIT;
    }
    return ErrorTags.SUCCESS;
  }

  public static ErrorTags idIsInitialized(long id) {
    if (id <= 0) {
      return ErrorTags.ID_IS_NOT_INIT;
    }
    return ErrorTags.SUCCESS;
  }

  public static ErrorTags isAnInitializedId(String id)  {
    if (id == null) {
      return ErrorTags.ID_IS_NULL;
    }
    if (id.isEmpty()) {
      return ErrorTags.ID_IS_EMPTY;
    }
    long longId;
    
    try {
      longId = Long.parseUnsignedLong(id);
    } catch (NumberFormatException e) {
      return ErrorTags.ID_IS_NaN;
    }
    return idIsInitialized(longId);
  }
  
//  public static ErrorTags companyIdIsSelected(String id){
//    if (id == null) {
//      return ErrorTags.ID_IS_NULL;
//    }
//    if (id.isEmpty()) {
//      return ErrorTags.ID_IS_EMPTY;
//    }
//    long longId;
//    
//    try {
//      longId = Long.parseUnsignedLong(id);
//    } catch (NumberFormatException e) {
//      return ErrorTags.ID_IS_NaN;
//    }
//    if(longId == 0){
//      return 
//    }
//  }
}
