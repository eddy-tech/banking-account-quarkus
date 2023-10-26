package org.banking.account.mappers

import jakarta.enterprise.context.ApplicationScoped
import org.banking.account.dto.UserDto
import org.banking.account.models.User
import java.time.LocalDateTime

@ApplicationScoped
class UserMapper {
    fun fromUser(user: User): UserDto = UserDto().also {
        it.id = user.id
        it.firstName = user.firstName
        it.lastName = user.lastName
        it.email = user.email
        it.password = user.password
    }

    fun toUserDto(userDto: UserDto): User = User().also{
        it.id = userDto.id
        it.firstName = userDto.firstName
        it.lastName = userDto.lastName
        it.email = userDto.email
        it.password = userDto.password
        it.createdDate = LocalDateTime.now()
    }
}