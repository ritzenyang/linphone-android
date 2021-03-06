package org.linphone.assistant;

/*
CountryPicker.java
Copyright (C) 2019 Belledonne Communications, Grenoble, France

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
*/

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import org.linphone.R;
import org.linphone.core.DialPlan;

public class CountryPicker {
    private Context mContext;
    private LayoutInflater mInflater;
    private CountryAdapter mAdapter;
    private ListView mList;
    private EditText mSearch;
    private ImageView mClear;
    private CountryPickedListener mListener;

    public CountryPicker(Context context, CountryPickedListener listener) {
        mContext = context;
        mListener = listener;
        mInflater = LayoutInflater.from(mContext);
        mAdapter = new CountryAdapter(mContext, mInflater);
    }

    private View createView() {
        View view = mInflater.inflate(R.layout.assistant_country_list, null, false);

        mList = view.findViewById(R.id.countryList);
        mList.setAdapter(mAdapter);
        mList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(
                            AdapterView<?> parent, View view, int position, long id) {
                        DialPlan dp = null;
                        if (position > 0 && position < mAdapter.getCount()) {
                            dp = mAdapter.getItem(position);
                        }

                        if (mListener != null) {
                            mListener.onCountryClicked(dp);
                        }
                    }
                });

        mSearch = view.findViewById(R.id.search_country);
        mSearch.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(
                            CharSequence s, int start, int count, int after) {}

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {}

                    @Override
                    public void afterTextChanged(Editable s) {
                        mAdapter.getFilter().filter(s);
                    }
                });
        mSearch.setText("");

        mClear = view.findViewById(R.id.clear_field);
        mClear.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mSearch.setText("");
                    }
                });

        return view;
    }

    public View getView() {
        View view = createView();
        return view;
    }

    public interface CountryPickedListener {
        void onCountryClicked(DialPlan dialPlan);
    }
}
