package org.banking.account.models

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*
import org.banking.account.utils.TransactionType
import java.math.BigDecimal
import java.time.LocalDate


@Entity
@Table(name = "transactions")
data class Transaction (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false) var transactionId: Long?,
    var amount: BigDecimal?,

    @Enumerated(EnumType.ORDINAL) var type: TransactionType?,
    var destinationIban: String?,

    @Column(updatable = false) var transactionDate: LocalDate?,

    @ManyToOne
    @JoinColumn(name = "user_id") var user: User?
) : PanacheEntityBase {
    constructor() : this (null, null, null, null, null,null)
}