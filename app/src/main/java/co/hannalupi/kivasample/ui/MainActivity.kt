package co.hannalupi.kivasample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.hannalupi.kivasample.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            val fragment = LoanListFragment()
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment, fragment.tag).commit()
        }
    }
}
