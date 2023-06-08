package com.kotlin.weatherapp.controller

import com.kotlin.weatherapp.model.WeatherReport
import com.kotlin.weatherapp.service.WeatherService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/v1")
class WeatherController(private val weatherService: WeatherService)
{

    @GetMapping("/weather")
    fun getWeather(@RequestParam city: String): WeatherReport? {
        return weatherService.weatherReport(city)
    }

}