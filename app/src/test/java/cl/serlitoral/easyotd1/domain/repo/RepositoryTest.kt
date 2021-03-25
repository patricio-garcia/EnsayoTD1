package cl.serlitoral.easyotd1.domain.repo

import cl.serlitoral.easyotd1.data.model.Product
import cl.serlitoral.easyotd1.domain.db.ProductEntity
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RepositoryTest {

    @Test
    fun db2Api_happyCase() {
        //Given
        val entity = ProductEntity(1, "Xiaomi", 249000, "img1")
        val expected = Product(1, "Xiaomi", 249000, "img1")

        //When
        val result = db2api(entity)

        //Then
        assertThat(result).isNotNull()
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun api2Db_happyCase() {
        //Given
        val product = Product(1, "Xiaomi", 249000, "img1")
        val expected = ProductEntity(1, "Xiaomi", 249000, "img1")

        //When
        val result = api2db(product)

        //Then
        assertThat(result).isNotNull()
        assertThat(result).isEqualTo(expected)
    }
}