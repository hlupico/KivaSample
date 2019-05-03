package co.hannalupi.kivasample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import co.hannalupi.kivasample.model.Loan
import co.hannalupi.kivasample.network.DataSource


class LoanViewModel(val dataSource : DataSource) : ViewModel() {

    private fun loadLoan(id : Int) : LiveData<Loan> {
        return dataSource.fetchLoanById(id)
    }

    fun getLoan(id : Int) : LiveData<Loan> {
        return loadLoan(id)
    }

}