package org.banking.account.mappers

import org.banking.account.dto.TransactionDto
import org.banking.account.models.Transaction
import java.time.LocalDateTime

object TransactionMapper {
        fun fromTransaction(transaction: Transaction): TransactionDto = TransactionDto(
                transactionId = transaction.transactionId,
                amount = transaction.amount,
                type = transaction.type,
                destinationIban = transaction.destinationIban,
                transactionDate = transaction.transactionDate,
                user = transaction.user
            )

        fun toTransactionDto(transactionDto: TransactionDto): Transaction = Transaction(
                transactionId = transactionDto.transactionId,
                amount = transactionDto.amount,
                type = transactionDto.type,
                destinationIban = transactionDto.destinationIban,
                transactionDate = transactionDto.transactionDate,
                user = transactionDto.user,
                createdDate = LocalDateTime.now(),
                lastModifiedDate = null
            )
    }
