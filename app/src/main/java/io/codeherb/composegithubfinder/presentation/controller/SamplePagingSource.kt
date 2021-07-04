package io.codeherb.composegithubfinder.presentation.controller

import androidx.paging.PagingSource
import androidx.paging.PagingState

class SamplePagingSource(
    private val f: (LoadParams<Int>) -> List<String>
): PagingSource<Int, String>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, String> {
        return try {
            println("@## SamplePagingSource ${params.key} / ${params.loadSize} / ${params.placeholdersEnabled}")
            LoadResult.Page(
                data = f(params),
                prevKey = params.key,
                nextKey = (params.key ?: INITIAL_POSITION) + params.loadSize
            ).also {
                println("@## SamplePagingSource ==> ${it.prevKey} : ${it.nextKey}")
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, String>): Int? {
        return null
    }

    companion object {
        const val INITIAL_POSITION = 1
    }
}