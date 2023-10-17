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
import java.time.LocalDateTime

@Entity
@Table(name = "_users")
class User(
    id: Long?, firstName: String?, lastName: String?, email: String?, password: String?, active: Boolean,
    createdDate: LocalDateTime?, lastModifiedDate: LocalDateTime?, address : Address?,
    transactionList : List<Transaction>?, contactList : List<Contact>?, account: Account?
) : PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null
    var firstName: String? = null
    var lastName: String? = null

    @Column(unique = true)
    var email: String? = null
    var password: String? = null
    var active: Boolean = false

    @Column(name = "created_date", nullable = false, updatable = false)
    var createdDate: LocalDateTime? = null

    @Column(name = "lastModified_date", insertable = false)
    var lastModifiedDate: LocalDateTime? = null


    @OneToOne
    var address : Address? = null
    @OneToMany(mappedBy = "user")
    var transactionList : List<Transaction>? = null
    @OneToMany(mappedBy = "user")
    var contactList : List<Contact>? = null
    @OneToOne
    var account: Account? = null

    constructor() : this(
        null,null, null, null, null, false, LocalDateTime.now(), null,
        null, null, null, null
    )

}