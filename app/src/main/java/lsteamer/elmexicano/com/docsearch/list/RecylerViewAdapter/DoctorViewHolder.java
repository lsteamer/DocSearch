package lsteamer.elmexicano.com.docsearch.list.RecylerViewAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import lsteamer.elmexicano.com.docsearch.R;
import lsteamer.elmexicano.com.docsearch.list.model.Doctor;

public class DoctorViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.doctor_image_view)
    private ImageView doctorProfileImageInHolder;

    @BindView(R.id.doctor_name_text_view)
    private TextView doctorNameInHolder;

    @BindView(R.id.doctor_address_text_view)
    private TextView doctorAddressInHolder;

    public DoctorViewHolder(View itemView) {
        super(itemView);
    }

    public void populate(Context context, Doctor doctor){
        doctorNameInHolder.setText(doctor.getName());
        doctorAddressInHolder.setText(doctor.getAddress());

    }

}
