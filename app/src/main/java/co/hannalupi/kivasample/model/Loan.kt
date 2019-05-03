package co.hannalupi.kivasample.model

import com.google.gson.annotations.SerializedName

data class Loan(val id: Int, val name: String, @SerializedName("loan_amount") val loanAmount: Int, val status: String, val description: Map<String, Any>) {

        val descriptionText: String?
                get() = (description["texts"] as Map<String, String>)["en"]
}
