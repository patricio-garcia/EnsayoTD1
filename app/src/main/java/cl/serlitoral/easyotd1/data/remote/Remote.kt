package cl.serlitoral.easyotd1.data.remote

import cl.serlitoral.easyotd1.data.model.Product
import cl.serlitoral.easyotd1.data.model.ProductDetail
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

//https://my-json-server.typicode.com/Himuravidal/FakeAPIdata/products/
//http://my-json-server.typicode.com/Himuravidal/FakeAPIdata/details/1

interface ProductAPI {
    @GET("products")
    suspend fun getProducts(): Response<List<Product>>

    @GET("details/{pid}")
    suspend fun getProduct(@Path("pid") id: Int): Response<ProductDetail>
}

class RetrofitClient {
    companion object {
        private const val BASE_URL = "https://my-json-server.typicode.com/Himuravidal/FakeAPIdata/"

        fun retrofitInstance(): ProductAPI {
            val retrofit = Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ProductAPI::class.java)
        }
    }
}