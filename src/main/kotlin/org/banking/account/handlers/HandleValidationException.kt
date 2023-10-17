package org.banking.account.handlers

import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider
import org.banking.account.exceptions.ObjectValidationException

@Provider
 abstract class HandleValidationException : ExceptionMapper<ObjectValidationException> {
     fun handleValidation(exception: ObjectValidationException) : Response{
        val representation = ExceptionRepresentation(
            "Object not valid exception has occurred",
            exception.violationSource,
            exception.violations
        )

        return Response.status(Response.Status.BAD_REQUEST)
            .entity(representation)
            .type(MediaType.APPLICATION_JSON_TYPE)
            .build()
    }
}