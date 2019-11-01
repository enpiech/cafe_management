package fit.tdc.edu.vn.cafemanagement.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavArgs
import androidx.navigation.NavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel
import fit.tdc.edu.vn.cafemanagement.data.model.FormState
import kotlinx.android.synthetic.main.activity_main.*


abstract class BaseViewFragmentTest<T : FirestoreModel>(
    @LayoutRes contentLayoutId: Int
) : Fragment(contentLayoutId) {
    private val fab: FloatingActionButton by lazy {
        requireActivity().fab
    }
    private val toolbar: Toolbar by lazy {
        requireActivity().toolbar
    }

    protected abstract val viewModel: BaseDetailViewModel<T>
    protected abstract val navController: NavController
    protected abstract val itemId: String?
    protected abstract val args: NavArgs

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupNavigate()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun setupNavigate() {
        toolbar.setNavigationOnClickListener {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.viewType.observe(viewLifecycleOwner, Observer {
            if (it == null) {
                if (itemId != null) {
                    viewModel.setViewType(FormState.Type.VIEW)
                    viewModel.getCurrentItem(itemId!!)
                } else {
                    viewModel.setViewType(FormState.Type.ADD)
                }
            } else {
                changeViewType(it)
            }
        })

        viewModel.formState.observe(viewLifecycleOwner, Observer {
            val state = it ?: return@Observer
            if (viewModel.viewType.value == FormState.Type.MODIFY) {
                if (state.isDataValid && state.isChanged) {
                    fab.show()
                } else {
                    fab.hide()
                }
            } else if (viewModel.viewType.value == FormState.Type.ADD) {
                if (state.isDataValid) {
                    fab.show()
                } else {
                    fab.hide()
                }
            }
            showError(state)
        })

        fab.setOnClickListener {
            when (viewModel.viewType.value) {
                FormState.Type.ADD -> {
                    viewModel.validate(getCurrentFormData())
                    viewModel.insert(
                        getCurrentFormData()
                    )
                    navController.navigateUp()
                }
                FormState.Type.VIEW -> {
                    viewModel.setViewType(FormState.Type.MODIFY)
                }
                FormState.Type.MODIFY -> {
                    viewModel.update(
                        getCurrentFormData().also { it.id = itemId!! }
                    )
                    navController.navigateUp()
                }
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun changeViewType(type: FormState.Type) {
        when (type) {
            FormState.Type.MODIFY, FormState.Type.ADD -> {
                toolbar.setNavigationIcon(R.drawable.ic_close)
                fab.setImageDrawable(requireActivity().getDrawable(R.drawable.ic_check))
                fab.hide()
            }
            FormState.Type.VIEW -> {
                toolbar.setNavigationIcon(R.drawable.ic_back)
                fab.setImageDrawable(requireActivity().getDrawable(R.drawable.ic_mode_edit))
                fab.show()
            }
        }

        when (type) {
            FormState.Type.VIEW -> {
                updateUIWithCurrentItem()
            }
            FormState.Type.MODIFY -> {
                updateUIWithDraftItem()
            }
        }

        updateUI(type)
    }

    private fun updateUIWithCurrentItem() {
        viewModel.currentItem.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                viewModel.validate(null)
                fillFormWith(it)
                viewModel.draftItem.value = it
                viewModel.saved = it
            } else {
                MaterialAlertDialogBuilder(context)
                    .setTitle(R.string.dialog_title_modifying_removed_item)
                    .setMessage(R.string.warning_message_modifying_removed_item)
                    .setPositiveButton(R.string.btnOK) { _, _ ->
                        viewModel.setViewType(FormState.Type.ADD)
                        viewModel.currentItem.value = getCurrentFormData()
                        fab.show()
                    }
                    .setNegativeButton(R.string.btnCancel) { _, _ ->
                        navController.navigateUp()
                    }
                    .show()
            }
        })
    }

    private fun updateUIWithDraftItem() {
        fillFormWith(viewModel.saved)
        viewModel.currentItem.removeObservers(viewLifecycleOwner)
    }

    private fun requestNavigateUp() {
        if (viewModel.viewType.value == FormState.Type.VIEW) {
            navController.navigateUp()
            return
        }

        if (viewModel.formState.value?.isChanged == true) {
            MaterialAlertDialogBuilder(context)
                .setTitle(R.string.dialog_title_warning)
                .setMessage(R.string.warning_message_unsaved_changed)
                .setPositiveButton(R.string.btnOK) { _, _ ->
                    when (viewModel.viewType.value) {
                        FormState.Type.MODIFY -> viewModel.setViewType(FormState.Type.VIEW)
                        FormState.Type.ADD -> navController.navigateUp()
                    }
                }
                .setNegativeButton(R.string.btnCancel, null)
                .show()
        } else {
            if (viewModel.viewType.value == FormState.Type.ADD) {
                navController.navigateUp()
            } else {
                viewModel.setViewType(FormState.Type.VIEW)
            }
        }
    }

    protected abstract fun showError(state: FormState)
    protected abstract fun updateUI(type: FormState.Type)
    protected abstract fun getCurrentFormData(): T
    protected abstract fun fillFormWith(item: T)
    protected abstract fun setupForm()
}