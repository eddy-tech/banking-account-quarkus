package org.banking.account.models

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*

@Entity
@Table(name = "contacts")
data class Contact (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
     var id: Long?,
     var firstName: String?,
     var lastName: String?,

    @Column(unique = true)
     var email: String?,
     var iban: String?,

    @ManyToOne
    @JoinColumn(name = "user_id")
     var user: User?
) : PanacheEntityBase {
    constructor() : this(null, null, null, null, null,null)
}