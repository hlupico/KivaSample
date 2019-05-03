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
import co.hannalupi.kivasample.databinding.FragmentLenderListBinding
import co.hannalupi.kivasample.model.Lender
import co.hannalupi.kivasample.model.Loan
import co.hannalupi.kivasample.network.DataSource
import co.hannalupi.kivasample.network.RetrofitService
import co.hannalupi.kivasample.ui.adapter.LenderAdapter
import co.hannalupi.kivasample.viewmodel.LenderListViewModel
import co.hannalupi.kivasample.viewmodel.LenderListViewModelFactory

class LenderListFragment : Fragment() {

    private lateinit var binding : FragmentLenderListBinding
    private lateinit var adapter : LenderAdapter

    companion object {
        val ARG_LOAN_ID = "loan_id"

        fun newInstance(loan : Loan) : LenderListFragment {
            val args = Bundle()
            args.putInt(ARG_LOAN_ID, loan.id)

            val fragment = LenderListFragment()
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_lender_list, container, false)
        adapter = LenderAdapter()
        binding.lenderList.setAdapter(adapter)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val model = ViewModelProviders.of(this, LenderListViewModelFactory(DataSource(RetrofitService))).get(
            LenderListViewModel::class.java)
        val id = arguments?.getInt(LoanDetailFragment.ARG_LOAN_ID)

        if(id != null) {
            observeData(model.getLenders(id))
        }
    }

    private fun observeData(liveData: LiveData<List<Lender>>) {
        liveData.observeForever(object : Observer<List<Lender>> {
            override fun onChanged(t: List<Lender>?) {
                if (t != null) {
                    adapter.setData(t)
                }
            }
        })
    }
}