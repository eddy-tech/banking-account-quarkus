package org.banking.account.dto

import jakarta.validation.constraints.*
import org.banking.account.models.User

data class UserDto (
     var id: Long?,

    @NotNull(message = "The firstname must not be empty")
    @NotEmpty(message = "The firstname must not be empty")
    @NotBlank(message = "The firstname must not be empty")
     var firstName: String?,

    @NotNull(message = "The lastname must not be empty")
    @NotEmpty(message = "The lastname must not be empty")
    @NotBlank(message = "The lastname must not be empty")
     var lastName: String?,

    @NotNull(message = "The email must not be empty")
    @NotEmpty(message = "The email must not be empty")
    @NotBlank(message = "The email must not be empty")
    @Email(message = "This email does not the right email")
     var email: String?,

    @NotNull(message = "The password must not be empty")
    @NotEmpty(message = "The password must not be empty")
    @NotBlank(message = "The password must not be empty")
    @Size(min = 8, max = 16, message = "The password must be between 8 and 16 characters")
     var password: String?
)