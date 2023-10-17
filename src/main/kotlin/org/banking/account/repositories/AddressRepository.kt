package org.banking.account.repositories

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import jakarta.enterprise.context.ApplicationScoped
import org.banking.account.models.Address

@ApplicationScoped
class AddressRepository : PanacheRepositoryBase<Address, Long> {
}