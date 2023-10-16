package org.banking.account.mappers

import org.banking.account.dto.AccountDto
import org.banking.account.models.Account

object AccountMapper {
    fun fromAccount(account: Account): AccountDto = AccountDto(
        accountId = account.accountId,
        iban = account.iban
    )

    fun toAccountDto(accountDto: AccountDto): Account = Account(
        accountId = accountDto.accountId,
        iban = accountDto.iban
    )
}