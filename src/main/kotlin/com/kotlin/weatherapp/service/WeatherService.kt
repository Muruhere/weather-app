package com.kotlin.weatherapp.service

import com.kotlin.weatherapp.model.WeatherReport

interface WeatherService {
    fun weatherReport(city: String): WeatherReport?
}