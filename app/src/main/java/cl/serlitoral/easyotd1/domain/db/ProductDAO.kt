package cl.serlitoral.easyotd1.domain.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(product: List<ProductEntity>)

    @Query("SELECT * FROM products")
    fun getProducts(): LiveData<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(detail: ProductDetailEntity)

    @Query("SELECT * FROM product_detail WHERE id=:pid")
    fun getDetail(pid: Int): LiveData<ProductDetailEntity>
}