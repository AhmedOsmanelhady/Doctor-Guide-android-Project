package com.example.lastApp.data

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Page
import androidx.paging.PagingState
import com.example.lastApp.data.api.ApiService
import com.example.lastApp.models.doctor.Data
import com.example.lastApp.util.Constants.DOCTOR_STARTING_PAGE_INDEX
import okio.IOException
import retrofit2.HttpException


class DoctorPagingSource(
    private val apiService: ApiService,
    private val query: String,
) : PagingSource<Int, Data>() {

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        val position = params.key ?: DOCTOR_STARTING_PAGE_INDEX
        return try {
            val response = apiService.getDoctors(query, position, params.loadSize)
            val doctors = response.data

            val page = Page(
                data = doctors,
                prevKey = if (position == DOCTOR_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (doctors.isEmpty()) null else position + 1)
            page
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}

