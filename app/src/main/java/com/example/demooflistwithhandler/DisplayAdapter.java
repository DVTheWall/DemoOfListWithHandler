package com.example.demooflistwithhandler;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DisplayAdapter extends BaseAdapter {
	public Context context;
	List<Student> mainDataList;

	public DisplayAdapter(Context context, List<Student> mainDataList) {
	
		this.context = context;
		this.mainDataList = mainDataList;
	}

	@Override
	public int getCount() {

		return mainDataList.size();
	}

	@Override
	public Object getItem(int postion) {

		return mainDataList.get(postion);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int postion, View convertView, ViewGroup parent) {
        Holder holder;
        LayoutInflater layoutinflater;
        if(convertView ==null){
        	layoutinflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        	convertView=layoutinflater.inflate(R.layout.listcell,null);
        	holder=new Holder();
        	holder.txt_id=(TextView)convertView.findViewById(R.id.text_id);
        	holder.txt_name=(TextView)convertView.findViewById(R.id.text_Name);
        	holder.txt_email=(TextView)convertView.findViewById(R.id.text_Email);
        	convertView.setTag(holder);
        }
        else {
			holder=(Holder)convertView.getTag();
		}
        holder.txt_id.setText(mainDataList.get(postion).getId());
        holder.txt_name.setText(mainDataList.get(postion).getSname());
        holder.txt_email.setText(mainDataList.get(postion).getEmail());
		return convertView;
	}
	
	public class Holder {
		TextView txt_id;
		TextView txt_name;
		TextView txt_email;
	}

}
