package org.banking.account.dto

import org.banking.account.models.User

data class AddressDto(
     var addressId: Long?,
     var street: String?,
     var houseNumber: Int?,
     var zipCode: Int?,
     var city: String?,
     var country: String?,
     var user: User?
)