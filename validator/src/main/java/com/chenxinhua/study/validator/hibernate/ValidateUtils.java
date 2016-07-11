package com.chenxinhua.study.validator.hibernate;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Iterator;
import java.util.Set;

/**
 * @author chenxinhua
 * @date 2016/7/7 10:55
 */
public class ValidateUtils {
    private static Validator validator;

    static {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
    private ValidateUtils() {
    }

    public static String validate(Object object) {
        StringBuilder stringBuilder = new StringBuilder();
        Set<ConstraintViolation<Object>> validate = validator.validate(object);
        for (ConstraintViolation<Object> next : validate) {
            stringBuilder.append(next.getPropertyPath()).append(next.getMessage()).append(" ");
        }
        return stringBuilder.toString();
    }
}
