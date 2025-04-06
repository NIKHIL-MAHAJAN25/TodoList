package com.nikhil.todolist

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.nikhil.todolist.database.Appdatabase
import com.nikhil.todolist.database.Maindao
import com.nikhil.todolist.database.Maintask
import com.nikhil.todolist.database.Subtask
import com.nikhil.todolist.databinding.FragmentAddSubfragBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddSubfrag.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddSubfrag : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentAddSubfragBinding
    lateinit var spinner: Spinner
    private var mainTaskIds: List<Int> = listOf()
    lateinit var database: Appdatabase
    var maindao: List<Maintask> = listOf()
//    lateinit var adapter=ArrayAdapter(requireContext())

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
        binding = FragmentAddSubfragBinding.inflate(layoutInflater)

        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        database=Appdatabase.getInstance(requireContext())
//
//        loadmain()
//        binding.addsubtask.setOnClickListener {
//            val title=binding.mail.text.toString().trim()
//            val description=binding.etdescsub.text.toString().trim()
//            val mainrefidindex=binding.spin.selectedItem
//            if(title.isNotEmpty() && description.isNotEmpty())
//            {
//                val mainrefid=mainrefidindex.
//                val subtask=
//                    Subtask(title=title, description = description, mainTaskRefId = mainrefid)
//                lifecycleScope.launch(Dispatchers.IO) {
//                    database.SubDao().insertSubTask(subtask)
//                }
//                Toast.makeText(requireContext(), "Sub Task Added!", Toast.LENGTH_SHORT).show()
//                requireActivity().onBackPressed()
//            }
//            else{
//                Toast.makeText(requireContext(), "Fill all fields!", Toast.LENGTH_SHORT).show()
//            }


//    fun loadmain()
//    {
//
//        maindao=database.Maindao().getAllTasks()
//        val adapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_dropdown_item,maindao)
//                spinner.adapter = adapter
//
//            }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddSubfrag.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddSubfrag().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
