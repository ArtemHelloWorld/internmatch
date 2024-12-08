package com.example.mobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile.serializers.Vacancy


class VacanciesList : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_vacancies_list, container, false)
        val vacanciesList: RecyclerView = view.findViewById(R.id.vacanciesList)
        val editTextSearch: EditText = view.findViewById(R.id.editTextSearch)
        val buttonSearch: Button = view.findViewById(R.id.buttonSearch)

        val vacancies = ArrayList<Vacancy>()

        val api = Api(view.context)
        api.getVacanciesList() { vacanciesObjs ->
            activity?.runOnUiThread(Runnable {

                for (vacanciesObj in vacanciesObjs) {
                    vacancies.add(vacanciesObj)
                }
                vacanciesList.layoutManager = LinearLayoutManager(requireContext())
                vacanciesList.adapter = VacanciesAdapter(vacancies, requireContext())
            })
        }

        buttonSearch.setOnClickListener {
            val searchString = editTextSearch.text.toString()
            Toast.makeText(context, searchString, Toast.LENGTH_SHORT).show()
            api.getVacanciesList(searchString) { vacanciesObjs ->
                activity?.runOnUiThread(Runnable {
                    vacancies.clear()
                    for (vacanciesObj in vacanciesObjs) {
                        vacancies.add(vacanciesObj)
                    }
                    vacanciesList.layoutManager = LinearLayoutManager(requireContext())
                    vacanciesList.adapter = VacanciesAdapter(vacancies, requireContext())
                })
            }
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = VacanciesList()
    }
}