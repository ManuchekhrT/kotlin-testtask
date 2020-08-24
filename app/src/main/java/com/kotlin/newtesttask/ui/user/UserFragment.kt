package com.kotlin.newtesttask.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.kotlin.newtesttask.R
import com.kotlin.newtesttask.extensions.showLoading
import com.kotlin.newtesttask.networking.model.UserModel
import com.kotlin.newtesttask.ui.user.details.UserDetailsFragment

class UserFragment : Fragment(), UserAdapter.OnUserClickListener {

    private val viewModel: UserViewModel by viewModels()
    private lateinit var mUserAdapter: UserAdapter

    @BindView(R.id.users_rv)
    lateinit var usersRv: RecyclerView
    @BindView(R.id.loading_pb)
    lateinit var loadingPb: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mUserAdapter = UserAdapter(this)
        val rootView = inflater.inflate(R.layout.fragment_user, container, false)
        ButterKnife.bind(this, rootView)

        usersRv.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = mUserAdapter
        }

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = resources.getString(R.string.title_users)
        }

        fetchUsers()
    }

    private fun fetchUsers() {
        loadingPb.showLoading(true)
        viewModel.getUsers().observe(viewLifecycleOwner, Observer {
            loadingPb.showLoading(false)
            if (it.isNotEmpty()) {
                showUsers(it)
            }
        })
    }

    private fun showUsers(usersList: List<UserModel>) {
        mUserAdapter.changeDataSet(usersList)
    }


    override fun onUserClick(aUser: UserModel) {
        val bundle = bundleOf(UserDetailsFragment.ARG_USER to aUser)
        Navigation.findNavController(requireView())
            .navigate(R.id.action_userFragment_to_userDetailsFragment, bundle)
    }

}