package org.banking.account.repositories

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import jakarta.enterprise.context.ApplicationScoped
import org.banking.account.models.Contact

@ApplicationScoped
class ContactRepository: PanacheRepositoryBase<Contact, Long> {
    fun findAllByUserId(userId: Long): List<Contact> = list(
        "select c from Contact c where c.user.id = ?1",
        userId
    )
}