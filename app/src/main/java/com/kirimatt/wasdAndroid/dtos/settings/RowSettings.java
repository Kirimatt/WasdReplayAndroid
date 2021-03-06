package com.kirimatt.wasdAndroid.dtos.settings;

import java.io.Serializable;

public class RowSettings implements Serializable {
    private String nameSetting;
    private String aliasSetting;
    private Boolean status;

    public RowSettings(String nameSetting, String aliasSetting, Boolean status) {
        this.nameSetting = nameSetting;
        this.aliasSetting = aliasSetting;
        this.status = status;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
