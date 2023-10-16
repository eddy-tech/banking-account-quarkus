package org.banking.account.mappers

import org.banking.account.dto.UserDto
import org.banking.account.models.User

object UserMapper {
    fun fromUser(user: User): UserDto = UserDto(
        id = user.id,
        firstName = user.firstName,
        lastName = user.lastName,
        email = user.email,
        password = user.password
    )

    fun toUserDto(userDto: UserDto): User = User(
        id = userDto.id,
        firstName = userDto.firstName,
        lastName = userDto.lastName,
        email = userDto.email,
        password = userDto.password,
        active = false,
        address = null,
        transactionList = mutableListOf(),
        contactList = mutableListOf(),
        account = null
    )
}