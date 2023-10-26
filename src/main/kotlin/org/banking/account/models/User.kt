package org.banking.account.models

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Entity
@Table(name = "_users")
@Serializable
class User : PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_users_id_seq")
    @SequenceGenerator(name = "_users_id_seq", sequenceName = "_users_id_seq", allocationSize = 1)
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

}