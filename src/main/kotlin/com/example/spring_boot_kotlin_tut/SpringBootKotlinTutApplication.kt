package com.example.spring_boot_kotlin_tut

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootKotlinTutApplication

fun main(args: Array<String>) {
	runApplication<SpringBootKotlinTutApplication>(*args)
}
