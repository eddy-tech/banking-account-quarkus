package org.banking.account.models

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "addresses")
class Address(
    addressId: Long?, street: String?, houseNumber: Int?, zipCode: Int?, user: User?,
    city: String?, country: String?,createdDate: LocalDateTime?, lastModifiedDate: LocalDateTime?
) : PanacheEntityBase{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
     var addressId: Long? = null
     var street: String? = null
     var houseNumber: Int? = null
     var zipCode: Int? = null
     var city: String? = null
     var country: String? = null
    @Column(name = "created_date", nullable = false, updatable = false)
    var createdDate: LocalDateTime? = null

    @Column(name = "lastModified_date", insertable = false)
    var lastModifiedDate: LocalDateTime? = null

    @OneToOne
    @JoinColumn(name = "user_id")
     var user: User? = null

    constructor() : this(
        null,null, null, null, null, null, null,
        LocalDateTime.now(), null
    )
}