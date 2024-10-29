package com.example.mobile

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile.serializers.EchoVacancy
import com.example.mobile.serializers.Vacancy

class EchoVacanciesAdapter(var echoVacancies: List<EchoVacancy>, var context: Context) : RecyclerView.Adapter<EchoVacanciesAdapter.MyViewHolder>() {

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val vacancyTitle: TextView = view.findViewById(R.id.vacancyTitle)
        val employerText: TextView = view.findViewById(R.id.employer)
        val internText: TextView = view.findViewById(R.id.intern)
        val detail_button: Button = view.findViewById(R.id.detail_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.echo_vacancy_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return echoVacancies.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.vacancyTitle.text = echoVacancies[position].vacancy_meta.title
        holder.employerText.text = "Компания ${echoVacancies[position].vacancy_meta.employer}"
        holder.internText.text = "Стажёр ${echoVacancies[position].intern_username}"

        holder.detail_button.setOnClickListener {
            val intent = Intent(context, EchoVacancyDetail::class.java)
            intent.putExtra("echoVacancyId", echoVacancies[position].id)
            intent.putExtra("internId", echoVacancies[position].intern)
            context.startActivity(intent)
        }

    }
}