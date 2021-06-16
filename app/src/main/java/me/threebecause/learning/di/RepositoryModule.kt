package me.threebecause.learning.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.threebecause.learning.data.remote.repository.CompetitionRepository
import me.threebecause.learning.data.remote.repository.MatchesRepository
import me.threebecause.learning.data.remote.repository.StandingsRepository
import me.threebecause.learning.data.remote.repository.TeamRepository
import me.threebecause.learning.data.remote.repository.impl.CompetitionRepositoryImpl
import me.threebecause.learning.data.remote.repository.impl.MatchesRepositoryImpl
import me.threebecause.learning.data.remote.repository.impl.StandingRepositoryImpl
import me.threebecause.learning.data.remote.repository.impl.TeamRepositoryImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideCompetitionRepository(
        competitionRepositoryImpl: CompetitionRepositoryImpl
    ): CompetitionRepository = competitionRepositoryImpl

    @Singleton
    @Provides
    fun provideMatchesRepository(
        matchesRepositoryImpl: MatchesRepositoryImpl
    ): MatchesRepository = matchesRepositoryImpl

    @Singleton
    @Provides
    fun provideStandingsRepository(
        standingRepositoryImpl: StandingRepositoryImpl
    ): StandingsRepository = standingRepositoryImpl

    @Singleton
    @Provides
    fun provideTeamRepository(
        teamRepositoryImpl: TeamRepositoryImpl
    ): TeamRepository = teamRepositoryImpl
}