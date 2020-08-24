package com.kotlin.newtesttask.ui.user.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.kotlin.newtesttask.R
import com.kotlin.newtesttask.extensions.showToast
import com.kotlin.newtesttask.networking.model.UserModel

class UserDetailsFragment : Fragment() {

    companion object {
        const val ARG_USER = "ARG_USER"
    }

    private val viewModel: UserDetailsViewModel by viewModels()
    private lateinit var mUser: UserModel

    enum class Action {
        DELETE,
        EDIT
    }

    @BindView(R.id.user_first_name_edt)
    lateinit var userFirstNameEdt: EditText

    @BindView(R.id.user_last_name_edt)
    lateinit var userLastNameEdt: EditText

    @BindView(R.id.user_email_edt)
    lateinit var userEmailEdt: EditText

    @OnClick(R.id.edit_btn)
    fun editBtnClick() {
        displayConfirmationDialog(Action.EDIT)
    }

    @OnClick(R.id.delete_btn)
    fun deleteBtnClick() {
        displayConfirmationDialog(Action.DELETE)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().apply {
            mUser = getSerializable(ARG_USER) as UserModel
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_user_details, container, false)
        ButterKnife.bind(this, rootView)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = resources.getString(R.string.title_user_detail)
        }

        userFirstNameEdt.setText(mUser.first_name)
        userLastNameEdt.setText(mUser.last_name)
        userEmailEdt.setText(mUser.email)
    }


    private fun displayConfirmationDialog(action: Action) {
        val actionMessage = when (action) {
            Action.DELETE -> resources.getString(R.string.message_delete_action)
            Action.EDIT -> resources.getString(R.string.message_edit_action)
        }
        val mBuilder = AlertDialog.Builder(requireContext())
        mBuilder.setMessage(actionMessage)
            .setPositiveButton(R.string.title_yes) { _, _ ->
                when (action) {
                    Action.EDIT -> editUser()
                    Action.DELETE -> deleteUser()
                }
            }
            .setNegativeButton(R.string.title_no) { dialogInterface, _ ->
                dialogInterface.dismiss()
            }

        val mDialog: AlertDialog = mBuilder.create()
        mDialog.show()
    }


    private fun editUser() {
        if (userFirstNameEdt.text.toString().isBlank()) {
            userFirstNameEdt.error = resources.getString(R.string.empty_field)
            return
        }

        if (userLastNameEdt.text.toString().isBlank()) {
            userLastNameEdt.error = resources.getString(R.string.empty_field)
            return
        }
        if (userEmailEdt.text.toString().isBlank()) {
            userEmailEdt.error = resources.getString(R.string.empty_field)
            return
        }

        mUser.first_name = userFirstNameEdt.text.toString()
        mUser.last_name = userLastNameEdt.text.toString()
        mUser.email = userEmailEdt.text.toString()
        viewModel.updateUser(mUser)
        requireContext().showToast(resources.getString(R.string.title_user_edited_successfully))
        findNavController().popBackStack()

    }


    private fun deleteUser() {
        viewModel.deleteUser(mUser)
        requireContext().showToast(resources.getString(R.string.title_user_deleted_successfully))
        findNavController().popBackStack()
    }
}