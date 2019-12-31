package com.pratthamarora.retrofit_coroutine_mvvm.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.pratthamarora.retrofit_coroutine_mvvm.R
import com.pratthamarora.retrofit_coroutine_mvvm.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ListViewModel
    private val userAdapter = UserAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        usersList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
        }
        observeViewModel()


    }

    private fun observeViewModel() {
        viewModel.users.observe(this, Observer { users ->
            users?.let {
                usersList.visibility = View.VISIBLE
                userAdapter.updateUsers(it)
            }

        })

        viewModel.usersLoadError.observe(this, Observer { isError ->
            list_error.visibility = if (isError == null) View.GONE else View.VISIBLE

        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loading_view.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    list_error.visibility = View.GONE
                    usersList.visibility = View.GONE
                }
            }
        })

    }
}
