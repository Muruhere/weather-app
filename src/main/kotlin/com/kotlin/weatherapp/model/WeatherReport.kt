package com.kotlin.weatherapp.model

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class WeatherReport(
        var location: Location?,
        var current: Current?,
        var errorMsg: String?
)

data class Location(
        @JsonAlias("name")
        val cityName: String,
        @JsonAlias("region")
        val state: String,
        val country: String
)

data class Current(
        @JsonAlias("temp_c") val tempInC: String,
        @JsonAlias("temp_f") val tempInF: String,
        val condition: Condition,
        var humidity: String)

data class Condition(val text: String)