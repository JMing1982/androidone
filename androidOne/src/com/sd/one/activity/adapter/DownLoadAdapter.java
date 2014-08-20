package com.sd.one.activity.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sd.core.network.download.DownloadManager;
import com.sd.one.R;
import com.sd.one.activity.demo.download.DownLoadActivity;
import com.sd.one.activity.demo.download.DownloadInfo;

/**
 * [A brief description]
 * 
 * @author: devin.hu
 * @version: 1.0
 * @date: Nov 25, 2013
 */
public class DownLoadAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ViewHolder holder;
    private List<DownloadInfo> list;
    private DownLoadActivity activity; 
    
    /**
     * [A brief description]
     * 
     * @param context
     */
    public DownLoadAdapter(Context context, DownLoadActivity activity) {
        mContext = context;
        this.activity = activity;
        mInflater = LayoutInflater.from(mContext);
    }

    class ViewHolder {
        TextView tv_name;
        TextView tv_state;
        Button btn_start;
        Button btn_pause;
        Button btn_continue;
        Button btn_delete;
        ProgressBar progressBar;
    }

    public List<DownloadInfo> getList() {
        return list;
    }

    public void setList(List<DownloadInfo> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        if(list != null){
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.demo_layout_download_item, null);
            holder.tv_name = (TextView)convertView.findViewById(R.id.tv_name);
            holder.tv_state = (TextView)convertView.findViewById(R.id.tv_state);
            holder.btn_pause = (Button)convertView.findViewById(R.id.btn_pause);
            holder.btn_continue = (Button)convertView.findViewById(R.id.btn_continue);
            holder.btn_delete = (Button)convertView.findViewById(R.id.btn_delete);
            holder.progressBar = (ProgressBar)convertView.findViewById(R.id.progressBar);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        
        DownloadInfo bean = list.get(position);
        final String url = bean.getUrl();
        holder.tv_name.setText(bean.getName());
        holder.progressBar.setProgress(bean.getProgress());
        
        String state = bean.getState();
        if(!TextUtils.isEmpty(state)){
        	holder.tv_state.setText(bean.getState());
        }
        
        holder.btn_pause.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				DownloadManager.getInstance().pauseHandler(url);
			}
		});
        
        holder.btn_continue.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				DownloadManager.getInstance().continueHandler(url);
			}
		});
        
        holder.btn_delete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				List<DownloadInfo> newList = new ArrayList<DownloadInfo>();
				for(DownloadInfo b : list){
					if(!b.getUrl().equals(url)){
						newList.add(b);
					}
				}
				activity.refresh(newList);
				DownloadManager.getInstance().deleteHandler(url);
			}
		});
        return convertView;
    }

}
