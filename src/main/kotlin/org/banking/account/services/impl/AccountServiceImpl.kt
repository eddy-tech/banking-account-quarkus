package org.banking.account.services.impl

import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import org.banking.account.dto.AccountDto
import org.banking.account.exceptions.OperationNoPermittedException
import org.banking.account.mappers.AccountMapper
import org.banking.account.models.Account
import org.banking.account.repositories.AccountRepository
import org.banking.account.services.interfaces.AccountService
import org.banking.account.validators.ObjectValidator

@Transactional
@ApplicationScoped
class AccountServiceImpl(
    private var accountRepository: AccountRepository,
    private var validator: ObjectValidator,
    private var accountMapper : AccountMapper
) : AccountService {
    override fun save(accountDto: AccountDto): AccountDto {
        validator.validate(accountDto)
        val account : Account = accountMapper.toAccountDto(accountDto)
        val userHasAlreadyAccount = accountRepository.findByUserId(account.user.id ?: 0).isPresent
        if(userHasAlreadyAccount && account.user.active) {
            throw OperationNoPermittedException(
                "The selected user has already an active account",
                "Create account",
                "Account service",
                "Account creating"
            )
        }

        accountRepository.persist(account)
        return accountMapper.fromAccount(account)
    }

    override fun getAll(): List<AccountDto> {
        TODO("Not yet implemented")
    }

    override fun getById(id: Long): AccountDto {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long): Void {
        TODO("Not yet implemented")
    }
}