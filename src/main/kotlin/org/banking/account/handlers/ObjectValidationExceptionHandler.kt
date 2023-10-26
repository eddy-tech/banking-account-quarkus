package org.banking.account.handlers

import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider
import org.banking.account.exceptions.ExceptionRepresentation
import org.banking.account.exceptions.ObjectValidationException

@Provider
class ObjectValidationExceptionHandler: ExceptionMapper<ObjectValidationException> {
    override fun toResponse(exception: ObjectValidationException?): Response {
        val representation = ExceptionRepresentation().also {
            it.errorMessage = "Object not valid exception has occurred"
            it.errorSource = exception?.violationSource
            it.validationErrors = exception?.violations
        }

        return Response.status(Response.Status.BAD_REQUEST)
            .entity(representation)
            .build()
    }
}