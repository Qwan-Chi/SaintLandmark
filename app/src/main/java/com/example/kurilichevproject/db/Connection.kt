package com.example.kurilichevproject.db

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.DatabaseConfig

fun connectToDB() {
    Database.connect("jdbc:h2:file:/data/data/com.example.kurilichevproject/databases/landmarks.db", driver = "org.h2.Driver",
        databaseConfig = DatabaseConfig {
        defaultRepetitionAttempts = 3
    })


}