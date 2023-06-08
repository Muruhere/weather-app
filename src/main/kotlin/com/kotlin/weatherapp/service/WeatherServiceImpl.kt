package com.kotlin.weatherapp.service

import com.kotlin.weatherapp.model.WeatherReport
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Service
class WeatherServiceImpl() : WeatherService {
    val logger: Logger = LoggerFactory.getLogger(WeatherServiceImpl::class.java)
    private val restTemplate = RestTemplate()

    @Value("\${weather.api.url}")
    lateinit var weatherUrl: String

    @Value("\${weather.api.key}")
    lateinit var key: String

    override fun weatherReport(city: String): WeatherReport? {
        logger.info("Weather searching for city $city")
        val weatherReport = WeatherReport(null, null, null)
        val urlTemplate: String = UriComponentsBuilder.fromHttpUrl(weatherUrl)
                .queryParam("q", city)
                .queryParam("key", key)
                .encode()
                .toUriString()
        try {
            val response: ResponseEntity<WeatherReport> = restTemplate.getForEntity(urlTemplate, WeatherReport::class.java)
            logger.info("Weather report of city $city is ${response.body}")
            if (response.statusCode.is2xxSuccessful) return response.body
        } catch (exception: HttpClientErrorException) {
            logger.error("Error fetching weather details ${exception.message}")
            weatherReport.errorMsg = exception.message
        } catch(exception: Exception) {
            logger.error("There's some problem with fetching the data ${exception.message}")
            weatherReport.errorMsg = exception.message
        }
        return weatherReport
    }

}