package com.example.mobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile.serializers.EchoVacancy


class Monitoring : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_monitoring, container, false)
        val echoVacanciesList: RecyclerView = view.findViewById(R.id.echoVacanciesList)
        val echoVacancies = ArrayList<EchoVacancy>()

        val api = Api(view.context)
        api.getEchoVacanciesList() { echoVacanciesObjs ->
            activity?.runOnUiThread(Runnable {

                for (echoVacanciesObj in echoVacanciesObjs) {
                    echoVacancies.add(echoVacanciesObj)
                }
                echoVacanciesList.layoutManager = LinearLayoutManager(requireContext())
                echoVacanciesList.adapter = EchoVacanciesAdapter(echoVacancies, requireContext())
            })
        }

        return view

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = Monitoring()
    }
}