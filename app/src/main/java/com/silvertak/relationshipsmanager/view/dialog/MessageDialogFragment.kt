package com.silvertak.relationshipsmanager.view.dialog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.silvertak.relationshipsmanager.R
import com.silvertak.relationshipsmanager.define.StringDefine
import com.silvertak.relationshipsmanager.library.CallLogLib
import com.silvertak.relationshipsmanager.library.ContactsLib
import com.silvertak.relationshipsmanager.library.DataCombineLib
import com.silvertak.relationshipsmanager.view.MainActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.dialog_fragment_message.*
import java.util.concurrent.TimeUnit

class MessageDialogFragment : BaseDialogFragment() {

    companion object {
        const val TAG: String = "MessageDialogFragment"
        @JvmStatic
        fun newInstance() : MessageDialogFragment{
            return MessageDialogFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setStyle(STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        //return inflater.inflate(R.layout.dialog_fragment_message, container, false)
        return DataBindingUtil.inflate<ViewDataBinding>(inflater, R.layout.dialog_fragment_message, container, true).root
    }

    override fun onResume() {
        super.onResume()
        isCancelable = false
        messageTv.text = getString(R.string.calculate_relationship_score)
        addDisposable(Observable.timer(100L, TimeUnit.MILLISECONDS).subscribe{
            addDisposable(Observable//.timer(300L, TimeUnit.MILLISECONDS)
                    .just(DataCombineLib.combineContactWithCallLog(ContactsLib(activity).contacts, CallLogLib(activity).callLog))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        dismiss()
                        startNextActivity(Bundle().apply { putParcelableArrayList(StringDefine.KEY_PERSON_RELATIONSHIP_INFO_ARRAY, it) })
                    })
        })
    }

    private fun startNextActivity(bundle: Bundle) {
        Intent(activity, MainActivity::class.java)
                .apply {
                    putExtra("data", bundle)
                    startActivity(this)
                    activity!!.finish()
                }
    }

}