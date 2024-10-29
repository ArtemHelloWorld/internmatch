package com.example.mobile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment


class ProfilePage : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val sharedPreferences = SharedPreferences()

        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val username = view.findViewById<TextView>(R.id.usernameProfile)
        val email = view.findViewById<TextView>(R.id.emailProfile)
        val inn = view.findViewById<TextView>(R.id.innProfile)

        val list = view.findViewById<ListView>(R.id.listView)
        val listTitle = view.findViewById<TextView>(R.id.listTitle)

        val addButton = view.findViewById<TextView>(R.id.addButton)
        val logoutBtn = view.findViewById<Button>(R.id.logoutBtn)


        val api = Api(view.context)
        api.getProfile() { profileObj ->
            if (profileObj != null){
                activity?.runOnUiThread(Runnable {
                    username.text = profileObj.username
                    email.text = profileObj.email

                    sharedPreferences.save("user_id", profileObj.id.toString(), view.context)
                    sharedPreferences.save("user_type", profileObj.user_type.toString(), view.context)

                })
            }
        }

        val user_type = sharedPreferences.get("user_type", view.context)

        if (user_type == "intern"){
            api.getMyProfileIntern() { profileInternObj ->
                if (profileInternObj != null){
                    activity?.runOnUiThread(Runnable {
                        val itemDataList = ArrayList<Map<String, Any>>()
                        for (skill in profileInternObj.skills){
                            val listItemMap: MutableMap<String, Any> = HashMap()
                            listItemMap["name"] = skill.name
                            listItemMap["rate"] = "${skill.rate}/10"
                            itemDataList.add(listItemMap)
                        }
                        val simpleAdapter = SimpleAdapter(
                            view.context,
                            itemDataList,
                            android.R.layout.simple_list_item_2,
                            arrayOf("name", "rate"),
                            intArrayOf(android.R.id.text1, android.R.id.text2)
                        )
                        list.adapter = simpleAdapter
                        listTitle.text = "Навыки"

                    })
                }
            }

            addButton.setOnClickListener {
                val intent = Intent(view.context, SkillCreation::class.java)
                this.startActivity(intent)
            }
        }

        else if (user_type == "employer"){
            api.getMyProfileEmployer() { profileEmployerObj ->
                if (profileEmployerObj != null){
                    activity?.runOnUiThread(Runnable {
                        val itemDataList = ArrayList<Map<String, Any>>()
                        for (vacancy in profileEmployerObj.vacancies){
                            val listItemMap: MutableMap<String, Any> = HashMap()
                            listItemMap["title"] = vacancy.title
                            listItemMap["description"] = vacancy.skills
                            itemDataList.add(listItemMap)
                        }
                        val simpleAdapter = SimpleAdapter(
                            view.context,
                            itemDataList,
                            android.R.layout.simple_list_item_2,
                            arrayOf("title", "description"),
                            intArrayOf(android.R.id.text1, android.R.id.text2)
                        )
                        list.adapter = simpleAdapter
                        listTitle.text = "Мои стажировки"
                        inn.text = "(ИНН ${profileEmployerObj.inn})"

                    })
                }
            }

            addButton.setOnClickListener {
                val intent = Intent(view.context, VacancyCreation::class.java)
                this.startActivity(intent)
            }
        }


        logoutBtn.setOnClickListener {
            sharedPreferences.save("access_token", "", view.context)
            val intent = Intent(view.context, LoginActivity::class.java)
            this.startActivity(intent)
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfilePage()
    }
}