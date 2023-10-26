package org.banking.account.exceptions

class ObjectValidationException(val violations : Set<String>, val violationSource: String) : RuntimeException()
