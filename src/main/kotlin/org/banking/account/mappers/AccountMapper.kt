package org.banking.account.mappers

import jakarta.enterprise.context.ApplicationScoped
import org.banking.account.dto.AccountDto
import org.banking.account.models.Account
import java.time.LocalDateTime

@ApplicationScoped
class AccountMapper (private val userMapper: UserMapper) {
    fun fromAccount(account: Account): AccountDto = AccountDto().also {
        it.accountId = account.accountId
        it.iban = account.iban
        it.user = account.user?.let { it1 -> userMapper.fromUser(it1) }
    }

    fun toAccountDto(accountDto: AccountDto): Account = Account().also {
        it.accountId = accountDto.accountId
        it.iban = accountDto.iban.toString()
        it.user = accountDto.user?.let { it1 -> userMapper.toUserDto(it1) }
        it.createdDate = LocalDateTime.now()
    }
}