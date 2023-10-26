package org.banking.account.mappers

import jakarta.enterprise.context.ApplicationScoped
import org.banking.account.dto.AddressDto
import org.banking.account.models.Address
import org.banking.account.models.User
import java.time.LocalDateTime

@ApplicationScoped
class AddressMapper {
    fun fromAddress(address: Address): AddressDto = AddressDto().also {
        it.addressId = address.addressId
        it.street = address.street
        it.city = address.city
        it.country = address.country
        it.houseNumber = address.houseNumber
        it.zipCode = address.zipCode
        it.userId = address.user?.id
    }

    fun toAddressDto(addressDto: AddressDto): Address = Address().also {
        it.addressId = addressDto.addressId
        it.street = addressDto.street
        it.city = addressDto.city
        it.country = addressDto.country
        it.houseNumber = addressDto.houseNumber
        it.zipCode = addressDto.zipCode
        it.user = User().apply { id = addressDto.userId }
        it.createdDate = LocalDateTime.now()
    }
}