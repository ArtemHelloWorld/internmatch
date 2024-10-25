package com.example.mobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        val vacancies = ArrayList<Vacancy>()

        val api = Api()
        api.getVacanciesList() { vacanciesObjs ->
            for (vacanciesObj in vacanciesObjs){
                vacancies.add(vacanciesObj)
            }
        }

        vacanciesList.layoutManager = LinearLayoutManager(requireContext())
        vacanciesList.adapter = VacanciesAdapter(vacancies, requireContext())
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = VacanciesList()
    }
}