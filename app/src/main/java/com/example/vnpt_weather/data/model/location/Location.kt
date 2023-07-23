package com.example.vnpt_weather.data.model.location

import com.example.vnpt_weather.data.model.current.Current

data class Location(
//    val AdministrativeArea: AdministrativeArea,
//    val Country: Country,
//    val DataSets: List<String>,
    val EnglishName: String,
//    val GeoPosition: GeoPosition,
//    val IsAlias: Boolean,
//    val Key: String,
//    val LocalizedName: String,
//    val PrimaryPostalCode: String,
//    val Rank: Int,
//    val Region: Region,
//    val SupplementalAdminAreas: List<Any>,
//    val TimeZone: TimeZone,
//    val Type: String,
//    val Version: Int,
    val Current : Current? = null
)