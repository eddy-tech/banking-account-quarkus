package org.banking.account.models

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Entity
@Table(name = "addresses")
@Serializable
class Address : PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addresses_id_seq")
    @SequenceGenerator(name = "addresses_id_seq", sequenceName = "addresses_id_seq", allocationSize = 1)
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
}