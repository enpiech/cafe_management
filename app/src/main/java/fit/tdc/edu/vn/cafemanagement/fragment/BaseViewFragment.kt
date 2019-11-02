package fit.tdc.edu.vn.cafemanagement.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.FormState
import kotlinx.android.synthetic.main.activity_main.*


abstract class BaseViewFragment(
    @LayoutRes contentLayoutId: Int
): Fragment(contentLayoutId) {
    val fab: FloatingActionButton by lazy {
        requireActivity().fab
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupNavigate()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun setupNavigate() {
        requireActivity().toolbar.setNavigationOnClickListener {
            requestNavigateUp()
        }
        handleBackPress()
    }

    private fun handleBackPress() {
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            requestNavigateUp()
        }
        callback.isEnabled = true
    }

    fun changeViewType(type: FormState.Type) {
        when (type) {
            FormState.Type.MODIFY, FormState.Type.ADD -> {
                (requireActivity() as AppCompatActivity).toolbar.apply {
                    setNavigationIcon(R.drawable.ic_close)
                }
                fab.setImageDrawable(requireActivity().getDrawable(R.drawable.ic_check))
                fab.hide()
            }
            FormState.Type.VIEW -> {
                (requireActivity() as AppCompatActivity).toolbar.apply {
                    setNavigationIcon(R.drawable.ic_back)
                }
                fab.setImageDrawable(requireActivity().getDrawable(R.drawable.ic_mode_edit))
                fab.show()
            }
        }

        updateUI(type)
    }

    abstract fun requestNavigateUp()
    abstract fun updateUI(type: FormState.Type)
}