package org.banking.account.models

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Entity
@Table(name = "contacts")
@Serializable
class Contact : PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contacts_id_seq")
    @SequenceGenerator(name = "contacts_id_seq", sequenceName = "contacts_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    var id: Long? = null
    var firstName: String? = null
    var lastName: String? = null

    @Column(unique = true)
    var email: String? = null
    var iban: String? = null

    @Column(name = "created_date", nullable = false, updatable = false)
    var createdDate: LocalDateTime? = null

    @Column(name = "lastModified_date", insertable = false)
    var lastModifiedDate: LocalDateTime? = null

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User? = null
}