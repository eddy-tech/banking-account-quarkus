package org.banking.account.resources

import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.banking.account.dto.UserDto
import org.banking.account.models.HttpResponse
import org.banking.account.roots.RootEndPoint.Companion.ROOT_ENDPOINT
import org.banking.account.services.interfaces.UserService
import java.net.URI
import java.time.LocalDateTime

@Path("$ROOT_ENDPOINT/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class UserResource (
    private var userService: UserService
) {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun saveAccount(userDto: UserDto): Response{
        val newUser = userService.save(userDto)
        val responseMap = HashMap<String, UserDto>()
        responseMap["user"] = newUser

        return Response.created(URI.create(""))
            .entity(
                HttpResponse().apply {
                    timeStamp = LocalDateTime.now().toString()
                    data = responseMap
                    message = "User saved successfully"
                    status = Response.Status.CREATED
                    statusCode = Response.Status.CREATED.statusCode
                }
            )
        .build()
    }

    @PATCH
    @Path("/{id}")
    fun updateUser (userDto: UserDto, @PathParam("id") userId: Long) : Response{
        val userUpdate = userService.update(userDto, userId)
        val responseMap = HashMap<String, UserDto>()
        responseMap["user"] = userUpdate

        return Response.ok()
            .entity(
                HttpResponse().apply {
                    timeStamp = LocalDateTime.now().toString()
                    data = responseMap
                    message = "User has been updated successfully"
                    status = Response.Status.OK
                    statusCode = Response.Status.OK.statusCode
                }
            )
        .build()
    }

    @GET
    fun getAllUser() : Response {
        val userList = userService.getAll()

        return Response.ok()
            .entity(userList)
        .build()
    }

    @GET
    @Path("/{id}")
    fun getUserById(@PathParam("id") id : Long) : Response {
        val user = userService.getById(id)
        val responseMap = HashMap<String, UserDto>()
        responseMap["user"] = user

        return Response.ok()
            .entity(
                HttpResponse().apply {
                    timeStamp = LocalDateTime.now().toString()
                    data = responseMap
                    message = "Get User with id = $id has been successfully"
                    status = Response.Status.OK
                    statusCode = Response.Status.OK.statusCode
                }
            )
        .build()
    }

    @DELETE
    @Path("/{id}")
    fun deleteUser(@PathParam("id") id: Long) : Response {
        val user = userService.delete(id)
        val responseMap = HashMap<String, Any>()
        responseMap["user"] = user

        return Response.accepted()
            .entity(
                HttpResponse().apply {
                    timeStamp = LocalDateTime.now().toString()
                    data = responseMap
                    message = "User with id = $id has been deleted with successfully"
                    status = Response.Status.NO_CONTENT
                    statusCode = Response.Status.NO_CONTENT.statusCode
                }
            )
        .build()
    }

    @PATCH
    @Path("/validate/{id}")
    fun validateAccount(@PathParam("id") id: Long) : Response {
        val userId = userService.validateAccount(id)
        val responseMap = HashMap<String, Long?>()
        responseMap["user"] = userId

        return Response.ok()
            .entity(
                HttpResponse().apply {
                    timeStamp = LocalDateTime.now().toString()
                    data = responseMap
                    message = "User with id = $id has been validated with successfully"
                    status = Response.Status.OK
                    statusCode = Response.Status.OK.statusCode
                }
            )
        .build()
    }

    @PATCH
    @Path("/invalidate/{id}")
    fun invalidateAccount(@PathParam("id") id: Long) : Response {
        val userId = userService.invalidateAccount(id)
        val responseMap = HashMap<String, Long?>()
        responseMap["user"] = userId

        return Response.ok()
            .entity(
                HttpResponse().apply {
                    timeStamp = LocalDateTime.now().toString()
                    data = responseMap
                    message = "User with id = $id has been invalidated with successfully"
                    status = Response.Status.OK
                    statusCode = Response.Status.OK.statusCode
                }
            )
        .build()
    }
}