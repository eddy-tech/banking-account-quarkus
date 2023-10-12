package org.banking.account.models

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import lombok.experimental.SuperBuilder
import org.banking.account.utils.TransactionType
import java.math.BigDecimal
import java.time.LocalDate

@Data
@SuperBuilder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
class Transaction : PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private var transactionId: Long? = null
    private val amount: BigDecimal? = null

    @Enumerated(EnumType.ORDINAL)
    private val type: TransactionType? = null
    private val destinationIban: String? = null

    @Column(updatable = false)
    private var transactionDate: LocalDate? = null

    @ManyToOne
    @JoinColumn(name = "user_id")
    private lateinit var user: User
}