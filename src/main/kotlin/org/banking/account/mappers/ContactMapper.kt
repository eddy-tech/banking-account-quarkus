package org.banking.account.mappers

import jakarta.enterprise.context.ApplicationScoped
import org.banking.account.dto.ContactDto
import org.banking.account.models.Contact
import java.time.LocalDateTime

@ApplicationScoped
object ContactMapper {
    fun fromContact(contact: Contact) : ContactDto = ContactDto(
        id = contact.id,
        firstName = contact.firstName,
        lastName = contact.lastName,
        email = contact.email,
        iban = contact.iban,
        user = contact.user
    )

    fun toContactDto(contactDto: ContactDto) : Contact = Contact(
        id = contactDto.id,
        firstName = contactDto.firstName,
        lastName = contactDto.lastName,
        email = contactDto.email,
        iban = contactDto.iban,
        user = contactDto.user,
        createdDate = LocalDateTime.now(),
        lastModifiedDate = null
    )
}