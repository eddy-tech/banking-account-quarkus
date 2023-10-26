package org.banking.account.handlers

import jakarta.persistence.EntityNotFoundException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider
import org.banking.account.exceptions.ExceptionRepresentation

@Provider
class EntityNotFoundExceptionHandler: ExceptionMapper<EntityNotFoundException> {
    override fun toResponse(exception: EntityNotFoundException?): Response {
        val representation = ExceptionRepresentation().also {
            it.errorMessage = exception?.message
        }

        return Response.status(Response.Status.NOT_FOUND)
            .entity(representation)
            .build()
    }
}