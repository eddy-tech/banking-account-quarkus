package org.banking.account.models

import jakarta.ws.rs.core.Response
import kotlinx.serialization.Serializable

@Serializable
data class HttpResponse (
    var timeStamp: String? = null,
    var status: Response.Status? = null,
    var message: String? = null,
    var data : MutableMap<*, *>? = null,
    var statusCode: Int? = null
)