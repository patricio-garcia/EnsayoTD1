package cl.serlitoral.easyotd1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import cl.serlitoral.easyotd1.databinding.ActivityMainBinding
import cl.serlitoral.easyotd1.ui.viewmodel.ProductVM

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val productVM: ProductVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        productVM.getProducts()
    }
}