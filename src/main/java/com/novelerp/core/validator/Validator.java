package com.novelerp.core.validator;

import com.novelerp.core.dto.Errors;

/**
 * Object Validator
 * @author Vivek
 *
 */
public interface Validator {

	public void validate(Object object, Errors errors);
}
