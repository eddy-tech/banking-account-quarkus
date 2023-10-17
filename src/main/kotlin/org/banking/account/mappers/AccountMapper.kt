package org.banking.account.mappers

import org.banking.account.dto.AccountDto
import org.banking.account.models.Account
import java.time.LocalDateTime

object AccountMapper {
    fun fromAccount(account: Account): AccountDto = AccountDto(
        accountId = account.accountId,
        iban = account.iban,
        user = account.user
    )

    fun toAccountDto(accountDto: AccountDto): Account = Account(
        accountId = accountDto.accountId,
        iban = accountDto.iban,
        user = accountDto.user,
        createdDate = LocalDateTime.now(),
        lastModifiedDate = null
    )
}