package com.example.astronauts.datalayer.network

interface Mapper<InputType, OutputType> {
    fun map(input: InputType?): OutputType?
}