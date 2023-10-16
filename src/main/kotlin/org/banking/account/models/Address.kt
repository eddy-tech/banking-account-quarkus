package org.banking.account.models

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*

@Entity
@Table(name = "addresses")
data class Address(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
     var addressId: Long?,
     var street: String?,
     var houseNumber: Int?,
     var zipCode: Int?,
     var city: String?,
     var country: String?,

    @OneToOne
    @JoinColumn(name = "user_id")
     var user: User?
) : PanacheEntityBase {
    constructor() : this(null, null, null, null, null, null, null)
}