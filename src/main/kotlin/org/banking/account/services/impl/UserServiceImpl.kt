package org.banking.account.services.impl

import jakarta.enterprise.context.ApplicationScoped
import jakarta.persistence.EntityNotFoundException
import jakarta.persistence.PersistenceException
import jakarta.transaction.Transactional
import org.banking.account.dto.AccountDto
import org.banking.account.dto.UserDto
import org.banking.account.mappers.AccountMapper
import org.banking.account.mappers.UserMapper
import org.banking.account.repositories.UserRepository
import org.banking.account.services.interfaces.AccountService
import org.banking.account.services.interfaces.UserService
import org.banking.account.validators.ObjectValidator

@ApplicationScoped
@Transactional
class UserServiceImpl (
    private var userRepository: UserRepository,
    private var validator: ObjectValidator,
    private var userMapper: UserMapper,
    private var accountMapper: AccountMapper,
    private var accountService: AccountService
) : UserService {
    override fun save(dto: UserDto): UserDto {
        validator.validate(dto)
        val userSave = userMapper.toUserDto(dto)
        val userEmailExist = userSave.email?.let {
            userRepository.findUserByMail(it)
        }

        if(userEmailExist == true)
            throw PersistenceException("A user already exists with the provided email = ${userSave.email}")
        userRepository.persist(userSave)

        return userMapper.fromUser(userSave)
    }

    override fun update(dto: UserDto, id: Long): UserDto {
        val existingUser = userRepository.findById(id)
            ?: throw EntityNotFoundException("User with id = $id has not been found")

        existingUser.let {
            it.firstName = dto.firstName
            it.lastName = dto.lastName
            it.email = dto.email
        }
        validator.validate(dto)
        userRepository.persist(existingUser)

        return userMapper.fromUser(existingUser)
    }

    override fun getAll(): List<UserDto> = userRepository.findAll()
        .stream()
        .map { userMapper.fromUser(it) }
        .toList()

    override fun getById(id: Long): UserDto {
        val userExisting = userRepository.findById(id)
            ?: throw EntityNotFoundException("No user with id = $id has not been found")

        return userMapper.fromUser(userExisting)
    }

    override fun delete(id: Long) {
        getById(id)
        userRepository.deleteById(id)
    }

    override fun validateAccount(id: Long): Long? {
        val userActive = userRepository.findById(id)
            ?: throw EntityNotFoundException("\"No user with id = $id has not been found\"")

        val account = AccountDto().also {
            it.user = this.userMapper.fromUser(userActive)
        }
        accountService.save(account)
        userActive.active = true
        userRepository.persist(userActive)

        return userActive.id
    }

    override fun invalidateAccount(id: Long): Long? {
        val userInvalid = userRepository.findById(id)
            ?: throw EntityNotFoundException("\"No user with id = $id has not been found\"")

        val account = AccountDto().also {
            it.user = this.userMapper.fromUser(userInvalid)
        }
        accountService.save(account)
        userInvalid.active = false
        userRepository.persist(userInvalid)

        return userInvalid.id
    }
}