package org.banking.account.handlers

import jakarta.persistence.EntityNotFoundException
import jakarta.persistence.PersistenceException
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider
import org.banking.account.exceptions.ObjectValidationException
import org.banking.account.exceptions.OperationNoPermittedException
import java.util.logging.Logger

@Provider
 abstract class HandleDataIntegrityException : ExceptionMapper<PersistenceException> {
     fun handleDataIntegrity(exception: PersistenceException) : Response{
        val representation = ExceptionRepresentation(
            errorMessage = "\"A user already exists with the provided email\"",
            errorSource = exception.message,
            validationErrors = null
        )

        return Response.status(Response.Status.BAD_REQUEST)
            .entity(representation)
            .type(MediaType.APPLICATION_JSON_TYPE)
            .build()
    }
}