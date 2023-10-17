package org.banking.account.validators

import jakarta.validation.ConstraintViolation
import jakarta.validation.Validation
import jakarta.validation.Validator
import jakarta.validation.ValidatorFactory
import org.banking.account.exceptions.ObjectValidationException
import java.util.stream.Collectors

class ObjectValidator {
    private val factory: ValidatorFactory = Validation.buildDefaultValidatorFactory()
    private val validator: Validator = factory.validator

    fun <T> validate(objectToValidate : T) {
       val  violations : Set<ConstraintViolation<T>> = validator.validate(objectToValidate)
        if(violations.isNotEmpty()) {
            val errorMessage : Set<String> = violations.stream()
                .map { it.message }
                .collect(Collectors.toSet())
            throw ObjectValidationException(errorMessage, objectToValidate.toString())
        }
    }
}