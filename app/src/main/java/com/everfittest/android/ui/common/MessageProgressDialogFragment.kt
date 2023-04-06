package com.everfittest.android.ui.common

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.everfittest.android.R
import com.everfittest.android.databinding.DialogFragmentMessageProgressBinding

class MessageProgressDialogFragment :
    DialogFragment(R.layout.dialog_fragment_message_progress) {

    private val safeArgs: MessageProgressDialogFragmentArgs by navArgs()
    private val message by lazy { safeArgs.message }
    private val binding by viewBindings(DialogFragmentMessageProgressBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.MessageProgressDialog)
        isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            window?.apply {
                setLayout(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressMessage.text = message
    }

    fun update(message: String) {
        binding.progressMessage.text = message
    }

    companion object {
        fun getInstance(message: String): MessageProgressDialogFragment {
            return MessageProgressDialogFragment()
                .also { fragment ->
                    fragment.arguments =
                        MessageProgressDialogFragmentArgs(message).toBundle()
                }
        }
    }
}
