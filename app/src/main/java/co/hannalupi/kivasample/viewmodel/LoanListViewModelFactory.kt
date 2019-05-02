package co.hannalupi.kivasample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.hannalupi.kivasample.network.DataSource

class LoanListViewModelFactory(val dataSource : DataSource) : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoanListViewModel(dataSource) as T
    }
}