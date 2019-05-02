package co.hannalupi.kivasample.network

import co.hannalupi.kivasample.model.LenderList
import co.hannalupi.kivasample.model.LoanList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface KivaClient {

    @GET("loans/search.json")
    fun getLoans(@Query("status") status : String) : Call<LoanList>

    @GET("loans/{id}.json")
    fun getLoanById(@Path("id") id : Int) : Call<LoanList>
    // TODO: Changed this to LoanList? Maybe rename pagedata

    @GET("/loans/{id}/lenders")
    fun getLendersByLoanId(@Path("id") id : Int) : Call<LenderList>
    // TODO: Changed this to LenderList? Maybe rename pagedata
}