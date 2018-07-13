package lsteamer.elmexicano.com.docsearch.list.recycler;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;

import java.util.List;

import lsteamer.elmexicano.com.docsearch.R;
import lsteamer.elmexicano.com.docsearch.list.model.Doctor;
import lsteamer.elmexicano.com.docsearch.utils.UrlContents;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorViewHolder> {


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


        holder.populate(doctor);


        if (doctor.getPhotoId() != null) {
            //if there's an image we dynamically get the images through Glide
            GlideUrl glideUrl = new GlideUrl(doctor.getPhotoIdUrl(), new LazyHeaders.Builder().addHeader(urlContents.getAuthorization(), urlContents.getBearer()).build());
            Glide.with(mActivity).load(glideUrl).into(holder.getDoctorProfileImageInHolder());
        } else
            holder.getDoctorProfileImageInHolder().setImageResource(R.drawable.doctor_profile_default);
    }


    @Override
    public int getItemCount() {
        return doctorList.size();
    }

}
