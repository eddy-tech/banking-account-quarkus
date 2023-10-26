package org.banking.account.mappers

import jakarta.enterprise.context.ApplicationScoped
import org.banking.account.dto.ContactDto
import org.banking.account.models.Contact
import org.banking.account.models.User
import java.time.LocalDateTime

@ApplicationScoped
class ContactMapper {
    fun fromContact(contact: Contact) : ContactDto = ContactDto().also {
        it.id = contact.id
        it.firstName = contact.firstName
        it.lastName = contact.lastName
        it.email = contact.email
        it.iban = contact.iban
        it.userId = contact.user?.id
    }

    fun toContactDto(contactDto: ContactDto) : Contact = Contact().also{
        it.id = contactDto.id
        it.firstName = contactDto.firstName
        it.lastName = contactDto.lastName
        it.email = contactDto.email
        it.iban = contactDto.iban
        it.user = User().apply { id = contactDto.userId }
        it.createdDate = LocalDateTime.now()
    }
}