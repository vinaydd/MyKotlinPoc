package com.doctoremr.data.error

data class CustomError(
    val errors: List<Error>?= ArrayList()
)