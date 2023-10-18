package org.banking.account.resources

import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.banking.account.dto.AddressDto
import org.banking.account.models.HttpResponse
import org.banking.account.roots.RootEndPoint.Companion.ROOT_ENDPOINT
import org.banking.account.services.interfaces.AddressService
import java.net.URI
import java.time.LocalDateTime

@Path("$ROOT_ENDPOINT/addresses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class AddressResource (
    private var addressService: AddressService
) {

    @POST
    fun saveAccount(addressDto: AddressDto): Response{
        val newAddress = addressService.save(addressDto)
        val responseMap = HashMap<String, AddressDto>()
        responseMap["address"] = newAddress

        return Response.created(URI.create(""))
            .entity(
                HttpResponse(
                    timeStamp = LocalDateTime.now().toString(),
                    data = responseMap,
                    message = "Address saved successfully",
                    status = Response.Status.CREATED,
                    statusCode = Response.Status.CREATED.statusCode
                )
            )
            .build()
    }

    @PATCH
    @Path("/{id}")
    fun updateAddress (addressDto: AddressDto, @PathParam("id") accountId: Long) : Response{
        val addressUpdate = addressService.update(addressDto, accountId)
        val responseMap = HashMap<String, AddressDto>()
        responseMap["address"] = addressUpdate

        return Response.ok()
            .entity(
                HttpResponse(
                    timeStamp = LocalDateTime.now().toString(),
                    data = responseMap,
                    message = "Address has been updated successfully",
                    status = Response.Status.OK,
                    statusCode = Response.Status.OK.statusCode
                )
            )
            .build()
    }

    @GET
    fun getAllAddress() : Response {
        val addressList = addressService.getAll()
        val responseMap = HashMap<String, List<AddressDto>>()
        responseMap["address"] = addressList

        return Response.ok()
            .entity(
                HttpResponse(
                    timeStamp = LocalDateTime.now().toString(),
                    data = responseMap,
                    message = "Lists of addresses",
                    status = Response.Status.OK,
                    statusCode = Response.Status.OK.statusCode
                )
            )
            .build()
    }

    @GET
    @Path("/{id}")
    fun getAddressById(@PathParam("id") id : Long) : Response {
        val address = addressService.getById(id)
        val responseMap = HashMap<String, AddressDto>()
        responseMap["address"] = address

        return Response.ok()
            .entity(
                HttpResponse(
                    timeStamp = LocalDateTime.now().toString(),
                    data = responseMap,
                    message = "Get Address with id = $id has been successfully",
                    status = Response.Status.OK,
                    statusCode = Response.Status.OK.statusCode
                )
            )
            .build()
    }

    @DELETE
    @Path("/{id}")
    fun deleteAddress(@PathParam("id") id: Long) : Response {
        val address = addressService.delete(id)
        val responseMap = HashMap<String, Any>()
        responseMap["address"] = address

        return Response.accepted()
            .entity(
                HttpResponse(
                    timeStamp = LocalDateTime.now().toString(),
                    data = responseMap,
                    message = "Address with id = $id has been deleted with successfully",
                    status = Response.Status.OK,
                    statusCode = Response.Status.OK.statusCode
                )
            )
            .build()
    }
}