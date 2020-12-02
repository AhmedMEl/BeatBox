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

        beatBox.play(sound)
    }
}