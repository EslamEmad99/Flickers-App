package com.eslam.data.repository.remote

import com.eslam.data.data_source.remote.MainDataSource
import com.eslam.domain.repository.remote.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainDataSource: MainDataSource
) : MainRepository {

}