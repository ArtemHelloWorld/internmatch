package com.example.mobile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment


class ProfilePage : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

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
        val list = view.findViewById<ListView>(R.id.listView)
        val listTitle = view.findViewById<TextView>(R.id.listTitle)

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
            api.getProfileIntern() { profileInternObj ->
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
        }



        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfilePage()
    }
}