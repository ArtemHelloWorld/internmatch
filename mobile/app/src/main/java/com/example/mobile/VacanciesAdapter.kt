package com.example.mobile

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile.serializers.Vacancy

class VacanciesAdapter(var vacancies: List<Vacancy>, var context: Context) : RecyclerView.Adapter<VacanciesAdapter.MyViewHolder>() {

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.vacancy_title)
        val description: TextView = view.findViewById(R.id.vacancy_description)
        val detail_button: Button = view.findViewById(R.id.vacancy_detail_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vacancy_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return vacancies.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = vacancies[position].title
        holder.description.text = vacancies[position].description
        holder.detail_button.setOnClickListener {
            // Create an Intent to open SecondActivity
            val intent = Intent(context, VacancyDetail::class.java)
            intent.putExtra("vacancyId", vacancies[position].id.toString())
            context.startActivity(intent)
        }

    }
}