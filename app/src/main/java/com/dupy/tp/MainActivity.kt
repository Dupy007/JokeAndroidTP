package com.dupy.tp


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.dupy.tp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val jokeViewModel: JokeViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        jokeViewModel.joke.observe(this, Observer { joke ->
            binding.jokeTextView.text = joke
        })

        binding.fetchJokeButton.setOnClickListener {
            jokeViewModel.fetchJoke()
        }
    }
}
