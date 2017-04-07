package com.excilys.mgajovski.computer_database.validations.primitives;

import com.excilys.mgajovski.computer_database.utils.ErrorTags;

/**
 * @author Gajovski Maxime
 * @date 20 févr. 2017
 */
public final class StringValidation {

  public static final String NAME_REGEX = "[a-zA-Z0-9-_ ./]*";

  public static ErrorTags hasValidName(String name) {

    if (name == null) {
      return ErrorTags.NAME_IS_NULL;
    }

    if (name.isEmpty()) {
      return ErrorTags.NAME_IS_EMPTY;
    }

    if (!name.matches(NAME_REGEX)) {
      return ErrorTags.NAME_MISMATCH_REGEX;
    }
    return ErrorTags.SUCCESS;
  }
}
