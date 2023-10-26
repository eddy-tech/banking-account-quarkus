package org.banking.account.handlers

import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider
import org.banking.account.exceptions.ExceptionRepresentation
import org.hibernate.exception.ConstraintViolationException

@Provider
class ConstraintViolationExceptionHandler: ExceptionMapper<ConstraintViolationException> {
    override fun toResponse(exception: ConstraintViolationException?): Response {
        val representation = ExceptionRepresentation().also {
            it.errorMessage = "A user already exists with the provided email"
            it.errorSource = exception?.message
        }

        return Response.status(Response.Status.BAD_REQUEST)
            .entity(representation)
            .build()
    }
}