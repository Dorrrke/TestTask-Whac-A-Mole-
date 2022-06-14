package com.example.whac_a_mole.di

import com.example.whac_a_mole.fragments.MainMenuFragment
import com.example.whac_a_mole.fragments.ResultFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DatabaseModule::class])
@Singleton
interface MainComponent {
    fun inject (fragment: ResultFragment)
    fun inject (fragment: MainMenuFragment)
}