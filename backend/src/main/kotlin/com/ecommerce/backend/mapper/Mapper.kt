package com.ecommerce.backend.mapper

interface Mapper<T,U> {

    fun map(t: T) : U
    fun mapList(t: List<T>): List<U>
}