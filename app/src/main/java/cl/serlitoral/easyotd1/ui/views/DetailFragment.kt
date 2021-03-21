package cl.serlitoral.easyotd1.ui.views

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

        val product = productVM.getSelected()
        productVM.consumeDetail(product.id)

        productVM.getDetail(product.id).observe(viewLifecycleOwner, {
            it?.let {
                binding.imgProductDetail.load(it.image)
                binding.tvProductNameDetail.text = it.name
                binding.tvPriceProductDetail.text = it.price.toString()
                binding.tvDescProductDetail.text = it.description
            }
        })

        return binding.root
    }

}