package co.hannalupi.kivasample.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.hannalupi.kivasample.model.Loan
import co.hannalupi.kivasample.model.LoanList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataSource(val retrofit : RetrofitService) {

    val tag = "DataSource"

    fun fetchLoans(status : String) : LiveData<List<Loan>> {
        val call : Call<LoanList> = retrofit.client.getLoans(status)
        var loans : MutableLiveData<List<Loan>> = MutableLiveData()

        call.enqueue(object : Callback<LoanList> {
            override fun onFailure(call: Call<LoanList>, t: Throwable) {
                Log.d(tag, call.toString())
            }

            override fun onResponse(call: Call<LoanList>, response: Response<LoanList>) {
                if(response.isSuccessful) {
                    Log.d(tag, "Success")
                    loans.value = response.body()?.loans
                }
                else {
                    // TODO: Handle no data case
                }
            }
        })

        return loans
    }

}