package org.banking.account.handlers

import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider
import org.banking.account.exceptions.ObjectValidationException
import org.banking.account.exceptions.OperationNoPermittedException

@Provider
 abstract class HandleOperationException : ExceptionMapper<OperationNoPermittedException> {
     fun handleOperation(exception: OperationNoPermittedException) : Response{
        val representation = ExceptionRepresentation(
            errorMessage = exception.errorMessage,
            errorSource = exception.source,
            validationErrors = null
        )

        return Response.status(Response.Status.NOT_ACCEPTABLE)
            .entity(representation)
            .type(MediaType.APPLICATION_JSON_TYPE)
            .build()
    }
}