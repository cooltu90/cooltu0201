package com.codingtu.cooltu.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.bean.User;
import com.codingtu.cooltu.lib4a.ui.fragment.CoreFragment;
import com.codingtu.cooltu.processor.annotation.net.NetBack;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu.processor.annotation.ui.FragmentBase;

import core.fragmentbase.StepOneFragmentBase;
import core.fragmentres.StepOneFragmentRes;
import core.net.Net;

@To(StepOneFragmentRes.class)
@FragmentBase(layout = R.layout.fragment_step_one, base = BaseStepFragment.class)
public class StepOneFragment extends StepOneFragmentBase {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @ClickView(R.id.tv1)
    public void tv1Click(User user) {
    }

    @Override
    protected void dogAdapterLoadMore(int page) {

    }

    @NetBack
    public void getObjBack(User user) {

    }

}
