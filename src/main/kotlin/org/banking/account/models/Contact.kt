package org.banking.account.models

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "contacts")
class Contact(
    id: Long?, firstName: String?, lastName: String?, email: String?, iban: String?,
    createdDate: LocalDateTime?, lastModifiedDate: LocalDateTime?, user: User?
) : PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    constructor() : this(null,null, null, null, null, LocalDateTime.now(), null, null)

}