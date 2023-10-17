package org.banking.account.services.interfaces

interface AbstractService <T> {
    fun save(dto: T) : T
    fun update(dto: T, id: Long) : T
    fun getAll(): List<T>
    fun getById(id: Long): T
    fun delete(id: Long)
}