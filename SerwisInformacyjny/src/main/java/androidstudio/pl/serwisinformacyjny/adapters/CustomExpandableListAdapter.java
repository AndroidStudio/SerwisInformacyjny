package androidstudio.pl.serwisinformacyjny.adapters;


import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import androidstudio.pl.serwisinformacyjny.R;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private final List<String> headerListGroup;
    private final HashMap<String, List<String>> childList;
    private final int padding;
    private final LayoutInflater layoutInflater;

    public CustomExpandableListAdapter(Context context, List<String> headerListGroup, HashMap<String, List<String>> childList) {
        this.headerListGroup = headerListGroup;
        this.childList = childList;
        this.padding = (int) (context.getResources().getDimension(R.dimen.explistmainlayoutpadding));
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.childList.get(this.headerListGroup.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    private class ViewHolder {
        private RelativeLayout relativeLayout;
        private TextView textView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.row_child, null);
            if (convertView != null) {

                holder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.relativeLayout);
                holder.relativeLayout.setBackgroundResource(R.drawable.shapechild);
                holder.relativeLayout.setPadding(padding, padding, padding, padding);

                holder.textView = (TextView) convertView.findViewById(R.id.textView);
                holder.textView.setTextColor(Color.rgb(240, 138, 1));
                holder.textView.setPadding(padding, 0, 0, 0);
                holder.textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, padding * 1.1f);

                convertView.setTag(holder);
            }
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.childList.get(this.headerListGroup.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.headerListGroup.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.headerListGroup.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final String headerTitle = (String) getGroup(groupPosition);
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.row_group, null);
            if (convertView != null) {

                holder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.relativeLayout);
                holder.relativeLayout.setBackgroundResource(R.drawable.shapegroup);
                holder.relativeLayout.setPadding(padding, padding, padding, padding);

                holder.textView = (TextView) convertView.findViewById(R.id.textView);
                holder.textView.setTextColor(Color.WHITE);
                holder.textView.setPadding(padding * 3, 0, 0, 0);
                holder.textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, padding * 1.4f);

                convertView.setTag(holder);
            }
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(headerTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}