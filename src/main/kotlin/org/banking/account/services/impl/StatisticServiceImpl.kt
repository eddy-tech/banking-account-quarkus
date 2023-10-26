package org.banking.account.services.impl

import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import org.banking.account.projections.TransactionSumDetails
import org.banking.account.repositories.TransactionRepository
import org.banking.account.services.interfaces.StatisticService
import org.banking.account.utils.TransactionType
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@ApplicationScoped
@Transactional
class StatisticServiceImpl (
    private var transactionRepository: TransactionRepository
) : StatisticService {
    override fun findSumTransactionByDate(
        startDate: LocalDate,
        endDate: LocalDate,
        userId: Long
    ): List<TransactionSumDetails> {
        val start : LocalDateTime = LocalDateTime.of(startDate, LocalTime.of(0,0,0))
        val end : LocalDateTime = LocalDateTime.of(endDate, LocalTime.of(23,59,59))

        return transactionRepository.findSumTransactionByDate(userId, start, end)
    }

    override fun getAccountBalance(userId: Long?): BigDecimal? = userId?.let {
        transactionRepository.findAccountBalance(it)
    }

    override fun highestTransfer(userId: Long): BigDecimal = transactionRepository.findHighestAmountByTransactionType(
        userId,TransactionType.TRANSFER
    )

    override fun highestDeposit(userId: Long): BigDecimal = transactionRepository.findHighestAmountByTransactionType(
        userId, TransactionType.DEPOSIT
    )
}