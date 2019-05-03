package co.hannalupi.kivasample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import co.hannalupi.kivasample.R
import co.hannalupi.kivasample.databinding.FragmentLoanListBinding
import co.hannalupi.kivasample.model.Loan
import co.hannalupi.kivasample.network.DataSource
import co.hannalupi.kivasample.network.RetrofitService
import co.hannalupi.kivasample.ui.adapter.LoanAdapter
import co.hannalupi.kivasample.viewmodel.LoanListViewModel
import co.hannalupi.kivasample.viewmodel.LoanListViewModelFactory


class LoanListFragment : Fragment() {

    private lateinit var adapter : LoanAdapter
    private lateinit var binding : FragmentLoanListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_loan_list, container, false)
        adapter = LoanAdapter(object : SelectedCallback {
            override fun onSelected(item: Any) {
                val detailFragment = LoanDetailFragment.newInstance(item as Loan)
                activity?.supportFragmentManager?.beginTransaction()?.add(R.id.fragment_container, detailFragment, null)?.addToBackStack(detailFragment.tag)?.commit()
            }
        })
        binding.loanList.setAdapter(adapter)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val model = ViewModelProviders.of(this, LoanListViewModelFactory(DataSource(RetrofitService))).get(LoanListViewModel::class.java)
        observeData(model.getLoans())
    }

    private fun observeData(liveData: LiveData<List<Loan>>) {
        liveData.observeForever(object : Observer<List<Loan>> {
            override fun onChanged(t: List<Loan>?) {
                if (t != null) {
                    adapter.setData(t)
                }
            }
        })
    }
}