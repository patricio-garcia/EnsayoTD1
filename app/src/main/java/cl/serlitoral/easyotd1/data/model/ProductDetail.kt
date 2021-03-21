package cl.serlitoral.easyotd1.data.model

data class ProductDetail(
    val id: Int,
    val name: String,
    val price: Long,
    val image: String,
    val description: String,
    val lastPrice: Long,
    val credit: Boolean
)
