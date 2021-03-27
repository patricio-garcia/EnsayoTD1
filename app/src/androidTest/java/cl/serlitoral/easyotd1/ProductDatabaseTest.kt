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
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProductDatabaseTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var dao: ProductDAO
    private lateinit var db: ProductDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room
                .inMemoryDatabaseBuilder(context, ProductDatabase::class.java)
                .build()

        dao = db.productDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry
                .getInstrumentation()
                .targetContext
        assertThat(appContext.packageName).isEqualTo("cl.serlitoral.easyotd1")
    }

    @Test
    fun insertProduct_empty() = runBlocking {
        //Give
        val productList = listOf<ProductEntity>()

        //When
        dao.insertProducts(productList)

        //Then
        dao.getProducts().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isEmpty()
        }
    }
}