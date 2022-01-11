package com.example.exercise.rfit

import com.example.exercise.repository.UserRepository
import com.example.exercise.viewmodel.UserViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule{

    @Provides
    @ViewModelScoped
    fun providesRepo(userRepository: UserRepository): UserViewModel {
        return UserViewModel(userRepository)
    }

}