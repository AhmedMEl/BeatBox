package com.example.beatbox

import android.content.Context
import android.content.Context.AUDIO_SERVICE
import android.media.AudioManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class SoundViewModel(val beatBox:BeatBox) : BaseObservable() {
    var sound: Sound? = null
        set(sound) {
            field = sound
            notifyChange()
        }

    @get:Bindable
    val title: String?
        get() = sound?.name

    fun onButtonClicked(){
//       val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
//        lateinit var afChangeListener :AudioManager.OnAudioFocusChangeListener
//
//        val result: Int = audioManager.requestAudioFocus(
//            afChangeListener,
//            // Use the music stream.
//            AudioManager.STREAM_MUSIC,
//            // Request permanent focus.
//            AudioManager.AUDIOFOCUS_GAIN
//        )
//
//        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
//            beatBox.play(sound)
//        }
        beatBox.play(sound)
    }
}