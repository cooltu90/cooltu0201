package core.fragmentbase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class BaseStepFragmentBase extends com.codingtu.cooltu.lib4a.ui.fragment.CoreFragment implements View.OnClickListener, View.OnLongClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI{
    protected android.widget.TextView tv2;
    protected com.codingtu.cooltu.ui.adapter.CatAdapter catAdapter;
    protected androidx.recyclerview.widget.RecyclerView rv;

    public String baseClassName = "BaseStepFragmentBase";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onCreateComplete() {
        super.onCreateComplete();
        // catAdapter
        catAdapter = new com.codingtu.cooltu.ui.adapter.CatAdapter();
        catAdapter.setVH(core.vh.CatVH.class);
        catAdapter.setClick(this);
        rv.setAdapter(catAdapter);
        new com.codingtu.cooltu.lib4a.ui.recyclerview.DefaultConfig().config(getAct(), rv, () -> rvObj());

        tv2.setOnClickListener(this);

        tv2.setOnLongClickListener(this);

    }
    protected Object rvObj() {
        return null;
    }


    @Override
    public void onClick(View v) {

        try {
            switch (v.getId()) {
                case com.codingtu.cooltu.R.id.tv2:
                    tv2Click(
                    );
                    break;

            }
        } catch (Exception e) {
            com.codingtu.cooltu.lib4a.log.Logs.e(e);
            if (!(e instanceof com.codingtu.cooltu.lib4a.exception.NotToastException)) {
                toast(e.getMessage());
            }
        }
    }
    protected void tv2Click() throws Exception {}


    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case com.codingtu.cooltu.R.id.tv2:
                return tv2LongClick(
                );

        }

        return false;


    }
    protected boolean tv2LongClick() {return false;}




    @Override
    public void accept(String code, Result<ResponseBody> result, com.codingtu.cooltu.lib4a.net.bean.CoreSendParams params, List objs) {



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == android.app.Activity.RESULT_OK) {

        }
    }








}
