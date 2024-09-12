package com.app.incroyable.videocall_prank.core.widgets

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.RingtoneManager
import androidx.lifecycle.ViewModel
import com.app.incroyable.videocall_prank.core.utils.callRingtone
import com.app.incroyable.videocall_prank.core.utils.ringtoneMP3

class AudioPlayerViewModel : ViewModel() {
    private var mediaPlayer: MediaPlayer? = null

    fun playSong(context: Context, id: Int, audioPath: String) {
        try {
            mediaPlayer?.reset()
            mediaPlayer = MediaPlayer()

            if (id == 1) {
                val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
                mediaPlayer?.setDataSource(
                    context,
                    RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
                )
                val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_RING)
                val currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_RING)
                val volume = currentVolume.toFloat() / maxVolume.toFloat()
                mediaPlayer?.setVolume(volume, volume)
                mediaPlayer?.prepare()
            } else {
                val path = callRingtone + audioPath + ringtoneMP3
                val descriptor = context.assets.openFd(path)
                mediaPlayer?.apply {
                    setDataSource(
                        descriptor.fileDescriptor,
                        descriptor.startOffset,
                        descriptor.length
                    )
                    descriptor.close()
                    prepare()
                }
            }

            mediaPlayer?.start()
            mediaPlayer?.setOnCompletionListener {
                mediaPlayer?.start()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun pauseSong() {
        mediaPlayer?.stop()
    }

    override fun onCleared() {
        mediaPlayer?.release()
        super.onCleared()
    }
}

