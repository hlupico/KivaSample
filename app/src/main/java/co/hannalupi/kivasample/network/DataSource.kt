package co.hannalupi.kivasample.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.hannalupi.kivasample.model.Lender
import co.hannalupi.kivasample.model.LenderList
import co.hannalupi.kivasample.model.Loan
import co.hannalupi.kivasample.model.LoanList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataSource(val retrofit : RetrofitService) {

    fun fetchLoans(status : String) : LiveData<List<Loan>> {
        val call : Call<LoanList> = retrofit.client.getLoans(status)
        var loans : MutableLiveData<List<Loan>> = MutableLiveData()

        call.enqueue(object : Callback<LoanList> {
            override fun onFailure(call: Call<LoanList>, t: Throwable) {
                // TODO handle error case
            }

            override fun onResponse(call: Call<LoanList>, response: Response<LoanList>) {
                if(response.isSuccessful) {
                    loans.value = response.body()?.loans
                }
                else {
                    // TODO: Handle no data case
                }
            }
        })

        return loans
    }

    fun fetchLoanById(id : Int) : LiveData<Loan> {
        val call : Call<LoanList> = retrofit.client.getLoanById(id)
        var loan : MutableLiveData<Loan> = MutableLiveData()

        call.enqueue(object : Callback<LoanList> {
            override fun onFailure(call: Call<LoanList>, t: Throwable) {
                // TODO handle error case
            }

            override fun onResponse(call: Call<LoanList>, response: Response<LoanList>) {
                if(response.isSuccessful) {
                    loan.value = response.body()?.loans?.get(0)
                }
                else {
                    // TODO Handle error/no data case
                }
            }
        })
        return loan
    }

    fun fetchLenders(id : Int) : LiveData<List<Lender>> {
        val call : Call<LenderList> = retrofit.client.getLendersByLoanId(id)
        var lenders : MutableLiveData<List<Lender>> = MutableLiveData()

        call.enqueue(object : Callback<LenderList> {
            override fun onFailure(call: Call<LenderList>, t: Throwable) {
                // TODO handle error case
            }

            override fun onResponse(call: Call<LenderList>, response: Response<LenderList>) {
                if(response.isSuccessful) {
                    lenders.value = response.body()?.lenders
                }
                else {
                    // TODO: Handle no data case
                }
            }
        })

        return lenders
    }
}