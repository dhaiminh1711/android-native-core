package com.working.core.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.working.core.repository.BaseRepository

abstract class BaseFragment<
        VIEW_BINDING : ViewBinding,
        VIEW_MODEL : ViewModel,
        BASE_REPOSITORY : BaseRepository> : Fragment() {

    /*
    Để xác định fragment có dùng chung ViewModel với activity cha của nó hay không
        - true: khi fragment init sẽ sử dụng ViewModel của activity cha
        - false: khi fragment init sẽ sử dụng ViewModel đã được chỉ định lúc khai báo BaseFragment
    */
    open var useSharedViewModel: Boolean = false

    protected lateinit var viewModel: VIEW_MODEL
    protected abstract fun getViewModelClass(): Class<VIEW_MODEL>

    protected lateinit var binding: VIEW_BINDING
    protected abstract fun getViewBinding(): VIEW_BINDING

    protected lateinit var repository: BASE_REPOSITORY
    protected abstract fun getBaseRepository(): BASE_REPOSITORY

    /*
    Lifecycle của fragment từ lúc create cho tới lúc bị destroy hoàn toàn
    */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViews()
        observeData()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

    // Setup recycler view hoặc là đổ dữ liệu vào giao diện UI
    open fun setUpViews() {}

    open fun observeViews() {}

    open fun observeData() {}

    private fun init() {
        binding = getViewBinding()

        viewModel = if (useSharedViewModel) {
            ViewModelProvider(requireActivity())[getViewModelClass()]
        } else {
            ViewModelProvider(this)[getViewModelClass()]
        }

        repository = getBaseRepository()
    }
}
