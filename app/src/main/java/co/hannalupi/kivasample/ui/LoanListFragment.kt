package co.hannalupi.kivasample.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import co.hannalupi.kivasample.model.Loan
import co.hannalupi.kivasample.network.DataSource
import co.hannalupi.kivasample.network.RetrofitService
import co.hannalupi.kivasample.viewmodel.LoanListViewModel
import co.hannalupi.kivasample.viewmodel.LoanListViewModelFactory

class LoanListFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val model = ViewModelProviders.of(this, LoanListViewModelFactory(DataSource(RetrofitService))).get(LoanListViewModel::class.java)
        observeData(model.getLoans())
    }

    private fun observeData(liveData : LiveData<List<Loan>>) {
        liveData.observe(this, object : Observer<List<Loan>> {
            override fun onChanged(t: List<Loan>?) {

            }
        })
    }
}