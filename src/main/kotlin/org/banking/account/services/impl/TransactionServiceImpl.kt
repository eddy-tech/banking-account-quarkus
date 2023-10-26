package org.banking.account.services.impl

import jakarta.enterprise.context.ApplicationScoped
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.banking.account.dto.TransactionDto
import org.banking.account.mappers.TransactionMapper
import org.banking.account.repositories.TransactionRepository
import org.banking.account.services.interfaces.TransactionService
import org.banking.account.utils.TransactionType
import org.banking.account.validators.ObjectValidator
import java.math.BigDecimal
import java.util.logging.Logger

@ApplicationScoped
@Transactional
class TransactionServiceImpl(
    private var transactionRepository: TransactionRepository,
    private var validator: ObjectValidator,
    private var transactionMapper: TransactionMapper
) : TransactionService{
    override fun save(dto: TransactionDto): TransactionDto {
        validator.validate(dto)
        val transaction = transactionMapper.toTransactionDto(dto)
        val transactionMultiplier = transaction.type?.let { getTransactionType(it) }
        val amount = transaction.amount.let {
            it?.multiply(
                BigDecimal.valueOf(transactionMultiplier!!.toLong())
            )
        }
        transaction.amount = amount
        Logger.getLogger("saved Transaction = $transaction")
        transactionRepository.persist(transaction)

        return transactionMapper.fromTransaction(transaction)
    }

    override fun update(dto: TransactionDto, id: Long): TransactionDto {
       val existingTransaction = transactionRepository.findById(id)
           ?: throw EntityNotFoundException("No Transaction with id = $id exists")

        existingTransaction.let {
            it.amount = dto.amount
            it.type = dto.type
            it.user?.id = dto.userId
        }
        validator.validate(dto)
        transactionRepository.persist(existingTransaction)

        return transactionMapper.fromTransaction(existingTransaction)
    }

    override fun getAll(): List<TransactionDto> = transactionRepository.findAll()
        .stream()
        .map { transactionMapper.fromTransaction(it) }
        .toList()

    override fun getById(id: Long): TransactionDto = transactionRepository.findById(id)
        .let {
            if(it != null) {
                transactionMapper.fromTransaction(it)
            }
            throw EntityNotFoundException("No transaction with id = $id has not been found")
        }

    override fun delete(id: Long) {
        getById(id)
        transactionRepository.deleteById(id)
    }

    override fun findAllByUser(userId: Long): List<TransactionDto> = transactionRepository.findAllByUserId(userId)
        .stream()
        .map { transactionMapper.fromTransaction(it) }
        .toList()

    private fun getTransactionType (type: TransactionType) : Long  =  when(type){
        TransactionType.DEPOSIT -> 1
        else -> -1
    }

}