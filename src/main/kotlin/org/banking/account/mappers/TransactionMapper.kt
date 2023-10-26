package org.banking.account.mappers

import jakarta.enterprise.context.ApplicationScoped
import org.banking.account.dto.TransactionDto
import org.banking.account.models.Transaction
import org.banking.account.models.User
import java.time.LocalDateTime

@ApplicationScoped
class TransactionMapper {
        fun fromTransaction(transaction: Transaction): TransactionDto = TransactionDto().also {
                it.transactionId = transaction.transactionId
                it.amount = transaction.amount
                it.type = transaction.type
                it.destinationIban = transaction.destinationIban
                it.transactionDate = transaction.transactionDate
                it.userId = transaction.user?.id
        }

        fun toTransactionDto(transactionDto: TransactionDto) : Transaction = Transaction().also{
                it.transactionId = transactionDto.transactionId
                it.amount = transactionDto.amount
                it.type = transactionDto.type
                it.destinationIban = transactionDto.destinationIban
                it.transactionDate = transactionDto.transactionDate
                it.user = User().apply { id = transactionDto.userId }
                it.createdDate = LocalDateTime.now()
        }

    }
