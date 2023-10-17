package org.banking.account.handlers

import jakarta.ejb.ApplicationException
import jakarta.persistence.EntityNotFoundException
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
 abstract class HandleEntityException : ExceptionMapper<EntityNotFoundException> {
     fun handleEntity(exception: EntityNotFoundException) : Response{
        val representation = ExceptionRepresentation(
            errorMessage = exception.message,
            errorSource = null,
            validationErrors = null
        )

        return Response.status(Response.Status.NOT_FOUND)
            .entity(representation)
            .type(MediaType.APPLICATION_JSON_TYPE)
            .build()
    }
}