package com.wipro.pocapp.data.network.responses.test

import com.doctoremr.data.network.responses.test.TestListResponse
import java.io.Serializable

data class BaseResponse(val title: String ,val  rows :List<TestListResponse>):Serializable