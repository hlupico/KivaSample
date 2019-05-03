package co.hannalupi.kivasample.model

import com.google.gson.annotations.SerializedName

data class Loan(val id: Int, val name: String, val location: HashMap<String, Any>, @SerializedName("loan_amount") val loanAmount: Int, val status: String) {

}

