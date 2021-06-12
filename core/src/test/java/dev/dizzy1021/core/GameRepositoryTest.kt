package dev.dizzy1021.core

import androidx.arch.core.executor.ArchTaskExecutor
import com.google.common.truth.Truth
import dev.dizzy1021.core.data.source.GameRepository
import dev.dizzy1021.core.data.source.local.LocalDataSource
import dev.dizzy1021.core.data.source.local.entity.GameEntity
import dev.dizzy1021.core.data.source.remote.RemoteDataSource
import dev.dizzy1021.core.domain.model.Game
import dev.dizzy1021.core.utils.State
import dev.dizzy1021.core.utils.toModel
import io.kotlintest.IsolationMode
import io.kotlintest.Spec
import io.kotlintest.specs.DescribeSpec
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

@ExperimentalCoroutinesApi
class GameRepositoryTest: DescribeSpec({

    describe("Test Game Repository") {

        val mockRemoteDataSource = mockk<RemoteDataSource>(relaxed = true)
        val mockLocalDataSource = mockk<LocalDataSource>(relaxUnitFun = true)

        val mockDataGames = generateDataGames()
        val mockDataGame = generateDataGame()
        val mockEntityGames = generateEntityGames()
        val mockEntityGame = generateEntityGame()

        val repository = GameRepository(mockRemoteDataSource, mockLocalDataSource)

        it("Call Games") {
            coEvery { mockRemoteDataSource.getGames("TEST") } returns mockDataGames
            val result = repository.callGames("TEST")

            result.collect {
                if(it.state == State.SUCCESS) {
                    Truth.assertThat(it.data).isNotNull()
                    Truth.assertThat(it.data).isEqualTo(mockDataGames.first().data?.toModel())
                }
            }
            coVerify (exactly = 1) { mockRemoteDataSource.getGames("TEST") }
        }

        it("Call Game from Local") {
            coEvery { mockLocalDataSource.getGame(211) } returns mockEntityGame
            val result = repository.callGame(211)

            result.collect {
                if(it.state == State.SUCCESS) {
                    Truth.assertThat(it.data).isNotNull()
                    Truth.assertThat(it.data).isEqualTo(mockEntityGame.first().toModel())
                }
            }
            coVerify (exactly = 1) { mockLocalDataSource.getGame(211) }
        }

        it("Call Game from Remote") {
            coEvery { mockLocalDataSource.getGame(211).firstOrNull() } returns null
            coEvery { mockRemoteDataSource.getGame(211) } returns mockDataGame

            val result = repository.callGame(211)

            result.collect {
                if(it.state == State.SUCCESS) {
                    Truth.assertThat(it.data).isNotNull()
                    Truth.assertThat(it.data).isEqualTo(mockDataGame.first().data?.toModel())
                }
            }
            coVerify (exactly = 1) { mockLocalDataSource.getGame(211) }
            coVerify (exactly = 1) { mockRemoteDataSource.getGame(211) }
        }

        it("Call Favorite Games") {
            coEvery { mockLocalDataSource.getFavoriteGames() } returns mockEntityGames

            val result = repository.getFavoriteGame()

            result.collect {
                Truth.assertThat(it).isNotNull()
            }
            coVerify (exactly = 1) { mockLocalDataSource.getFavoriteGames() }
        }

        it("Add Favorite Games") {
            val game = Game(
                id = 1,
                name = "NAME",
                desc = "DESCRIPTION",
                rating = 0.0,
                poster = null,
                website = null,
                date = null,
                publisher = null,
                publisherPoster = null,
                genres = null,
                screenshot = null,
                isFavorite = true
            )
            val slot = slot<GameEntity>()
            coEvery { mockLocalDataSource.insertGame(capture(slot)) } just runs
            repository.addFavoriteGame(game)
            coVerify (exactly = 1) { mockLocalDataSource.insertGame(capture(slot)) }
        }

        it("Remove Favorite Games") {
            val game = 211
            coEvery { mockLocalDataSource.deleteGame(game) } just runs
            repository.removeFavoriteGame(game)
            coVerify (exactly = 1) { mockLocalDataSource.deleteGame(game) }
        }

    }

}) {
    override fun beforeSpec(spec: Spec) {
        Dispatchers.setMain(Dispatchers.Unconfined)
        ArchTaskExecutor.getInstance().setDelegate(InstantTaskExecutor)
        super.beforeSpec(spec)
    }

    override fun isolationMode(): IsolationMode = IsolationMode.InstancePerTest

    override fun afterSpec(spec: Spec) {
        Dispatchers.resetMain()
        ArchTaskExecutor.getInstance().setDelegate(null)
        super.afterSpec(spec)
    }
}
