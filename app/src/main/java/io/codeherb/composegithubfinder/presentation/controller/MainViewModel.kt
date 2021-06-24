package io.codeherb.composegithubfinder.presentation.controller

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf

class MainViewModel: ViewModel() {

    private val _cities = listOf("서울특별시", "부산광역시", "인천광역시", "대구광역시", "대전광역시", "광주광역시", "경기도 수원시", "울산광역시", "경기도 고양시", "경기도 용인시")
    private val _cache = mutableListOf<String>()
    private val _states = MutableStateFlow(0)
    val items: Flow<List<String>> by lazy {
        _states.flatMapLatest {
            val start = 50 * it
            val end = start + 50
            _cache.addAll((start until end).map {
                "[$it] ${_cities[it % _cities.size]}"
            })
            flowOf(_cache.toList())
        }
    }

    suspend fun loadMore() {
        _states.emit(++_states.value)
    }

    val pagingItems: Flow<PagingData<String>> = Pager(PagingConfig(20)) {
        SamplePagingSource { params ->
            val page = params.key ?: SamplePagingSource.INITIAL_POSITION
            val size = params.loadSize
            (page until (page + size)).map {
                "[$it] ${_cities[it % _cities.size]}"
            }
        }
    }.flow

    fun searchUser(keyword: String) {

    }

}
