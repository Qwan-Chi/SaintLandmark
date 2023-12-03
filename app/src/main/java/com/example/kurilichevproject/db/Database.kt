package com.example.kurilichevproject.db

import com.example.kurilichevproject.db.LandmarkImage.Companion.referrersOn
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Landmarks: IntIdTable() {
    val title = varchar("title", 255)
    val address = varchar("address", 1000)
    val shortDescription = varchar("shortDescription", 1000)
    val detaileDescription = varchar("detaileDescription", 1000)
    val isFavorite = bool("isFavorite")
    val comment = varchar("comment", 1000)
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
    var landmarkId by Landmarks referencedOn
    var image by LandmarkImages.image
}


