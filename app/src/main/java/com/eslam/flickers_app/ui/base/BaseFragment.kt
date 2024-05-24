package com.eslam.flickers_app.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.eslam.flickers_app.R
import com.eslam.flickers_app.databinding.LayoutMainToolbarBinding
import com.eslam.flickers_app.util.AlertHelper.createDialog
import com.eslam.flickers_app.util.Inflate
import com.eslam.flickers_app.util.ProgressUtil
import com.eslam.flickers_app.util.ToastType
import com.eslam.flickers_app.util.showToast
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseFragment<VB : ViewBinding>(private val inflate: Inflate<VB>) :
    Fragment(),
    NetworkExtensionsActions {

    private val viewModel by viewModels<BaseViewModel>()

    @Inject
    lateinit var progressUtil: ProgressUtil

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (_binding == null) {
            _binding = inflate.invoke(inflater, container, false)
            _binding!!.root.layoutDirection = View.LAYOUT_DIRECTION_LOCALE
        }
        afterCreateView()
        handleClicks()
        return binding.root
    }

    open fun afterCreateView() {
    }

    open fun handleClicks() {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleObservers()
        handleBackPressed()
    }

    open fun handleObservers() {
    }

    open fun handleBackPressed() {
        requireView().findViewById<AppCompatImageView>(R.id.iv_back)?.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun LayoutMainToolbarBinding.init(
        title: String,
        hideBackBtn: Boolean = false
    ) {
        this.tvTitle.text = title
        this.tvTitle.isSelected = true
        this.ivBack.isInvisible = hideBackBtn
        this.ivBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun onLoad(showLoading: Boolean) {
        progressUtil.statusProgress(showLoading)
    }

    override fun onCommonError(exceptionMsgId: Int) {
        onLoad(false)
        progressUtil.statusProgress(false)
        requireContext().showToast(getString(exceptionMsgId), ToastType.ERROR)
    }

    override fun onShowSuccessToast(msg: String?) {
        requireContext().showToast(msg, ToastType.SUCCESS)
    }

    override fun onFail(msg: String?) {
        requireContext().showToast(msg, ToastType.ERROR)
    }

    override fun authorizationNeedActive(msg: String) {
        requireContext().showToast(msg, ToastType.WARNING)
    }

    override fun needApproval(msg: String?) {
        msg?.let {
            requireContext().showToast(it, ToastType.WARNING)
        }
    }

    override fun authorizationFail() {
        requireActivity().createDialog(
            getString(R.string.text_alert),
            getString(R.string.un_auth),
            getString(R.string.text_ok), {
                onLogout()
            }
        )
    }

    override fun block() {
        requireActivity().createDialog(
            getString(R.string.text_alert),
            getString(R.string.block),
            getString(R.string.text_ok),
            {
                onLogout()
            }
        )
    }

    protected fun onLogout() {
        viewModel.setAuthUserToken("")
        viewModel.setAuthUserData(null)
        viewModel.setIsLogin(false)
//        requireActivity().openActivity<AuthActivity>(true)
    }

    protected fun observe(observer: suspend () -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                observer()
            }
        }
    }
}
