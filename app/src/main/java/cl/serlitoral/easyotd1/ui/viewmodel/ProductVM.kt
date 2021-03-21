package cl.serlitoral.easyotd1.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.serlitoral.easyotd1.data.model.Product
import cl.serlitoral.easyotd1.data.model.ProductDetail
import cl.serlitoral.easyotd1.domain.repo.Repository
import kotlinx.coroutines.launch

class ProductVM: ViewModel() {

    private val repo = Repository()

    fun productList(): LiveData<List<Product>> = repo.productList()

    fun getProducts() {
        viewModelScope.launch {
            repo.getProducts()
        }
    }

    fun getDetail(id: Int): LiveData<ProductDetail> = repo.getDetailFromDB(id)

    fun consumeDetail(id: Int) {
        viewModelScope.launch {
            repo.getDetail(id)
        }
    }

    private lateinit var selected: Product

    fun setSelected(product: Product) {
        selected = product
    }

    fun getSelected() = selected
}