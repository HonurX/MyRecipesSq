package com.onurgunes.myrecipes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.onurgunes.myrecipes.databinding.FragmentMealListBinding


class MealListFragment : Fragment() {
    private var _binding: FragmentMealListBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMealListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.floatingActionButton.setOnClickListener {
            menuClick(it)
        }

    }

     private fun menuClick (view: View) {
         val action = MealListFragmentDirections.actionMealListFragmentToRecipeAddFragment(id = -1 , bilgi = "yeni")
         Navigation.findNavController(view).navigate(action)

     }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}
