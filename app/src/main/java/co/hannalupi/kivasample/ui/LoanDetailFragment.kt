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
import co.hannalupi.kivasample.databinding.FragmentLoanBinding
import co.hannalupi.kivasample.model.Loan
import co.hannalupi.kivasample.network.DataSource
import co.hannalupi.kivasample.network.RetrofitService
import co.hannalupi.kivasample.viewmodel.LoanViewModel
import co.hannalupi.kivasample.viewmodel.LoanViewModelFactory

class LoanDetailFragment : Fragment() {

    private lateinit var binding : FragmentLoanBinding

    companion object {
        val ARG_LOAN_ID = "loan_id"

        fun newInstance(loan : Loan) : LoanDetailFragment {
            val args = Bundle()
            args.putInt(ARG_LOAN_ID, loan.id)

            val fragment = LoanDetailFragment()
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_loan, container, false)
        binding.callback = object : SelectedCallback {
            override fun onSelected(item: Any) {
                val lenderFragment = LenderListFragment.newInstance(item as Loan)
                activity?.supportFragmentManager?.beginTransaction()?.add(R.id.fragment_container, lenderFragment, null)?.addToBackStack(lenderFragment.tag)?.commit()
            }
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val model = ViewModelProviders.of(this, LoanViewModelFactory(DataSource(RetrofitService))).get(LoanViewModel::class.java)
        val id = arguments?.getInt(ARG_LOAN_ID)

        if(id != null) {
            observeData(model.getLoan(id))
        }
        // todo handle else case
    }

    private fun observeData(liveData : LiveData<Loan>) {
        liveData.observeForever(object : Observer<Loan> {
            override fun onChanged(t: Loan?) {
                if (t != null) {
                    binding.loan = t
                }
            }
        })
    }
}