package org.banking.account.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Positive
import kotlinx.serialization.Serializable
import org.banking.account.models.User
import org.banking.account.utils.TransactionType
import java.math.BigDecimal
import java.time.LocalDate

@Serializable
data class TransactionDto (
    var transactionId: Long? = null,
    @Positive
    @Max(value = 100000000)
    @Min(value = 10)
    var amount: BigDecimal? = null,
    var type: TransactionType? = null,
    var destinationIban: String? = null,
    var transactionDate: LocalDate? = null,
    var userId: Long? = null
)