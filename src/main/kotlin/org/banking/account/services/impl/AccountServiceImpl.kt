package org.banking.account.services.impl

import jakarta.enterprise.context.ApplicationScoped
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.banking.account.dto.AccountDto
import org.banking.account.exceptions.OperationNoPermittedException
import org.banking.account.mappers.AccountMapper
import org.banking.account.mappers.UserMapper
import org.banking.account.models.Account
import org.banking.account.repositories.AccountRepository
import org.banking.account.services.interfaces.AccountService
import org.banking.account.validators.ObjectValidator
import org.iban4j.CountryCode
import org.iban4j.Iban
import java.util.logging.Logger


@Transactional
@ApplicationScoped
class AccountServiceImpl (
    private var accountRepository: AccountRepository,
    private var validator: ObjectValidator,
    private var accountMapper : AccountMapper,
    private var userMapper: UserMapper
) : AccountService {
    override fun save(dto: AccountDto): AccountDto {
        validator.validate(dto)
        val account : Account = accountMapper.toAccountDto(dto)
        val userHasAlreadyAccount = dto.user?.id?.let { accountRepository.findByUserId(it).isPresent }
        if(userHasAlreadyAccount!! && account.user?.active == true) {
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

    override fun update(dto: AccountDto, id: Long): AccountDto {
        val existingAccount = accountRepository.findById(id)
            ?: throw EntityNotFoundException("Account with id = $id has not been not found")

        existingAccount.let {
            it.user = dto.user?.let { it1-> userMapper.toUserDto(it1) }
            it.iban = dto.iban!!
        }
        validator.validate(dto)
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