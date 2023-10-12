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
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import lombok.experimental.SuperBuilder

@Entity
@Table(name = "_users")
@AllArgsConstructor @NoArgsConstructor
@Data
@SuperBuilder
class User : PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private var id: Long? = null
    private val firstName: String? = null
    private val lastName: String? = null
    @Column(unique = true)
    private val email: String? = null
    private val active: Boolean = false

    @OneToOne
    private lateinit var address : Address
    @OneToMany(mappedBy = "user")
    private lateinit var transactionList : MutableList<Transaction>
    @OneToMany(mappedBy = "user")
    private lateinit var contactList : MutableList<Contact>
    @OneToOne
    private lateinit var account: Account
}