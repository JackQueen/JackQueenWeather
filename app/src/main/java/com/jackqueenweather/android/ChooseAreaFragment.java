package com.jackqueenweather.android;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jackqueenweather.android.model_db.City;
import com.jackqueenweather.android.model_db.County;
import com.jackqueenweather.android.model_db.Province;
import com.jackqueenweather.android.util.GsonUtil;
import com.jackqueenweather.android.util.HttpUtil;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseAreaFragment extends Fragment {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.lv_daychoose)
    ListView lvDaychoose;
    private Unbinder bind;
    private static final int LEVEL_PROVINCE = 0;
    private static final int LEVEL_CITY = 1;
    private static final int LEVEL_COUNTY = 2;
    private ProgressDialog progressDialog;
    //省 市 县列表
    private List<Province> provinceList;
    private List<City> cityList;
    private List<County> countyList;
    //选中的省份 城市
    private Province selectedProvince;
    private City selectedCity;
    //当前选中的级别
    private int currentLevel;
    private List<String> dataList = new ArrayList<String>();
    private ArrayAdapter<String> mAdapter;

    public ChooseAreaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.area_choose, container, false);
        bind = ButterKnife.bind(this,view);
        initListView();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        queryProvinces();
    }

    private void initListView() {
        mAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, dataList);
        lvDaychoose.setAdapter(mAdapter);

    }

    @OnItemClick(R.id.lv_daychoose)
    public void onItemClick(int position) {
        if (currentLevel == LEVEL_PROVINCE) {
            selectedProvince = provinceList.get(position);
            queryCities();
        }
    }


    @OnClick({R.id.btn_back,})
    public void onClick(View view) {
        if (currentLevel == LEVEL_COUNTY) {
            queryCities();
        } else if (currentLevel == LEVEL_CITY) {
            queryProvinces();
        }
    }

    /**
     * 遍历查询中国省份信息,如果数据库查询不到则到服务器查询
     */
    private void queryProvinces() {
        tvTitle.setText("中国");
        btnBack.setVisibility(View.GONE);
        provinceList = DataSupport.findAll(Province.class);
        if (provinceList.size() > 0) {
            dataList.clear();
            for (Province province : provinceList) {
                dataList.add(province.getProvinceName());
            }
            mAdapter.notifyDataSetChanged();
            lvDaychoose.setSelection(0);
            currentLevel = LEVEL_PROVINCE;
        } else {
            queryFromServer(Constant.HOST, "province");
        }
    }

    /**
     * 遍历数据库某省份城市列表
     */
    private void queryCities() {
        tvTitle.setText(selectedProvince.getProvinceName());
        btnBack.setVisibility(View.VISIBLE);
        cityList = DataSupport.where("provinceid=?", String.valueOf(selectedProvince.getId())).find(City.class);
        if (cityList.size() > 0) {
            dataList.clear();
            for (City city : cityList) {
                dataList.add(city.getCityName());
            }
            mAdapter.notifyDataSetChanged();
            lvDaychoose.setSelection(0);
            currentLevel = LEVEL_CITY;
        } else {//如果该省份没有子城市直接查询省份天气
            int provinceCode = selectedProvince.getProvinceCode();
            String address = Constant.HOST + provinceCode;
            queryFromServer(address, "city");
        }
    }

    /**
     * 查询某城市下的县级市,如果数据库无,就到服务器查询
     */
    private void queryCounties() {
        tvTitle.setText(selectedCity.getCityName());
        btnBack.setVisibility(View.VISIBLE);
        countyList = DataSupport.where("cityid=?", String.valueOf(selectedProvince.getId())).find(County.class);
        if (countyList.size() > 0) {
            dataList.clear();
            for (County county : countyList) {
                dataList.add(county.getCountyName());
            }
            mAdapter.notifyDataSetChanged();
            lvDaychoose.setSelection(0);
            currentLevel = LEVEL_COUNTY;
        } else {
            int provinceCode = selectedProvince.getProvinceCode();
            int cityCode = selectedCity.getCityCode();
            String address = Constant.HOST + provinceCode + "/" + cityCode;
            queryFromServer(address, "county");
        }
    }

    /**
     * 根据传入的url和地址类型查询天气数据
     */
    private void queryFromServer(String address, final String type) {
        showProgressDialog();
        HttpUtil.getRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //在主线程弹出Toast
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        closeProgressDialog();
                        Toast.makeText(getActivity(), "加载失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                boolean result = false;
                switch (type) {
                    case "province":
                        result = GsonUtil.initProvinceJson(responseText);
                        break;
                    case "city":
                        result = GsonUtil.initCityJson(responseText, selectedProvince.getId());
                        break;
                    case "county":
                        result = GsonUtil.initCountyJson(responseText, selectedCity.getId());
                        break;
                }
                if (result) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            closeProgressDialog();
                            switch (type) {
                                case "province":
                                    queryProvinces();
                                    break;
                                case "city":
                                    queryCities();
                                    break;
                                case "county":
                                    queryCounties();
                                    break;
                            }
                        }
                    });
                }
            }
        });
    }

    /**
     * 显示进度对话框
     */
    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("正在加载...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    /**
     * 关闭进度对话框
     */
    private void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
