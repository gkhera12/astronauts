package com.example.astronauts.datalayer.network

import com.example.astronauts.datalayer.ApiResult
import com.example.astronauts.datalayer.ErrorType
import retrofit2.Call
import java.io.IOException

open class RetrofitCallExecuter<InputType, OutputType> {
    fun execute(
        call: Call<InputType>,
        mapper: Mapper<InputType, OutputType>
    ): ApiResult<OutputType> {
        return try {
            val response = call.execute()
            if (response.isSuccessful) {
                val mappedValue = mapper.map(response.body())
                ApiResult(mappedValue, ErrorType.None)
            } else {
                ApiResult(null, ErrorType.Network)
            }
        } catch (exception: IOException) {
            ApiResult(null, ErrorType.Network)
        }
    }

}