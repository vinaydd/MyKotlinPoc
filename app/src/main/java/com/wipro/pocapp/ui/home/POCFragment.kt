package com.wipro.pocapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.doctoremr.data.network.responses.test.TestListResponse
import com.doctoremr.ui.base.BaseFragment
import com.doctoremr.ui.base.ErrorWrapper
import com.wipro.pocapp.data.models.Token
import com.wipro.pocapp.databinding.FragmentAllergiesTabBinding
import com.wipro.pocapp.exception.ApiException
import com.wipro.pocapp.exception.NoInternetException
import com.wipro.pocapp.ui.base.RecyclerViewClickListener
import com.wipro.pocapp.ui.home.adapter.ItemListAdapter
import kotlinx.android.synthetic.main.fragment_allergies_tab.*

import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.wipro.pocapp.R


class POCFragment : BaseFragment(), KodeinAware, RecyclerViewClickListener<TestListResponse> {
    override val kodein by kodein()
    private val factory: PocViewModelFactory by instance()
    private lateinit var viewModel: PocViewModel
    private lateinit var binding: FragmentAllergiesTabBinding
    private lateinit var token: Token

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this, factory).get(PocViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, com.wipro.pocapp.R.layout.fragment_allergies_tab, container, false)
        binding.viewModelallergies = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.swip.setOnRefreshListener {
            bindUI()
            binding.swip.isRefreshing = false

        }
        bindUI()
    }
    private fun bindUI() = launch {
        binding.allergiesTabRecyclerview.also {
            it.layoutManager = LinearLayoutManager(requireActivity())
            it.setHasFixedSize(true)
        }
        try {
            val allergies = viewModel.getAllergiesTypeList().rows
            setTitle(viewModel.getAllergiesTypeList().title)
                  progressBar.visibility = View.GONE
            allergies_tab_recyclerview.adapter = ItemListAdapter(allergies, this@POCFragment)
        } catch (e: ApiException) {
           ErrorWrapper(e.errorCode, e._message)
            progressBar.visibility = View.GONE
        } catch (e: NoInternetException) {
            ErrorWrapper(e.errorCode, e._message)
            findNavController().navigate(R.id.fragmentNoInternet)
            progressBar.visibility = View.GONE
        }


    }

    private fun setTitle(title: String) {
        val activity = activity as AppCompatActivity?
        val actionBar = activity!!.supportActionBar
        actionBar!!.setTitle(title)



    }

    override fun onRecyclerViewItemClick(view: View, item: TestListResponse) {
    }

}
