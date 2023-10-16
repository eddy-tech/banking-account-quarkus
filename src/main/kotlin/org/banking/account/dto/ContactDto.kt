package org.banking.account.dto

import org.banking.account.models.User

data class ContactDto(
     var id: Long?,
     var firstName: String?,
     var lastName: String?,
     var email: String?,
     var iban: String?,
     var user: User?
)
