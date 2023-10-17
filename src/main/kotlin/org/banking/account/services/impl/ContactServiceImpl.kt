package org.banking.account.services.impl

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.banking.account.dto.ContactDto
import org.banking.account.mappers.ContactMapper
import org.banking.account.repositories.ContactRepository
import org.banking.account.services.interfaces.ContactService
import org.banking.account.validators.ObjectValidator

@ApplicationScoped
@Transactional
class ContactServiceImpl @Inject constructor(
    private var contactRepository: ContactRepository,
    private var validator: ObjectValidator,
    private var contactMapper: ContactMapper,
) : ContactService {
    override fun save(contactDto: ContactDto): ContactDto {
        validator.validate(contactDto)
        val saveContact = contactMapper.toContactDto(contactDto)
        contactRepository.persist(saveContact)

        return contactMapper.fromContact(saveContact)
    }

    override fun update(contactDto: ContactDto, id: Long): ContactDto {
        val existingContact = contactRepository.findById(id) ?: throw EntityNotFoundException("No contact with id = $id has been found")
        existingContact.let {
            it.firstName = contactDto.firstName
            it.lastName = contactDto.lastName
            it.email = contactDto.email
            it.iban = contactDto.iban
            it.user = contactDto.user
        }
        contactRepository.persist(existingContact)

        return contactMapper.fromContact(existingContact)
    }

    override fun getAll(): List<ContactDto> = contactRepository.findAll()
        .stream()
        .map { contactMapper.fromContact(it) }
        .toList()

    override fun getById(id: Long): ContactDto = contactRepository.findById(id)
        .let {
            if(it != null) {
                contactMapper.fromContact(it)
            }
            throw EntityNotFoundException("No address with id = $id has not been found")
        }

    override fun delete(id: Long) {
        getById(id)
        contactRepository.deleteById(id)
    }
}