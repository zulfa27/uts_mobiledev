package com.zulfa.uts_18090053.activity.report.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment

import com.zulfa.uts_18090053.R
import com.zulfa.uts_18090053.activity.report.detail.data.ResultItemKeranjang
import com.zulfa.uts_18090053.activity.report.detail.presenter.DetailReportPresenter
import com.zulfa.uts_18090053.activity.report.detail.adapter.ReportDetailAdapter
import com.zulfa.uts_18090053.model.Keranjang
import kotlinx.android.synthetic.main.fragment_detail_report.view.*
import com.zulfa.uts_18090053.utils.Uang

class DetailReportFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_report, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val data = arguments?.getSerializable("penjualan")

        if (data != null) {
            val penjualan = data as Keranjang

            DetailReportPresenter()
                .getPenjualan(Integer.parseInt(penjualan.idUser),Integer.parseInt(penjualan.idKeranjang), object: DetailReportPresenter.OnResult{
                    override fun onFailed(msg: String?) {

                    }
                    override fun onResult(resultItemKeranjang: ResultItemKeranjang) {
                        view?.list?.adapter = ReportDetailAdapter(resultItemKeranjang)
                        view?.tvStatus?.text = resultItemKeranjang.keranjang?.status
                        view?.tvQty?.text = resultItemKeranjang.keranjang?.qty.toString()
                        view?.tvGrandTotal?.text = resultItemKeranjang.keranjang?.totalHarga?.let {
                            Uang.indonesia(it)
                        }
                    }

                })
        }
    }
}
