package org.banking.account.services.interfaces

import org.banking.account.projections.TransactionSumDetails
import java.math.BigDecimal
import java.time.LocalDate

interface StatisticService {
    fun findSumTransactionByDate (startDate: LocalDate, endDate: LocalDate, userId: Long) : List<TransactionSumDetails>
    fun getAccountBalance (userId : Long?) : BigDecimal?
    fun highestTransfer (userId : Long) : BigDecimal
    fun highestDeposit (userId : Long) : BigDecimal
}