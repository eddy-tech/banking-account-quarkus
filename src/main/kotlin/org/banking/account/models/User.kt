package org.banking.account.models

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "_users")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
     var id: Long?,
     var firstName: String?,
     var lastName: String?,
    @Column(unique = true)
     var email: String?,
     var password: String?,
     var active: Boolean = false,

    @OneToOne
     var address : Address?,
    @OneToMany(mappedBy = "user")
     var transactionList : MutableList<Transaction>?,
    @OneToMany(mappedBy = "user")
     var contactList : MutableList<Contact>?,
    @OneToOne
     var account: Account?
) : PanacheEntityBase {
    constructor() :
            this (null, null, null, null, null, false, null, null, null, null)
}