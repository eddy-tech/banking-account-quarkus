package org.banking.account.dto

import kotlinx.serialization.Serializable
import org.banking.account.models.User

@Serializable
data class AccountDto (
     var accountId: Long? = null,
     var iban: String? = null,
     var user: UserDto? = null,
)