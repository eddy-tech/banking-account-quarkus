package org.banking.account.services.impl

import jakarta.enterprise.context.ApplicationScoped
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.banking.account.dto.AccountDto
import org.banking.account.dto.UserDto
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
    private var accountService: AccountService
) : UserService {
    override fun save(userDto: UserDto): UserDto {
        validator.validate(userDto)
        val userSave = userMapper.toUserDto(userDto)
        userRepository.persist(userSave)

        return userMapper.fromUser(userSave)
    }

    override fun update(userDto: UserDto, id: Long): UserDto {
        val existingUser = userRepository.findById(id)
            ?: throw EntityNotFoundException("User with id = $id has not been found")
        validator.validate(userDto)
        existingUser.let {
            it.firstName = userDto.firstName
            it.lastName = userDto.lastName
            it.email = userDto.email
        }

        return userMapper.fromUser(existingUser)
    }

    override fun getAll(): List<UserDto> = userRepository.findAll()
        .stream()
        .map { userMapper.fromUser(it) }
        .toList()

    override fun getById(id: Long): UserDto = userRepository.findById(id)
        .let {
            if(it != null) {
                userMapper.fromUser(it)
            }
            throw EntityNotFoundException("No user with id = $id has not been found")
        }

    override fun delete(id: Long) {
        getById(id)
        userRepository.deleteById(id)
    }

    override fun validateAccount(id: Long): Long? {
        val user = getById(id)
        val account = AccountDto(
            user = userMapper.toUserDto(user)
        )
        accountService.save(account)
        val userActive = userMapper.toUserDto(user)
        userActive.active = true
        userRepository.persist(userActive)

        return userActive.id
    }

    override fun invalidateAccount(id: Long): Long? {
        val user = getById(id)
        val account = AccountDto(
            user = userMapper.toUserDto(user)
        )
        accountService.save(account)
        val userInvalid = userMapper.toUserDto(user)
        userInvalid.active = false
        userRepository.persist(userInvalid)

        return userInvalid.id
    }
}