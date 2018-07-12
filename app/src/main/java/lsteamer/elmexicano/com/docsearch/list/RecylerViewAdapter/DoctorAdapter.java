package lsteamer.elmexicano.com.docsearch.list.RecylerViewAdapter;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import lsteamer.elmexicano.com.docsearch.R;
import lsteamer.elmexicano.com.docsearch.list.model.Doctor;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorViewHolder>{


    private AppCompatActivity mActivity;
    private LayoutInflater inflater;
    private ArrayList<Doctor> doctorList;

    public DoctorAdapter(AppCompatActivity mActivity, ArrayList<Doctor> doctorList) {
        this.mActivity = mActivity;
        this.doctorList = doctorList;
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
        holder.populate(mActivity.getApplicationContext(), doctor);
    }


    @Override
    public int getItemCount() {
        return doctorList.size();
    }

}
