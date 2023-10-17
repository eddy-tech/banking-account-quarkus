package org.banking.account.services.interfaces

import org.banking.account.dto.TransactionDto

interface TransactionService: AbstractService<TransactionDto> {
    fun findAllByUser(userId: Long) : List<TransactionDto>
}