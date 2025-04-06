package com.nikhil.todolist

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.nikhil.todolist.database.Appdatabase
import com.nikhil.todolist.database.Maintask
import com.nikhil.todolist.databinding.FragmentAddNewfragBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddNewfrag.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddNewfrag : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentAddNewfragBinding
    var formatter=SimpleDateFormat("dd-MM-yyyy")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentAddNewfragBinding.inflate(layoutInflater)
        var database=Appdatabase.getInstance(requireContext())
        binding.etdate.setOnClickListener {
            var calendar=Calendar.getInstance()
            val datePickerDialog=DatePickerDialog(
                requireContext(),
                { _, year, month, dayofmonth ->

                    calendar.set(year, month, dayofmonth)
                    var fromatdate = formatter.format(calendar.time)
                    binding.etdate.setText(fromatdate.toString())

                },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
            )
            datePickerDialog.show()

        }
        binding.etenddate.setOnClickListener {
            var calendar1=Calendar.getInstance()
            val datePickerDialog=DatePickerDialog(
                requireContext(),
                { _, year, month, dayofmonth ->

                    calendar1.set(year, month, dayofmonth)
                    var fromatdate = formatter.format(calendar1.time)
                    binding.etenddate.setText(fromatdate.toString())

                },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
            )
            datePickerDialog.show()
        }
        binding.btnAddnewtask.setOnClickListener {
            val title = binding.mail.text.toString().trim()
            val description = binding.etdesc.text.toString().trim()
            val startDate = binding.etdate.text.toString().trim()
            val endDate = binding.etenddate.text.toString().trim()
            if(title.isNotEmpty() && description.isNotEmpty() && startDate.isNotEmpty() && endDate.isNotEmpty() )
            {
                val newtask=Maintask(title=title, description = description, startDate = startDate, endDate = endDate)
                lifecycleScope.launch(Dispatchers.IO) {
                    database.Maindao().insertTask(newtask)
                }
                Toast.makeText(requireContext(), "Task Added!", Toast.LENGTH_SHORT).show()
                requireActivity().onBackPressed()
            }
            else
            {
                Toast.makeText(requireContext(), "Fill all fields!", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddNewfrag.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddNewfrag().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}