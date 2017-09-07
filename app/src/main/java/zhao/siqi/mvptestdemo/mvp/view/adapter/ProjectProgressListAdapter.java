
package zhao.siqi.mvptestdemo.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import zhao.siqi.mvptestdemo.R;
import zhao.siqi.mvptestdemo.bean.ProjectProgressList;
import zhao.siqi.mvptestdemo.util.DateUtils;
import zhao.siqi.mvptestdemo.util.ToastU;

/**
 * 项目进展列表
 * Created by Bill on 17/8/2.
 */
public class ProjectProgressListAdapter extends BaseAdapter {

    private Context context;
    private List<ProjectProgressList.DataBean> mProjectContactDatas;

    public ProjectProgressListAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<ProjectProgressList.DataBean> mProjectContactDatas) {
        this.mProjectContactDatas = mProjectContactDatas;

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mProjectContactDatas == null ? 0 : mProjectContactDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mProjectContactDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_project_proress_list, null);

            holder.tv_center = (TextView) convertView.findViewById(R.id.tv_center);
            holder.tv_researcher = (TextView) convertView.findViewById(R.id.tv_researcher);
            holder.tv_week = (TextView) convertView.findViewById(R.id.tv_week);
            holder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
            holder.tv_chose_num = (TextView) convertView.findViewById(R.id.tv_chose_num);
            holder.tv_random_num = (TextView) convertView.findViewById(R.id.tv_random_num);
            holder.tv_sae_num = (TextView) convertView.findViewById(R.id.tv_sae_num);
            holder.iv_direct = (ImageView) convertView.findViewById(R.id.iv_direct);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 内容
        final ProjectProgressList.DataBean project = mProjectContactDatas.get(position);

        holder.tv_center.setText(project.getCore_content());
        holder.tv_researcher.setText(project.getCore_crc());
        holder.tv_week.setText(project.getWeek());
        holder.tv_date.setText(DateUtils.getStrDate_China1_3(Long.parseLong(project.getDay_time())));
        holder.tv_chose_num.setText(project.getScreening_num());
        holder.tv_random_num.setText(project.getRandom_num());
        holder.tv_sae_num.setText(project.getSae_num());

        // 点击查看
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastU.showLongToast("点我啦。。。。。。。");
            }
        });

        return convertView;
    }

    private class ViewHolder {
        ImageView iv_direct;
        TextView tv_center, tv_researcher, tv_week, tv_date, tv_chose_num, tv_random_num, tv_sae_num;
    }

}
