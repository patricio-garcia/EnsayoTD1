package cl.serlitoral.easyotd1.ui.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import cl.serlitoral.easyotd1.databinding.FragmentDetailBinding
import cl.serlitoral.easyotd1.ui.viewmodel.ProductVM
import coil.load

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val productVM: ProductVM by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailBinding.inflate(inflater)

        binding.chkCredit.isClickable = false

        val product = productVM.getSelected()
        productVM.consumeDetail(product.id)

        productVM.getDetail(product.id).observe(viewLifecycleOwner, {
            it?.let {
                binding.imgProductDetail.load(it.image)
                binding.tvProductNameDetail.text = it.name
                binding.tvPriceProductDetail.text = "$".plus(it.price.toString())
                binding.chkCredit.isChecked = it.credit
                if (binding.chkCredit.isChecked) {
                    binding.chkCredit.setText("Acepta Tarjeta de Crédito")
                } else {
                    binding.chkCredit.setText("No acepta Tarjeta de Crédito")
                }
                binding.tvDescProductDetail.text = it.description
            }
        })

        binding.fabEmailSend.setOnClickListener {
            val recipiens = arrayOf("info@plaplix.cl")
            val subject = "CONSULTA ${product.name} id ${product.id}"
            val message = "Hola\n" +
                    "Vi el producto ${product.name} y me gustaría que me contactaran a este correo o al\n" +
                    "siguiente número _________(indique número aquí)\n" +
                    "Quedo atento.”\n"

            composeEmail(recipiens, subject, message)
        }

        return binding.root
    }

    fun composeEmail(recipiens:Array<String>, subject: String, message: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, recipiens)
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, message)
        }

        startActivity(intent)

    }

}