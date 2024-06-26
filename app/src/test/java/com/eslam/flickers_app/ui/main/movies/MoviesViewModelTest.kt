package com.eslam.flickers_app.ui.main.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.eslam.domain.exceptions.LocalExceptions
import com.eslam.domain.model.Movie
import com.eslam.domain.model.YearMovies
import com.eslam.domain.use_case.main.GetMoviesUseCase
import com.eslam.domain.util.DataState
import com.eslam.flickers_app.util.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Mock
    private lateinit var getMoviesUseCase: GetMoviesUseCase

    private lateinit var viewModel: MoviesViewModel

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(getMoviesUseCase)
    }

    @Test
    fun `getMovies should emit Loading and then Success when data is available`() = runTest {
        // Mock the flow returned by getMoviesUseCase
        val movies = listOf(
            Movie(
                cast = listOf("Actor A", "Actor B"),
                year = 2022,
                genres = listOf("Drama", "Action"),
                rating = 4.5f,
                title = "Movie A"
            ),
            Movie(
                cast = listOf("Actor C", "Actor D"),
                year = 2022,
                genres = listOf("Comedy"),
                rating = 4.0f,
                title = "Movie B"
            )
        )
        val yearMovies = listOf(YearMovies(year = 2022, movies = movies))
        val flow = flowOf(yearMovies)
        whenever(getMoviesUseCase(null)).thenReturn(flow)

        // Collect the StateFlow and assert the values
        viewModel.getMoviesResponse.test {
            assertEquals(DataState.Idle, awaitItem()) // Initial state
            viewModel.getMovies()
            assertEquals(DataState.Loading, awaitItem()) // Loading state
            assertEquals(DataState.Success(yearMovies), awaitItem()) // Success state

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `getMovies should emit Loading and then Error when data is empty`() = runTest {
        // Mock the flow returned by getMoviesUseCase
        val flow = flowOf(emptyList<YearMovies>())
        whenever(getMoviesUseCase(null)).thenReturn(flow)

        // Collect the StateFlow and assert the values
        viewModel.getMoviesResponse.test {
            assertEquals(DataState.Idle, awaitItem()) // Initial state
            viewModel.getMovies()
            assertEquals(DataState.Loading, awaitItem()) // Loading state
            assertEquals(
                DataState.Error(LocalExceptions.UnknownException),
                awaitItem()
            ) // Error state

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `getMovies with searchQuery should emit Loading and then Success when data is available`() =
        runTest {
            // Mock the flow returned by getMoviesUseCase
            val movies = listOf(
                Movie(
                    cast = listOf("Actor A", "Actor B"),
                    year = 2022,
                    genres = listOf("Drama", "Action"),
                    rating = 4.5f,
                    title = "Movie A"
                ),
                Movie(
                    cast = listOf("Actor C", "Actor D"),
                    year = 2022,
                    genres = listOf("Comedy"),
                    rating = 4.0f,
                    title = "Movie B"
                )
            )
            val yearMovies = listOf(YearMovies(year = 2022, movies = movies))
            val flow = flowOf(yearMovies)
            whenever(getMoviesUseCase("Movie")).thenReturn(flow)

            // Collect the StateFlow and assert the values
            viewModel.getMoviesResponse.test {
                assertEquals(DataState.Idle, awaitItem()) // Initial state
                viewModel.getMovies("Movie")
                assertEquals(DataState.Loading, awaitItem()) // Loading state
                assertEquals(DataState.Success(yearMovies), awaitItem()) // Success state

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `getMovies with searchQuery should emit Loading and then Error when data is empty`() =
        runTest {
            // Mock the flow returned by getMoviesUseCase
            val flow = flowOf(emptyList<YearMovies>())
            whenever(getMoviesUseCase("Movie")).thenReturn(flow)

            // Collect the StateFlow and assert the values
            viewModel.getMoviesResponse.test {
                assertEquals(DataState.Idle, awaitItem()) // Initial state
                viewModel.getMovies("Movie")
                assertEquals(DataState.Loading, awaitItem()) // Loading state
                assertEquals(
                    DataState.Error(LocalExceptions.UnknownException),
                    awaitItem()
                ) // Error state

                cancelAndIgnoreRemainingEvents()
            }
        }
}