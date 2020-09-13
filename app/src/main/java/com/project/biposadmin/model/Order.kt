package com.project.biposadmin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Order(
    var image: String? = "",
    var productId: String? = "",
    var codePos: String = "",
    var deliveryType: String = "",
    var province: String = "",
    var city: String = "",
    var subDistrict: String = "",
    var productName:String = "",
    var orderNumber: String = "",
    var customerName: String = "",
    var customerAddress: String = "",
    var customerPhone: String = "",
    var totalPrice: Int = 0,
    var purchaseType: String = "",
    var resiNumber: String = ""
): Parcelable