package org.banking.account.repositories

import io.quarkus.hibernate.orm.panache.kotlin.PanacheQuery
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import jakarta.enterprise.context.ApplicationScoped
import org.banking.account.models.User

@ApplicationScoped
class UserRepository : PanacheRepositoryBase<User, Long> {
    fun findUserByMail (email: String) : User? = find("email", email).firstResult()

    fun findAllByAccountIban(iban: String) : List<User> {
        val query: PanacheQuery<User> = find(
            "from User u inner join Account a on u.id = a.user.id and a.iban = ?1",
            iban
        )
        return query.list()
    }

    fun searchByFirstName(firstName: String) : List<User> {
        val queryFirstName : PanacheQuery<User> = find(
            "from User u where u.firstName = ?1",
            firstName
        )
        return queryFirstName.list()
    }

}
