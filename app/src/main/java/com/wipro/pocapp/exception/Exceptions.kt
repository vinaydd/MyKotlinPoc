package com.wipro.pocapp.exception

import java.io.IOException

class NoInternetException(val errorCode: Int, val _message: String) : IOException(_message)
class ApiException(val errorCode: Int, val _message: String) : IOException(_message)
