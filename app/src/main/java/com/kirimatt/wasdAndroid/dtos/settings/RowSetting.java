package com.kirimatt.wasdAndroid.dtos.settings;

import java.io.Serializable;

public abstract class RowSetting<T> implements Serializable {
    private String nameSetting;
    private String aliasSetting;
    private RowSettingType rowSettingType;

    protected RowSetting(String nameSetting, String aliasSetting, RowSettingType rowSettingType) {
        this.nameSetting = nameSetting;
        this.aliasSetting = aliasSetting;
        this.rowSettingType = rowSettingType;
    }

    public RowSettingType getRowSettingType() {
        return rowSettingType;
    }

    public void setRowSettingType(RowSettingType rowSettingType) {
        this.rowSettingType = rowSettingType;
    }

    public String getAliasSetting() {
        return aliasSetting;
    }

    public void setAliasSetting(String aliasSetting) {
        this.aliasSetting = aliasSetting;
    }

    public String getNameSetting() {
        return nameSetting;
    }

    public void setNameSetting(String nameSetting) {
        this.nameSetting = nameSetting;
    }

    public abstract T getValue();

    public abstract void setValue(T value);
}
