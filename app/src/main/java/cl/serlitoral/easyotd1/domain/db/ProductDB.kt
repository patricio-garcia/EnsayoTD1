package cl.serlitoral.easyotd1.domain.db

import android.app.Application
import androidx.room.*

@Database(entities = [ProductEntity::class, ProductDetailEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class ProductDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDAO
}

class ProductApplication: Application() {
    companion object {
        var db: ProductDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        db = Room
            .databaseBuilder(this, ProductDatabase::class.java, "product_db")
            .build()
    }
}