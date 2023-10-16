package org.banking.account.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Positive
import org.banking.account.models.User
import org.banking.account.utils.TransactionType
import java.math.BigDecimal
import java.time.LocalDate

data class TransactionDto(
    var transactionId: Long?,
    @Positive
    @Max(value = 100000000)
    @Min(value = 10) var amount: BigDecimal?,
    var type: TransactionType?,
    var destinationIban: String?,
    var transactionDate: LocalDate?,
    var user: User?
)
