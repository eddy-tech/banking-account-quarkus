package org.banking.account.dto

import kotlinx.serialization.Serializable
import org.banking.account.models.User

@Serializable
data class AddressDto (
     var addressId: Long? = null,
     var street: String? = null,
     var houseNumber: Int? = null,
     var zipCode: Int? = null,
     var city: String? = null,
     var country: String? = null,
     var userId: Long? = null
)