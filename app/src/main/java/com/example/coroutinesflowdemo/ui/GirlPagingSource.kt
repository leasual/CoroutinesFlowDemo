package com.example.coroutinesflowdemo.ui

import androidx.paging.PagingSource
import com.example.coroutinesflowdemo.api.GankService
import com.example.coroutinesflowdemo.model.GirlResp
//https://proandroiddev.com/how-to-use-the-paging-3-library-in-android-5d128bb5b1d8
//https://github.com/android/architecture-components-samples/blob/master/PagingWithNetworkSample/app/src/main/java/com/android/example/paging/pagingwithnetwork/reddit/repository/inDb/PageKeyedRemoteMediator.kt
//https://www.youtube.com/watch?v=1cwqGOku2a4&list=PLDA8WhPNyyEBzXWuivlROVjYTLszDXs9j&index=2&t=0s

class GirlPagingSource(
    private val gankService: GankService
): PagingSource<Int, GirlResp>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GirlResp> {
        try {
            val nextPage = params.key ?: 1
            val data = gankService.getGirlPictures(nextPage, 10)
            if (data.isSuccessful && data.body()?.code == 100) {
                val body = data.body()
                val dataList = body?.data ?: return LoadResult.Error(Exception("error"))
                if (dataList.isEmpty()) return LoadResult.Error(Exception("end"))
                return LoadResult.Page(
                    data = dataList,
                    prevKey = if (nextPage == 1) null else nextPage - 1,
                    nextKey = if (dataList.isEmpty() || dataList.size < body.pageCount) null else body.page + 1
                )
            } else {
                return LoadResult.Error(Exception())
            }
        }catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}