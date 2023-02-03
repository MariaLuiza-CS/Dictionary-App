package com.example.dictionaryapi.presentation.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.dictionaryapi.R
import com.example.dictionaryapi.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
        initViews()
    }

    private fun initViews() {
        val bottomNavigation = binding.bottomNavigationDictionary
        val navController = findNavController(R.id.fragmentContainerView)

        bottomNavigation.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        when (findNavController(R.id.fragmentContainerView).currentDestination?.id) {
            R.id.wordFragment -> {
                findNavController(R.id.fragmentContainerView).navigate(R.id.action_wordFragment_to_wordListFragment)
            }
            R.id.historyFragment -> {
                findNavController(R.id.fragmentContainerView).navigate(R.id.action_historyFragment_to_wordListFragment)
            }
            R.id.favoritesFragment -> {
                findNavController(R.id.fragmentContainerView).navigate(R.id.action_favoritesFragment_to_wordListFragment)
            }
            else -> {
                super.onBackPressed()
            }
        }
    }
}