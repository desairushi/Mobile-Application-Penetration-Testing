package com.example.myapplication;

public class DataClass {

    private String dataTitle;
    private String dataDose;
    private String dataFreq;

    public DataClass(String dataTitle, String dataDose, String dataFreq) {
        this.dataTitle = dataTitle;
        this.dataDose = dataDose;
        this.dataFreq = dataFreq;
    }
    public DataClass() {

    }


    public String getDataTitle() {
        return dataTitle;
    }

    public String getDataDose() {
        return dataDose;
    }

    public String getDataFreq() {
        return dataFreq;
    }


}
