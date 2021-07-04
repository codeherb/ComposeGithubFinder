package io.codeherb.composegithubfinder.presentation.controller

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.codeherb.composegithubfinder.data.model.GithubUser
import io.codeherb.composegithubfinder.domain.model.GithubUserSearchParam
import io.codeherb.composegithubfinder.domain.repository.GithubSearchRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

@HiltViewModel
class Paging3ScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val githubSearchRepository: GithubSearchRepository
): ViewModel() {

    private val _keyword = MutableStateFlow("")

    private lateinit var _source: PagingSource<Int, GithubUser>

    private val _pager = Pager(PagingConfig(30)) {
        object: PagingSource<Int, GithubUser>() {
            override fun getRefreshKey(state: PagingState<Int, GithubUser>): Int? {
                return null
            }

            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubUser> {
                return try {
                    val page = params.key ?: SamplePagingSource.INITIAL_POSITION
                    val size = params.loadSize
                    val keyword = _keyword.value

                    val result = githubSearchRepository.getGithubUsers(
                        GithubUserSearchParam(keyword, page, size)
                    ).flatMapConcat {
                        flowOf(it.items)
                    }.first()
                    LoadResult.Page(
                        data = result,
                        prevKey = params.key,
                        nextKey = (params.key ?: SamplePagingSource.INITIAL_POSITION) + params.loadSize
                    )
                } catch (e: Exception) {
                    LoadResult.Error(e)
                }
            }
        }.also {
            _source = it
        }
    }

    val pagingItems: Flow<PagingData<GithubUser>> = _pager.flow

    suspend fun searchUser(keyword: String) {
        _keyword.emit(keyword)
        _source.invalidate()
    }

}