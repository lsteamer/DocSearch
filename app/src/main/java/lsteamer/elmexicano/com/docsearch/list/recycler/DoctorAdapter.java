package lsteamer.elmexicano.com.docsearch.list.recycler;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;

import java.util.List;

import lsteamer.elmexicano.com.docsearch.R;
import lsteamer.elmexicano.com.docsearch.list.model.Doctor;
import lsteamer.elmexicano.com.docsearch.utils.UrlContents;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorViewHolder>{


    private AppCompatActivity mActivity;
    private LayoutInflater inflater;
    private List<Doctor> doctorList;
    private UrlContents urlContents;

    public DoctorAdapter(AppCompatActivity mActivity, List<Doctor> doctorList, UrlContents urlContents) {
        this.mActivity = mActivity;
        this.doctorList = doctorList;
        this.urlContents = urlContents;
        inflater = mActivity.getLayoutInflater();
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.doctor_list_item, parent, false);
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        final Doctor doctor = doctorList.get(position);

        TextView name = holder.getDoctorNameInHolder();
        TextView address = holder.getDoctorAddressInHolder();
        ImageView some = holder.getDoctorProfileImageInHolder();

        if(doctor.getPhotoId()!=null&& doctor.isPhotoIdStatus()){


            GlideUrl glideUrl = new GlideUrl(doctor.getPhotoIdUrl(), new LazyHeaders.Builder().addHeader(urlContents.getAuthorization(), urlContents.getBearer()).build());
            Glide.with(mActivity).load(glideUrl).into(some);
            name.setText(doctor.getName());
            address.setText(doctor.getAddress());

        }
        else{
            some.setImageResource(R.drawable.doctor_profile_default);
            name.setText(doctor.getName());
            address.setText(doctor.getAddress());
        }
    }


    @Override
    public int getItemCount() {
        return doctorList.size();
    }

}
