package org.banking.account.repositories

import io.quarkus.hibernate.orm.panache.kotlin.PanacheQuery
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import jakarta.enterprise.context.ApplicationScoped
import org.banking.account.models.Transaction
import org.banking.account.projections.TransactionSumDetails
import org.banking.account.utils.TransactionType
import java.math.BigDecimal
import java.time.LocalDateTime

@ApplicationScoped
class TransactionRepository: PanacheRepositoryBase<Transaction, Long> {
    fun findAllByUserId(userId: Long): List<Transaction> = list("user.id", userId)
    fun findAccountBalance(userId: Long): BigDecimal? {
        val queryBalance: PanacheQuery<Transaction> = find(
            "select sum(t.amount) from Transaction t where t.user.id = ?1",
            userId
        )
        return queryBalance as? BigDecimal
    }

    fun findHighestAmountByTransactionType (userId : Long, type: TransactionType): BigDecimal {
        val queryHighest : PanacheQuery<Transaction> = find(
            "select max(abs(t.amount)) as masAmount from Transaction t where t.user.id = ?1 and t.type = ?2",
            userId, type
        )
        return queryHighest
            .project(BigDecimal::class.java)
            .singleResult()
    }

    fun findLowestAmountByTransactionType (userId : Long, type: TransactionType): BigDecimal? {
        val queryHighest : PanacheQuery<Transaction> = find(
            "select min(abs(t.amount)) as minAmount from Transaction t where t.user.id = ?1 and t.type = ?2",
            userId, type
        )
        return queryHighest
            .project(BigDecimal::class.java)
            .firstResult()
    }

    fun findSumTransactionByDate(userId: Long, start: LocalDateTime, end: LocalDateTime): List<TransactionSumDetails> {
        val querySumTransaction: PanacheQuery<Transaction> = find(
            "select t.transactionDate as transactionDate, sum(t.amount) as amount " +
                    "from Transaction t " +
                    "where t.user.id = ?1 and t.createdDate between ?2 and ?3 " +
                    "group by t.transactionDate",
            userId, start, end
        )

        return querySumTransaction
            .project(TransactionSumDetails::class.java)
            .list()
    }

}