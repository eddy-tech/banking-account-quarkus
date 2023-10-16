package org.banking.account.mappers

import org.banking.account.dto.AddressDto
import org.banking.account.models.Address

object AddressMapper {
    fun fromAddress(address: Address): AddressDto = AddressDto(
        addressId = address.addressId,
        street = address.street,
        city = address.city,
        country = address.country,
        houseNumber = address.houseNumber,
        zipCode = address.zipCode,
        user = address.user
    )

    fun toAddressDto(addressDto: AddressDto): Address = Address(
        addressId = addressDto.addressId,
        street = addressDto.street,
        city = addressDto.city,
        country = addressDto.country,
        houseNumber = addressDto.houseNumber,
        zipCode = addressDto.zipCode,
        user = addressDto.user
    )
}