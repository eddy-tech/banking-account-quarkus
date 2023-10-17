package org.banking.account.models

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "accounts")
class Account(
    accountId: Long?, iban: String?, user: User?, createdDate: LocalDateTime?, lastModifiedDate: LocalDateTime?
) : PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var accountId: Long? = null
    lateinit var iban: String

    @OneToOne
    @JoinColumn(name = "user_id")
    lateinit var user: User

    @Column(name = "created_date", nullable = false, updatable = false)
    lateinit var createdDate: LocalDateTime

    @Column(name = "lastModified_date", insertable = false)
    lateinit var lastModifiedDate: LocalDateTime

    constructor() : this(null,null, null, LocalDateTime.now(), null)

}



