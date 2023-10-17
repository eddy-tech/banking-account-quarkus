package org.banking.account.dto

import org.banking.account.models.User

data class AccountDto (
     var accountId: Long?,
     var iban: String?,
     var user: User?
)