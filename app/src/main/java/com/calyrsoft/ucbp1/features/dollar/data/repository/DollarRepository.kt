package com.calyrsoft.ucbp1.features.dollar.data.repository

import com.calyrsoft.ucbp1.features.dollar.data.datasource.DollarLocalDataSource
import com.calyrsoft.ucbp1.features.dollar.data.datasource.RealTimeRemoteDataSource
import com.calyrsoft.ucbp1.features.dollar.domain.model.DollarModel
import com.calyrsoft.ucbp1.features.dollar.domain.repository.IDollarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach


class DollarRepository(
    val realTimeRemoteDataSource: RealTimeRemoteDataSource,
    val localDataSource: DollarLocalDataSource
): IDollarRepository {


    override fun getDollarFromFireBaseInMyLocalDB(): Flow<DollarModel> {
//        return flow {
//            emit(DollarModel("123", "456"))
//        }
        return realTimeRemoteDataSource.getDollarUpdates().onEach { model ->
            val enriched = model.copy(
                dollarUsdt = model.dollarUsdt ?: "7.05",
                dollarUsdc = model.dollarUsdc ?: "7.07"
            )
            localDataSource.insert(enriched)
        }

//        return realTimeRemoteDataSource.getDollarUpdates()



    }

    override suspend fun getHistoryOfDollarsFromMyLocalDB(): Result<List<DollarModel>> {

        val history=  localDataSource.getList()
        return if (history != null) {
            Result.success(history)
        } else {

            Result.failure(Exception("Usuario o contraseña incorrectos"))
        }




    }

    override suspend fun deleteByTimestamp(timestamp: Long): Result<Unit> {
        return try {
            localDataSource.deleteByTimestamp(timestamp)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
