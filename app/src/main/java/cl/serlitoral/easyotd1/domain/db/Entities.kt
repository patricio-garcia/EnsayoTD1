package cl.serlitoral.easyotd1.domain.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val price: Long,
    val image: String
)

@Entity(tableName = "product_detail")
data class ProductDetailEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val price: Long,
    val image: String,
    val description: String,
    val lastPrice: Long,
    val credit: Boolean
)