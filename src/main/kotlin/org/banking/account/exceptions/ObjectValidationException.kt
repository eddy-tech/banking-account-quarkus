package org.banking.account.exceptions

data class ObjectValidationException(val violations : Set<String>, val violationSource: String) : RuntimeException()
