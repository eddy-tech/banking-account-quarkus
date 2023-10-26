package org.banking.account.dto

import kotlinx.serialization.Serializable
import org.banking.account.models.User

@Serializable
data class ContactDto(
     var id: Long? = null,
     var firstName: String? = null,
     var lastName: String? = null,
     var email: String? = null,
     var iban: String? = null,
     var userId: Long? = null
)
