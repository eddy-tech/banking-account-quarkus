package org.banking.account.services.interfaces

import org.banking.account.dto.UserDto

interface UserService : AbstractService<UserDto> {
    fun validateAccount(id: Long) : Long?
    fun invalidateAccount(id: Long) : Long?
}