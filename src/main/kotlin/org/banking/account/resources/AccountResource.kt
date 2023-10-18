package org.banking.account.resources

import io.netty.handler.codec.http.HttpResponseStatus
import jakarta.inject.Inject
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.banking.account.dto.AccountDto
import org.banking.account.models.HttpResponse
import org.banking.account.roots.RootEndPoint.Companion.ROOT_ENDPOINT
import org.banking.account.services.interfaces.AccountService
import java.net.URI
import java.time.LocalDateTime

@Path(ROOT_ENDPOINT)
@Produces(MediaType.APPLICATION_JSON)
class AccountResource @Inject constructor(
    private var accountService: AccountService
) {

    @POST
    fun saveAccount(accountDto: AccountDto): Response{
        val newAccount = accountService.save(accountDto)
        val responseMap = HashMap<String, AccountDto>()
        responseMap["account"] = newAccount
        return Response.created(URI.create(""))
            .entity(
                HttpResponse(
                    timeStamp = LocalDateTime.now().toString(),
                    data = responseMap,
                    message = "Account saved successfully",
                    status = Response.Status.CREATED,
                )
            )
            .build()
    }
}