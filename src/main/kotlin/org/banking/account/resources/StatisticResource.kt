package org.banking.account.resources

import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.banking.account.models.HttpResponse
import org.banking.account.projections.TransactionSumDetails
import org.banking.account.roots.RootEndPoint
import org.banking.account.services.interfaces.StatisticService
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Path("${RootEndPoint.ROOT_ENDPOINT}/statistics")
@Produces(MediaType.APPLICATION_JSON)
class StatisticResource(
    private var statisticService: StatisticService
) {

    @GET
    @Path("/sum-by-date/{id}")
    fun getSumTransactionByDate(
        @QueryParam("start-date")startDate: LocalDate,
        @QueryParam("end-date")endDate: LocalDate,
        @PathParam("id") userId: Long
    ) : Response {
        val sumTransaction = statisticService.findSumTransactionByDate(startDate, endDate, userId)
        val responseMap = HashMap<String, List<TransactionSumDetails>>()
        responseMap["statistics"] = sumTransaction

        return Response.ok()
            .entity(
                HttpResponse().apply {
                    timeStamp = LocalDateTime.now().toString()
                    data = responseMap
                    message = "New Sum Transaction success!!"
                    status = Response.Status.OK
                    statusCode = Response.Status.OK.statusCode
                }
            )
        .build()
    }

    @GET
    @Path("/account-balance/{id}")
    fun getAccountBalance(@PathParam("id") userId: Long) : Response {
        val balance = statisticService.getAccountBalance(userId)
        val responseMap = HashMap<String, BigDecimal?>()
        responseMap["account"] = balance

        return Response.ok()
            .entity(
                HttpResponse().apply {
                    timeStamp = LocalDateTime.now().toString()
                    data = responseMap
                    message = "Account balance has been found with successfully"
                    status = Response.Status.OK
                    statusCode = Response.Status.OK.statusCode
                }
            )
        .build()
    }

    @GET
    @Path("/highest-transfer/{id}")
    fun getHighestTransfer(@PathParam("id") userId: Long) : Response {
        val highestTransfer = statisticService.highestTransfer(userId)
        val responseMap = HashMap<String, BigDecimal>()
        responseMap["highest-transfer"] = highestTransfer

        return Response.ok()
            .entity(
                HttpResponse().apply {
                    timeStamp = LocalDateTime.now().toString()
                    data = responseMap
                    message = "Highest transfer has been found with successfully"
                    status = Response.Status.OK
                    statusCode = Response.Status.OK.statusCode
                }
            )
        .build()
    }

    @GET
    @Path("/highest-deposit/{id}")
    fun getHighestDeposit(@PathParam("id") userId: Long) : Response {
        val highestDeposit = statisticService.highestDeposit(userId)
        val responseMap = HashMap<String, BigDecimal>()
        responseMap["highest-deposit"] = highestDeposit

        return Response.ok()
            .entity(
                HttpResponse().apply {
                    timeStamp = LocalDateTime.now().toString()
                    data = responseMap
                    message = "Highest deposit has been found with successfully"
                    status = Response.Status.OK
                    statusCode = Response.Status.OK.statusCode
                }
            )
        .build()
    }
}