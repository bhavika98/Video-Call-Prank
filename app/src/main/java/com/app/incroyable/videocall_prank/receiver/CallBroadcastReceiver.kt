package com.app.incroyable.videocall_prank.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.app.incroyable.videocall_prank.activity.CallScreenActivity
import com.app.incroyable.videocall_prank.database.model.User
import com.app.incroyable.videocall_prank.database.network.CURRENT_CALL
import com.app.incroyable.videocall_prank.database.network.KEY_CONTACT
import com.app.incroyable.videocall_prank.database.network.KEY_EMOJI
import com.app.incroyable.videocall_prank.database.network.KEY_ID
import com.app.incroyable.videocall_prank.database.network.KEY_IMAGE
import com.app.incroyable.videocall_prank.database.network.KEY_NAME
import com.app.incroyable.videocall_prank.database.network.KEY_TYPE
import com.app.incroyable.videocall_prank.database.network.KEY_VIDEO

class CallBroadcastReceiver : BroadcastReceiver() {

    private var userData: User? = null

    override fun onReceive(context: Context, intent: Intent) {
        userData = User().apply {
            id = intent.getIntExtra(KEY_ID, 0)
            type = intent.getIntExtra(KEY_TYPE, 0)
            name = intent.getStringExtra(KEY_NAME) ?: ""
            contact = intent.getStringExtra(KEY_CONTACT) ?: ""
            image = intent.getStringExtra(KEY_IMAGE) ?: ""
            video = intent.getStringExtra(KEY_VIDEO) ?: ""
            emoji = intent.getStringExtra(KEY_EMOJI) ?: ""
        }
        val vIntent = Intent(context, CallScreenActivity::class.java)
        vIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        vIntent.putExtra(CURRENT_CALL, userData)
        context.startActivity(vIntent)
    }
}
