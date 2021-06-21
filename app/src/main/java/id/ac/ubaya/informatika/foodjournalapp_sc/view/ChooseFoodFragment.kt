package id.ac.ubaya.informatika.foodjournalapp_sc.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ac.ubaya.informatika.foodjournalapp_sc.R

/**
 * A simple [Fragment] subclass.
 * Use the [ChooseFoodFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChooseFoodFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_food, container, false)
    }
}