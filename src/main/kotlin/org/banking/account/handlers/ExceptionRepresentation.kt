package org.banking.account.handlers

data class ExceptionRepresentation(val errorMessage: String?, val errorSource: String?, val validationErrors: Set<String>?)
