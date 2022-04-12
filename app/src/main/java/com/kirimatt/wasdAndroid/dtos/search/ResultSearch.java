package com.kirimatt.wasdAndroid.dtos.search;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ResultSearch implements Serializable {
    @SerializedName("count")
    private int count;
    @SerializedName("rows")
    private List<Row> rows;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }
}
