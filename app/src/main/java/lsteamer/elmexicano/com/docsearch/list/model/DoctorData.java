package lsteamer.elmexicano.com.docsearch.list.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DoctorData {


    @SerializedName("doctors")
    @Expose
    private List<Doctor> listDoctors;

    @SerializedName("lastKey")
    @Expose
    private String lastKey;

    public List<Doctor> getListDoctors() {
        return listDoctors;
    }

    public void setListDoctors(List<Doctor> listDoctors) {
        this.listDoctors = listDoctors;
    }

    public String getLastKey() {
        return lastKey;
    }

    public void setLastKey(String lastKey) {
        this.lastKey = lastKey;
    }
}
