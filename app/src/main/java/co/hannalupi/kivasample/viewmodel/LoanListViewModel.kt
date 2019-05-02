package co.hannalupi.kivasample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.hannalupi.kivasample.model.Loan
import co.hannalupi.kivasample.network.DataSource

class LoanListViewModel(val dataSource : DataSource) : ViewModel() {

    val status = "funded"

    private val loans : MutableLiveData<List<Loan>> by lazy {
        loadLoans() as MutableLiveData
    }

    private fun loadLoans() : LiveData<List<Loan>> {
        return dataSource.fetchLoans(status)
    }

    fun getLoans() : LiveData<List<Loan>> {
        return loans
    }
}