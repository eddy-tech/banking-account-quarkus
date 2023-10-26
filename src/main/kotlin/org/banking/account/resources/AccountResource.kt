package org.banking.account.resources

import jakarta.ws.rs.Consumes
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.GET
import jakarta.ws.rs.PATCH
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.core.Response.Status
import org.banking.account.dto.AccountDto
import org.banking.account.models.HttpResponse
import org.banking.account.roots.RootEndPoint.Companion.ROOT_ENDPOINT
import org.banking.account.services.interfaces.AccountService
import java.net.URI
import java.time.LocalDateTime

@Path("$ROOT_ENDPOINT/accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class AccountResource (
    private var accountService: AccountService
) {
    @POST
    fun saveAccount(accountDto: AccountDto): Response{
        val newAccount = accountService.save(accountDto)
        val responseMap = HashMap<String, AccountDto>()
        responseMap["account"] = newAccount

        return Response.created(URI.create(""))
            .entity(
                HttpResponse().apply {
                    timeStamp = LocalDateTime.now().toString()
                    data = responseMap
                    message = "Account saved successfully"
                    status = Status.CREATED
                    statusCode = Status.CREATED.statusCode
                }
            )
            .build()
    }

    @PATCH
    @Path("/{id}")
    fun updateAccount (accountDto: AccountDto, @PathParam("id") accountId: Long) : Response{
        val accountUpdate = accountService.update(accountDto, accountId)
        val responseMap = HashMap<String, AccountDto>()
        responseMap["account"] = accountUpdate

        return Response.ok()
            .entity(
                HttpResponse().apply {
                    timeStamp = LocalDateTime.now().toString()
                    data = responseMap
                    message = "Account has been updated successfully"
                    status = Status.OK
                    statusCode = Status.OK.statusCode
                }
            )
            .build()
    }

    @GET
    fun getAllAccount() : Response {
        val accountList = accountService.getAll()

        return Response.ok()
            .entity(accountList)
            .build()
    }

    @GET
    @Path("/{id}")
    fun getAccountById(@PathParam("id") id : Long) : Response {
        val account = accountService.getById(id)
        val responseMap = HashMap<String, AccountDto>()
        responseMap["account"] = account

        return Response.ok()
            .entity(
                HttpResponse().apply {
                    timeStamp = LocalDateTime.now().toString()
                    data = responseMap
                    message = "Get Account with id = $id has been successfully"
                    status = Status.OK
                    statusCode = Status.OK.statusCode
                }
            )
            .build()
    }

    @DELETE
    @Path("/{id}")
    fun deleteAccount(@PathParam("id") id: Long) : Response {
        val account = accountService.delete(id)
        val responseMap = HashMap<String, Any>()
        responseMap["account"] = account

        return Response.accepted()
            .entity(
                HttpResponse().apply {
                    timeStamp = LocalDateTime.now().toString()
                    data = responseMap
                    message = "Account with id = $id has been deleted with successfully"
                    status = Status.OK
                    statusCode = Status.OK.statusCode
                }
            )
            .build()
    }
}