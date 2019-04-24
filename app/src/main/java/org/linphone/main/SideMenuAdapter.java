package org.linphone.main;

/*
SideMenuAdapter.java
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;
import org.linphone.R;

public class SideMenuAdapter extends ArrayAdapter<SideMenuItem> {
    private final List<SideMenuItem> mItems;
    private final int mResource;

    SideMenuAdapter(@NonNull Context context, int resource, @NonNull List<SideMenuItem> objects) {
        super(context, resource, objects);
        mResource = resource;
        mItems = objects;
    }

    @Nullable
    @Override
    public SideMenuItem getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =
                (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(mResource, parent, false);

        TextView textView = rowView.findViewById(R.id.item_name);
        ImageView imageView = rowView.findViewById(R.id.item_icon);

        SideMenuItem item = getItem(position);
        textView.setText(item.name);
        imageView.setImageResource(item.icon);

        return rowView;
    }
}
