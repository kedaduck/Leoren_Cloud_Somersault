package com.leoren.liehu.MyView.SerachView;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.leoren.liehu.R;

/**
 * @Author Leoren
 * @Date 2018/5/24 11:24
 */
public class SearchView extends LinearLayout implements View.OnClickListener {

    //输入框
    private EditText input;

    //删除键
    private ImageView imgdelete;

    //返回按钮
    private Button btnBack;

    //上下文对象
    private Context mContext;

    //弹出列表
    private ListView lvTips;

    //提示adapter
    private ArrayAdapter<String> hintAdapter;

    //自动补全adapter  只显示名字
    private ArrayAdapter<String> autoCompleteAdapter;

    //搜索接口回调
    private SearchViewListener listener;

    public void setSearchViewListener(SearchViewListener listener){
        this.listener = listener;
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.acticvity_search,this);
        initViews();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_et_input:
                lvTips.setVisibility(VISIBLE);
                break;
            case R.id.search_delete:
                input.setText("");
                imgdelete.setVisibility(GONE);
                break;
            case R.id.search_back:
                ((Activity)mContext).finish();
                break;
        }
    }

    private void initViews(){
        input = findViewById(R.id.search_et_input);
        imgdelete = findViewById(R.id.search_delete);
        btnBack = findViewById(R.id.search_back);
        lvTips = findViewById(R.id.search_tips);

        lvTips.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = lvTips.getAdapter().getItem(position).toString();
                input.setText(text);
                input.setSelection(text.length());
                lvTips.setVisibility(View.GONE);
                notifyStartSearching(text);
            }
        });

        imgdelete.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        input.addTextChangedListener(new EditChangeListener());
        input.setOnClickListener(this);
        input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    lvTips.setVisibility(GONE);
                    notifyStartSearching(input.getText().toString());
                }
                return true;
            }
        });

    }

    private void notifyStartSearching(String text){
        if(listener != null){
            listener.onSearch(input.getText().toString());
        }
        InputMethodManager manager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }



    private class EditChangeListener implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(!"".equals(s.toString())){
                imgdelete.setVisibility(VISIBLE);
                lvTips.setVisibility(VISIBLE);
                if(autoCompleteAdapter != null && lvTips.getAdapter() != autoCompleteAdapter){
                    lvTips.setAdapter(autoCompleteAdapter);
                }
                if(listener != null){
                    listener.onRefreshAutoComplete(s + "");
                }
            }else{
                imgdelete.setVisibility(GONE);
                if(hintAdapter != null){
                    lvTips.setAdapter(hintAdapter);
                }
                lvTips.setVisibility(GONE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    public interface SearchViewListener{

        //更新自动补全内容
        void onRefreshAutoComplete(String text);

        //开始搜索
        void onSearch(String text);
    }
}
