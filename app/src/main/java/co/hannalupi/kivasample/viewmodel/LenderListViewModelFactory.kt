package co.hannalupi.kivasample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.hannalupi.kivasample.network.DataSource

class LenderListViewModelFactory(val dataSource: DataSource) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LenderListViewModel(dataSource) as T
    }
}