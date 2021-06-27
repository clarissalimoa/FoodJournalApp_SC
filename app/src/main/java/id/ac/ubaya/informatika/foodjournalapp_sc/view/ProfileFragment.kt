package id.ac.ubaya.informatika.foodjournalapp_sc.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import id.ac.ubaya.informatika.foodjournalapp_sc.R
import id.ac.ubaya.informatika.foodjournalapp_sc.databinding.FragmentLogMealBinding
import id.ac.ubaya.informatika.foodjournalapp_sc.viewmodel.DetailFoodViewModel
import id.ac.ubaya.informatika.foodjournalapp_sc.viewmodel.DetailUserViewModel
import kotlinx.android.synthetic.main.fragment_log_meal.*
import java.text.SimpleDateFormat
import java.util.*


class ProfileFragment : Fragment(), ButtoneditUserClick{

    private lateinit var viewModel2:DetailUserViewModel
    private lateinit var dataBinding: FragmentLogMealBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel2 = ViewModelProvider(this).get(DetailUserViewModel::class.java)

       // dataBinding.listener = this

        val sdf = SimpleDateFormat("dd MMMM yyyy")
        val currentDate = sdf.format(Date())
        textViewFLTanggal.text = currentDate

        val sdf1 = SimpleDateFormat("yyyy-MM-dd")
        val currentDate1 = sdf1.format(Date())

        viewModel2.fetchCurrentUser()

        observeViewModel()

    }

    private fun observeViewModel() {

        viewModel2.userLD.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            dataBinding.user = it
        })

    }

    override fun onButtonEditUserClick(v: View) {

    }

}