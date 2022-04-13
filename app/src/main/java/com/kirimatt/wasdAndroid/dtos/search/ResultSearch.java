package com.kirimatt.wasdAndroid.dtos.search;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultSearch that = (ResultSearch) o;
        return count == that.count && Objects.equals(rows, that.rows);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, rows);
    }
}
