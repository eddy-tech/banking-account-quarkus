package org.banking.account.services.impl

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.banking.account.dto.AddressDto
import org.banking.account.mappers.AddressMapper
import org.banking.account.repositories.AddressRepository
import org.banking.account.services.interfaces.AddressService
import org.banking.account.validators.ObjectValidator

@ApplicationScoped
@Transactional
class AddressServiceImpl(
    private var addressRepository: AddressRepository,
    private var validator: ObjectValidator,
    private var addressMapper: AddressMapper
) : AddressService{
    override fun save(addressDto: AddressDto): AddressDto {
        validator.validate(addressDto)
        val saveAddress = addressMapper.toAddressDto(addressDto)
        addressRepository.persist(saveAddress)

        return addressMapper.fromAddress(saveAddress)
    }

    override fun update(addressDto: AddressDto, id: Long): AddressDto {
       val existingAddress = addressRepository.findById(id)
           ?: throw EntityNotFoundException("No Address with id = $id exists")
        validator.validate(addressDto)
        existingAddress.let {
            it.city = addressDto.city
            it.country = addressDto.country
            it.houseNumber = addressDto.houseNumber
            it.street = addressDto.street
            it.zipCode = addressDto.zipCode
            it.user = addressDto.user
        }
        addressRepository.persist(existingAddress)

        return addressMapper.fromAddress(existingAddress)
    }

    override fun getAll(): List<AddressDto> = addressRepository.findAll()
        .stream()
        .map { addressMapper.fromAddress(it) }
        .toList()

    override fun getById(id: Long): AddressDto = addressRepository.findById(id)
        .let {
            if(it != null) {
                addressMapper.fromAddress(it)
            }
            throw EntityNotFoundException("No address with id = $id has not been found")
        }

    override fun delete(id: Long) {
        getById(id)
        addressRepository.deleteById(id)
    }
}