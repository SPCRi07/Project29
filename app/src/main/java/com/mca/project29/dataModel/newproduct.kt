package com.mca.project29.dataModel

public data class newProduct(
    val discount: String? = null,
    val image: String?=null,
    @field:JvmField
    val isVeg: Boolean? = null,
    val keywords:List<String>? =null,
    val name: String? = null,
    val price: String? = null,
    val priceclosed: String? = null,
    val rating: String? = null,
    var type:String?=null,
    )

