package com.chenxinhua.study.validator.hibernate;

import org.apache.commons.lang3.StringUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;

import java.lang.reflect.Method;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author chenxinhua
 * @date 2016/7/7 10:15
 */
public class CarTest {

    private static Validator validator;
    private static ExecutableValidator executableValidator;
    @BeforeClass
    public static void setUp() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
        executableValidator = validator.forExecutables();
    }

    @Test
    public void manufacturerIsNull() {
        Car car = new Car(null, "Ddsfsd32sdfsdfD-23", 7);
        String validate = ValidateUtils.validate(car);
        if (StringUtils.isNotBlank(validate)) {
            System.out.println("validate = " + validate);
        }
    }

    @Test
    public void driveMethodParam() throws NoSuchMethodException {
        Car car = new Car();
        Method driveMethod = Car.class.getMethod("drive", int.class);
        Set<ConstraintViolation<Car>> constraintViolations = executableValidator.validateParameters(car, driveMethod, new Object[]{300});
        for (ConstraintViolation<Car> constraintViolation : constraintViolations) {
            System.out.println("constraintViolation.getMessage() = " + constraintViolation.getMessage());
        }
    }

}