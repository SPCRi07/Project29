package com.mca.project29.dataModel

public data class Product(
    val discount: String? = null,
    val image: String?=null,
    @field:JvmField
    val isVeg: Boolean? = null,
    val name: String? = null,
    val price: String? = null,
    val priceclosed: String? = null,
    val rating: String? = null,

)

