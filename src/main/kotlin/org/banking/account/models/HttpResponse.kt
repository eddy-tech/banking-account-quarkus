package org.banking.account.models

import jakarta.ws.rs.core.Response

data class HttpResponse (
     var timeStamp: String, var status: Response.Status,
    var message: String,
    var data : MutableMap<*, *>,
    var statusCode: Int

)