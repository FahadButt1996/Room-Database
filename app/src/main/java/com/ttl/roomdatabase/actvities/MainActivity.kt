package com.ttl.roomdatabase.actvities

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.facebook.stetho.Stetho
import com.ttl.roomdatabase.DAO.DataBase
import com.ttl.roomdatabase.INSERT_USER
import com.ttl.roomdatabase.R
import com.ttl.roomdatabase.RecyclerItemClick
import com.ttl.roomdatabase.UPDATE_USER
import com.ttl.roomdatabase.adapter.UserAdapter
import com.ttl.roomdatabase.model.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RecyclerItemClick {

    lateinit var userAdapter: UserAdapter
    var userId: Int = 0

    override fun editItemClicked(user: User) {
        name.setText(user.name)
        contact.setText(user.contact_no)
        CNIC.setText(user.cnic)
        addres.setText(user.address)
        userId = user.id
        btn_user.setText(UPDATE_USER)
    }

    override fun deleteItemClicked(itemId: Int) {
        // add the delete code here
        DataBase.getInstance(this)?.getUserDao()?.deleteUser(itemId)
        showToast("Item Deleted")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Stetho.initializeWithDefaults(this)
        setContentView(R.layout.activity_main)
        initClickListener()

        DataBase.getInstance(this@MainActivity)?.getUserDao()?.allUsers()!!.observe(this@MainActivity, Observer { result ->
            user_recycler.layoutManager = LinearLayoutManager(this@MainActivity)
            userAdapter = UserAdapter(result as MutableList<User>, this@MainActivity, this)
            user_recycler.adapter = userAdapter
            userAdapter.notifyDataSetChanged()

        })
    }

    private fun initClickListener() {

        btn_user.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                if (btn_user.text.equals(INSERT_USER)) {
                    if (!name.text?.isEmpty()!! || !contact.text?.isEmpty()!! || !addres.text?.isEmpty()!! || !CNIC.text?.isEmpty()!!) {
                        insertUser()
                        clearFeilds()
                        userAdapter.notifyDataSetChanged()
                    } else {
                        showToast("Do not leave any field")
                    }
                } else if (btn_user.text.equals(UPDATE_USER)) {
                    if (!name.text?.isEmpty()!! || !contact.text?.isEmpty()!! || !addres.text?.isEmpty()!! || !CNIC.text?.isEmpty()!!) {
                        DataBase.getInstance(this@MainActivity)?.getUserDao()?.updateUser(User(userId,
                                name.text.toString(), contact.text.toString(), CNIC.text.toString(), addres.text.toString()))
                        userAdapter.notifyDataSetChanged()
                        btn_user.setText(INSERT_USER)
                        clearFeilds()
                    } else {
                        showToast("Do not leave any field")
                    }
                }
            }
        })
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun insertUser() {
        DataBase.getInstance(this@MainActivity)?.getUserDao()?.insertUser(User
        (0, name.text.toString(), contact.text.toString(), CNIC.text.toString(), addres.text.toString()))
    }

    fun clearFeilds() {
        name.setText("")
        contact.setText("")
        CNIC.setText("")
        addres.setText("")
    }
}
