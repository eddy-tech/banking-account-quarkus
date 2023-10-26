package org.banking.account.dto

import jakarta.validation.constraints.*
import kotlinx.serialization.Serializable

@Serializable
data class UserDto (
    var id: Long? = null,

    @NotNull(message = "The firstname must not be empty")
    @NotEmpty(message = "The firstname must not be empty")
    @NotBlank(message = "The firstname must not be empty")
    var firstName: String? = null,

    @NotNull(message = "The lastname must not be empty")
    @NotEmpty(message = "The lastname must not be empty")
    @NotBlank(message = "The lastname must not be empty")
    var lastName: String? = null,

    @NotNull(message = "The email must not be empty")
    @NotEmpty(message = "The email must not be empty")
    @NotBlank(message = "The email must not be empty")
    @Email(message = "This email does not the right email")
    var email: String? = null,

    @NotNull(message = "The password must not be empty")
    @NotEmpty(message = "The password must not be empty")
    @NotBlank(message = "The password must not be empty")
    @Size(min = 8, max = 16, message = "The password must be between 8 and 16 characters")
    var password: String? = null
)