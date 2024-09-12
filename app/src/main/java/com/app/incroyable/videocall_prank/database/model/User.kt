package com.app.incroyable.videocall_prank.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.incroyable.videocall_prank.core.utils.memberId
import com.app.incroyable.videocall_prank.database.network.USER_TABLE
import java.io.Serializable

@Entity(tableName = USER_TABLE)
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var type: Int = 0,
    var name: String = "",
    var contact: String = "",
    var image: String = "",
    var video: String = "",
    var emoji: String = ""
) : Serializable

fun testUser(): User {
    return User(
        id = 1,
        type = 0,
        name = "User",
        contact = "1234567890",
        image = memberId + "1",
        video = memberId + "1",
        emoji = "👋"
    )
}

