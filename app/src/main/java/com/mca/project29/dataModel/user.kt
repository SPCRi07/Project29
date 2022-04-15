package com.mca.project29.dataModel

data class user(
    val id:String?=null,
    val fname: String? = null,
    val lname: String?=null,
    @field:JvmField
    val isVeg: Boolean? = null,
    val city:String? =null,
    val address: String? = null,
    val email:String? =null,
    val mobile: String? = null,
    val password: String? = null,
)