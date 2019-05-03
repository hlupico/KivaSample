package co.hannalupi.kivasample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.hannalupi.kivasample.network.DataSource

class LoanViewModelFactory(val dataSource : DataSource) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoanViewModel(dataSource) as T
    }
}