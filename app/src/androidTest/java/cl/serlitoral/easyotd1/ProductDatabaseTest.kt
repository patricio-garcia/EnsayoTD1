package cl.serlitoral.easyotd1

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import cl.serlitoral.easyotd1.domain.db.ProductDAO
import cl.serlitoral.easyotd1.domain.db.ProductDatabase
import cl.serlitoral.easyotd1.domain.db.ProductEntity
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProductDatabaseTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var productDao: ProductDAO
    private lateinit var productDB: ProductDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        productDB =Room
                .inMemoryDatabaseBuilder(context, ProductDatabase::class.java)
                .build()

        productDao = productDB.productDao()
    }

    @After
    fun tearDown() {
        productDB.close()
    }

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Truth.assertThat(appContext.packageName).isEqualTo("cl.serlitoral.easyotd1")
    }

    @Test
    fun insertProduct_empry() {
        //Give
        val productList = listOf<ProductEntity>()

        //When
        productDao.insertProducts(productList)

        //Then
        productDao.getProducts().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isEmpty()
        }
    }
}