package org.banking.account.handlers

import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider
import org.banking.account.exceptions.ExceptionRepresentation
import org.banking.account.exceptions.OperationNoPermittedException

@Provider
class OperationNoPermittedExceptionHandler : ExceptionMapper<OperationNoPermittedException> {
    override fun toResponse(exception: OperationNoPermittedException?): Response {
        val representation = ExceptionRepresentation().also {
            it.errorMessage = exception?.errorMessage
            it.errorSource = exception?.message
        }

        return Response.status(Response.Status.NOT_ACCEPTABLE)
            .entity(representation)
            .build()
    }
}