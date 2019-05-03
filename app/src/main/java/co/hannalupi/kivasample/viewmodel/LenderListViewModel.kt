package co.hannalupi.kivasample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import co.hannalupi.kivasample.model.Lender
import co.hannalupi.kivasample.network.DataSource

class LenderListViewModel(val dataSource : DataSource) : ViewModel() {

    private fun loadLenders(id : Int) : LiveData<List<Lender>> {
        return dataSource.fetchLenders(id)
    }

    fun getLenders(id : Int) : LiveData<List<Lender>> {
        return loadLenders(id)
    }

}