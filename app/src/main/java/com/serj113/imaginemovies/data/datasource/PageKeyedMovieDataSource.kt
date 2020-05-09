package com.serj113.imaginemovies.data.datasource

import androidx.paging.PageKeyedDataSource
import com.serj113.imaginemovies.data.model.Response

class PageKeyedMovieDataSource : PageKeyedDataSource<Int, Response>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Response>
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Response>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Response>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}