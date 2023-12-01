package com.example.kurilichevproject

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.like
import org.jetbrains.exposed.sql.transactions.transaction

object Landmarks: IntIdTable() {
    val title = varchar("title", 255)
    val address = varchar("address", 255)
    val shortDescription = varchar("shortDescription", 255)
    val detaileDescription = varchar("detaileDescription", 255)
    val isFavorite = bool("isFavorite")
    val comment = varchar("comment", 255)
}
object LandmarkImages: IntIdTable() {
    val landmarkId = reference("landmarkId", Landmarks)
    val image = varchar("image", 255)
}

class Landmark(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<Landmark>(Landmarks)
    var title by Landmarks.title
    var address by Landmarks.address
    var shortDescription by Landmarks.shortDescription
    var detaileDescription by Landmarks.detaileDescription
    var isFavorite by Landmarks.isFavorite
    var comment by Landmarks.comment
}

class LandmarkImage(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<LandmarkImage>(LandmarkImages)
    var landmarkId by LandmarkImages.landmarkId
    var image by LandmarkImages.image
}


