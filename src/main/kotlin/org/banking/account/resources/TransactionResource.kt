package org.banking.account.resources

import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.banking.account.dto.TransactionDto
import org.banking.account.models.HttpResponse
import org.banking.account.roots.RootEndPoint.Companion.ROOT_ENDPOINT
import org.banking.account.services.interfaces.TransactionService
import java.net.URI
import java.time.LocalDateTime

@Path("$ROOT_ENDPOINT/transactions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class TransactionResource (
    private var transactionService: TransactionService
) {
    @POST
    fun saveAccount(transactionDto: TransactionDto): Response{
        val newTransaction = transactionService.save(transactionDto)
        val responseMap = HashMap<String, TransactionDto>()
        responseMap["transactions"] = newTransaction

        return Response.created(URI.create(""))
            .entity(
                HttpResponse().apply {
                    timeStamp = LocalDateTime.now().toString()
                    data = responseMap
                    message = "Transaction has been saved successfully"
                    status = Response.Status.CREATED
                    statusCode = Response.Status.CREATED.statusCode
                }
            )
        .build()
    }

    @PATCH
    @Path("/{id}")
    fun updateTransaction (transactionDto: TransactionDto, @PathParam("id") transactionId: Long) : Response{
        val transactionUpdate = transactionService.update(transactionDto, transactionId)
        val responseMap = HashMap<String, TransactionDto>()
        responseMap["transactions"] = transactionUpdate

        return Response.ok()
            .entity(
                HttpResponse().apply {
                    timeStamp = LocalDateTime.now().toString()
                    data = responseMap
                    message = "Transaction has been updated successfully"
                    status = Response.Status.OK
                    statusCode = Response.Status.OK.statusCode
                }
            )
        .build()
    }

    @GET
    fun getAllTransaction() : Response {
        val transactionList = transactionService.getAll()

        return Response.ok()
            .entity(transactionList)
        .build()
    }

    @GET
    @Path("/{id}")
    fun getTransactionById(@PathParam("id") id : Long) : Response {
        val transaction = transactionService.getById(id)
        val responseMap = HashMap<String, TransactionDto>()
        responseMap["transactions"] = transaction

        return Response.ok()
            .entity(
                HttpResponse().apply {
                    timeStamp = LocalDateTime.now().toString()
                    data = responseMap
                    message = "Get Transaction with id = $id has been successfully"
                    status = Response.Status.OK
                    statusCode = Response.Status.OK.statusCode
                }
            )
        .build()
    }

    @DELETE
    @Path("/{id}")
    fun deleteTransaction(@PathParam("id") id: Long) : Response {
        val transaction = transactionService.delete(id)
        val responseMap = HashMap<String, Any>()
        responseMap["transactions"] = transaction

        return Response.accepted()
            .entity(
                HttpResponse().apply {
                    timeStamp = LocalDateTime.now().toString()
                    data = responseMap
                    message = "Transaction with id = $id has been deleted with successfully"
                    status = Response.Status.OK
                    statusCode = Response.Status.OK.statusCode
                }
            )
        .build()
    }

    @GET
    @Path("/users/{id}")
    fun getAllTransactionByUser(@PathParam("id") userId: Long) : Response {
        val transactionByUser = transactionService.findAllByUser(userId)

        return Response.ok()
            .entity(transactionByUser)
        .build()
    }
}