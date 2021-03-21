package cl.serlitoral.easyotd1.domain.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import cl.serlitoral.easyotd1.data.model.Product
import cl.serlitoral.easyotd1.data.model.ProductDetail
import cl.serlitoral.easyotd1.data.remote.RetrofitClient
import cl.serlitoral.easyotd1.domain.db.ProductApplication
import cl.serlitoral.easyotd1.domain.db.ProductDetailEntity
import cl.serlitoral.easyotd1.domain.db.ProductEntity

class Repository {

    private val productDB = ProductApplication.db!!.productDao()

    fun productList(): LiveData<List<Product>> = Transformations.map(productDB.getProducts()) {
        it.map { entity ->
            db2api(entity)
        }
    }

    fun getDetailFromDB(pid: Int): LiveData<ProductDetail> = Transformations.map(productDB.getDetail(pid)) {
        it?.let { detailEntity ->
            db2api(detailEntity)
        }
    }

    suspend fun getProducts() {
        val response = RetrofitClient
            .retrofitInstance()
            .getProducts()

        response.let {
            when(it.isSuccessful) {
                true -> {
                    response.body()?.let { productList ->
                        val map = productList.map { product ->
                            api2db(product)
                        }
                        productDB.insertProducts(map)
                    }
                }
                false -> {
                    Log.d("Repo", "error en Repo")
                }
            }
        }
    }

    suspend fun getDetail(id: Int) {
        val response = RetrofitClient.retrofitInstance().getProduct(id)

        if (response.isSuccessful) {
            response.body()?.let { detail ->
                productDB.insertDetail(api2db(detail))
            }
        } else {
            Log.d("Repo", "error en el detalle ${response.code()}")
        }
    }
}

fun api2db(product: Product): ProductEntity {
    return ProductEntity(product.id, product.name, product.price, product.image)
}

fun db2api(productEntity: ProductEntity): Product {
    return Product(productEntity.id, productEntity.name, productEntity.price, productEntity.image)
}

fun api2db(detail: ProductDetail): ProductDetailEntity {
    return ProductDetailEntity(detail.id, detail.name, detail.price, detail.image, detail.description, detail.lastPrice, detail.credit)
}

fun db2api(detailEntity: ProductDetailEntity): ProductDetail {
    return ProductDetail(detailEntity.id, detailEntity.name, detailEntity.price, detailEntity.image, detailEntity.description, detailEntity.lastPrice, detailEntity.credit)
}