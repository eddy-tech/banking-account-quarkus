package org.banking.account.models

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Entity
@Table(name = "accounts")
@Serializable
class Account : PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accounts_id_seq")
    @SequenceGenerator(name = "accounts_id_seq", sequenceName = "accounts_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    var accountId: Long? = null
    lateinit var iban: String

    @OneToOne
    @JoinColumn(name = "user_id")
    var user: User? = null

    @Column(name = "created_date", nullable = false, updatable = false)
    lateinit var createdDate: LocalDateTime

    @Column(name = "lastModified_date", insertable = false)
    lateinit var lastModifiedDate: LocalDateTime
}



