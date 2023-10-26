package org.banking.account.projections

import java.math.BigDecimal
import java.time.LocalDateTime

data class TransactionSumDetails (val transactionDate: LocalDateTime, val amount: BigDecimal)