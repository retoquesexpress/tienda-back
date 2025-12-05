//package com.fpmislata.tienda_back.domain.validation;
//
//import jakarta.validation.ConstraintViolationException;
//import jakarta.validation.Validation;
//import jakarta.validation.ValidationException;
//import jakarta.validation.Validator;
//
//import java.util.Set;
//
//public class DtoValidator {
//    private static Validator validator;
//
//    private static Validator getValidator() {
//        if (validator == null) {
//            ValidatorFactor factory = Validation.byDefaultProvider()
//                    .configure()
//                    .buildValidatorFactory();
//            validator = factory.getValidator();
//        }
//        return validator;
//    }
//
//    public static <T> void validate(T dto) {
//        Set<ConstraintViolationException<T>> violations = getValidator().validate(dto);
//        if (!violations.isEmpty()) {
//            throw new ValidationException(violations);
//        }
//    }
//}
