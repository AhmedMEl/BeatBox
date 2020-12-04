package com.example.beatbox

import android.content.Context
import android.media.AudioManager
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beatbox.databinding.ActivityMainBinding
import com.example.beatbox.databinding.ListItemSoundBinding


class MainActivity : AppCompatActivity() {
    private var myAudioManager: AudioManager? = null
    private lateinit var beatBox: BeatBox

    private lateinit var speedControlBar: SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beatBox = BeatBox(assets)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = SoundAdapter(beatBox.sounds)
        }
        myAudioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager

        speedControlBar = findViewById(R.id.seekbar)
        speedControlBar.setProgress(50)
        speedControlBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar, p1: Int, p2: Boolean) {
                    val speed = if (p1 < p0.max / 2) p1 * 0.01F + 0.5F else p1 * 0.02f
                    beatBox.playbackSpeed = speed
                    Log.d("MAIN", speed.toString())
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {

                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                }
            })
    }

    private inner class SoundHolder(private val binding: ListItemSoundBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.viewModel = SoundViewModel(beatBox)
        }
        fun bind(sound: Sound) {
            binding.apply {
                viewModel?.sound = sound
                executePendingBindings()
            }
        }

    }

    private inner class SoundAdapter(private val sounds: List<Sound>) :
        RecyclerView.Adapter<SoundHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
                SoundHolder {
            val binding = DataBindingUtil.inflate<ListItemSoundBinding>(
                layoutInflater,
                R.layout.list_item_sound,
                parent,
                false
            )
            return SoundHolder(binding)
        }
        override fun onBindViewHolder(holder: SoundHolder, position: Int) {
            val sound = sounds[position]
            holder.bind(sound)

        }
        override fun getItemCount() =  sounds.size
    }

    override fun onDestroy() {
        super.onDestroy()
        beatBox.release()
    }


}