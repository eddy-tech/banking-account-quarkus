package org.banking.account.exceptions

class OperationNoPermittedException(var errorMessage: String, var operationId: String, var source: String, var dependency: String) : RuntimeException()
