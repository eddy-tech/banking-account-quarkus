package org.banking.account.repositories

import io.quarkus.hibernate.orm.panache.kotlin.PanacheQuery
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import jakarta.enterprise.context.ApplicationScoped
import org.banking.account.models.Account
import java.util.Optional

@ApplicationScoped
class AccountRepository: PanacheRepositoryBase<Account, Long> {
    fun findByIban (iban: String) : Optional<Account>?{
        val queryIban : Account? = find("iban = ?1", iban).firstResult()
        return Optional.ofNullable(queryIban)
    }

    fun findByUserId(id: Long) : Optional<Account> {
        val queryAccount : Account? = find("user.id = ?1", id).firstResult()
        return Optional.ofNullable(queryAccount)
    }
}