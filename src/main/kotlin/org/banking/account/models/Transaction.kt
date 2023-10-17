package org.banking.account.models

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*
import org.banking.account.utils.TransactionType
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime


@Entity
@Table(name = "transactions")
class Transaction (
    transactionId: Long?, amount: BigDecimal?, type: TransactionType?, destinationIban: String?,
    transactionDate: LocalDate?, createdDate: LocalDateTime?, lastModifiedDate: LocalDateTime?,  user: User?
) : PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var transactionId: Long? = null
    var amount: BigDecimal? = null

    @Enumerated(EnumType.ORDINAL)
    var type: TransactionType? = null
    var destinationIban: String? = null

    @Column(updatable = false)
    var transactionDate: LocalDate? = null

    @Column(name = "created_date", nullable = false, updatable = false)
    var createdDate: LocalDateTime? = null

    @Column(name = "lastModified_date", insertable = false)
    var lastModifiedDate: LocalDateTime? = null

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User? = null

    constructor() : this(
        null,null, null, null, null, LocalDateTime.now(),
        null, null
    )

}