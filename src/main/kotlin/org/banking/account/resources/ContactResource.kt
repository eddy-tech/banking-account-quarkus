package org.banking.account.resources

import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.banking.account.dto.ContactDto
import org.banking.account.models.HttpResponse
import org.banking.account.roots.RootEndPoint.Companion.ROOT_ENDPOINT
import org.banking.account.services.interfaces.ContactService
import java.net.URI
import java.time.LocalDateTime

@Path("$ROOT_ENDPOINT/contacts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class ContactResource (
    private var contactService: ContactService
) {

    @POST
    fun saveAccount(contactDto: ContactDto): Response{
        val newContact = contactService.save(contactDto)
        val responseMap = HashMap<String, ContactDto>()
        responseMap["contact"] = newContact

        return Response.created(URI.create(""))
            .entity(
                HttpResponse(
                    timeStamp = LocalDateTime.now().toString(),
                    data = responseMap,
                    message = "Contact saved successfully",
                    status = Response.Status.CREATED,
                    statusCode = Response.Status.CREATED.statusCode
                )
            )
            .build()
    }

    @PATCH
    @Path("/{id}")
    fun updateContact (contactDto: ContactDto, @PathParam("id") accountId: Long) : Response{
        val contactUpdate = contactService.update(contactDto, accountId)
        val responseMap = HashMap<String, ContactDto>()
        responseMap["contact"] = contactUpdate

        return Response.ok()
            .entity(
                HttpResponse(
                    timeStamp = LocalDateTime.now().toString(),
                    data = responseMap,
                    message = "Contact has been updated successfully",
                    status = Response.Status.OK,
                    statusCode = Response.Status.OK.statusCode
                )
            )
            .build()
    }

    @GET
    fun getAllContact() : Response {
        val contactList = contactService.getAll()
        val responseMap = HashMap<String, List<ContactDto>>()
        responseMap["contact"] = contactList

        return Response.ok()
            .entity(
                HttpResponse(
                    timeStamp = LocalDateTime.now().toString(),
                    data = responseMap,
                    message = "Lists of contacts",
                    status = Response.Status.OK,
                    statusCode = Response.Status.OK.statusCode
                )
            )
            .build()
    }

    @GET
    @Path("/{id}")
    fun getContactById(@PathParam("id") id : Long) : Response {
        val contact = contactService.getById(id)
        val responseMap = HashMap<String, ContactDto>()
        responseMap["contact"] = contact

        return Response.ok()
            .entity(
                HttpResponse(
                    timeStamp = LocalDateTime.now().toString(),
                    data = responseMap,
                    message = "Get Contact with id = $id has been successfully",
                    status = Response.Status.OK,
                    statusCode = Response.Status.OK.statusCode
                )
            )
            .build()
    }

    @DELETE
    @Path("/{id}")
    fun deleteContact(@PathParam("id") id: Long) : Response {
        val contact = contactService.delete(id)
        val responseMap = HashMap<String, Any>()
        responseMap["contact"] = contact

        return Response.accepted()
            .entity(
                HttpResponse(
                    timeStamp = LocalDateTime.now().toString(),
                    data = responseMap,
                    message = "Contact with id = $id has been deleted with successfully",
                    status = Response.Status.OK,
                    statusCode = Response.Status.OK.statusCode
                )
            )
            .build()
    }
}