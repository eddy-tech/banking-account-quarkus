package org.banking.account.exceptions

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_EMPTY)
class ExceptionRepresentation (
    var errorMessage: String? = null,
    var errorSource: String? = null,
    var validationErrors: Set<String>? = emptySet()
)