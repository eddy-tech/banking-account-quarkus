package org.banking.account.repositories

import io.quarkus.hibernate.orm.panache.kotlin.PanacheQuery
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import jakarta.enterprise.context.ApplicationScoped
import org.banking.account.models.Account
import java.util.Optional

@ApplicationScoped
class AccountRepository: PanacheRepositoryBase<Account, Long> {
    fun findByIban (iban: String) : Account? = find("iban", iban).firstResult()
    fun findByUserId(userId: Long) : Optional<Account> {
        val queryAccount : Account? = find("userId", userId).firstResult()
        return Optional.ofNullable(queryAccount)
    }
}