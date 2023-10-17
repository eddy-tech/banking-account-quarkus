package org.banking.account.services.impl

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.banking.account.dto.AccountDto
import org.banking.account.exceptions.OperationNoPermittedException
import org.banking.account.mappers.AccountMapper
import org.banking.account.models.Account
import org.banking.account.repositories.AccountRepository
import org.banking.account.services.interfaces.AccountService
import org.banking.account.validators.ObjectValidator
import org.iban4j.CountryCode
import org.iban4j.Iban
import java.util.logging.Logger
import java.util.stream.Collectors
import kotlin.streams.toList

@Transactional
@ApplicationScoped
class AccountServiceImpl @Inject constructor(
    private var accountRepository: AccountRepository,
    private var validator: ObjectValidator,
    private var accountMapper : AccountMapper
) : AccountService {
    override fun save(accountDto: AccountDto): AccountDto {
        validator.validate(accountDto)
        val account : Account = accountMapper.toAccountDto(accountDto)
        val userHasAlreadyAccount = accountDto.user?.id?.let { accountRepository.findByUserId(it).isPresent }
        if(userHasAlreadyAccount!! && accountDto.user!!.active) {
            throw OperationNoPermittedException(
                "The selected user has already an active account",
                "Create account",
                "Account service",
                "Account creating"
            )
        }

        account.iban = generateRandomIban()
        accountRepository.persist(account)

        return accountMapper.fromAccount(account)
    }

    override fun update(accountDto: AccountDto, id: Long): AccountDto {
        val existingAccount = accountRepository.findById(id) ?: throw EntityNotFoundException("Account with id $id not found")
        validator.validate(accountDto)
        existingAccount.let {
            it.user = accountDto.user!!
            it.iban = accountDto.iban!!
        }
        accountRepository.persist(existingAccount)

        return accountMapper.fromAccount(existingAccount)
    }

    override fun getAll(): List<AccountDto> = accountRepository.findAll()
        .stream()
        .map { accountMapper.fromAccount(it) }
        .toList()

    override fun getById(id: Long): AccountDto = accountRepository.findById(id)
        .let {
            if (it != null) {
                accountMapper.fromAccount(it)
            }
            throw EntityNotFoundException("No account with id = $id has not been found")
        }

    override fun delete(id: Long) {
       getById(id)
        Logger.getLogger("\"Account exist with this id = $id\"")
       accountRepository.deleteById(id)
    }

    private fun generateRandomIban() : String {
        val iban : String = Iban.random(CountryCode.DE).toFormattedString()
        val existIban : Boolean = accountRepository.findByIban(iban)!!.isPresent
        if(existIban) generateRandomIban()
        Logger.getLogger("Iban has been created $existIban")
        return iban
    }
}