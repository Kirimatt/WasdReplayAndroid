package com.kirimatt.wasdAndroid.dtos.settings;

public class RowStatusSetting extends RowSetting<Boolean> {
    private Boolean status;

    public RowStatusSetting(String nameSetting, String aliasSetting, Boolean status) {
        super(nameSetting, aliasSetting, RowSettingType.STATUS);
        this.status = status;
    }

    @Override
    public Boolean getValue() {
        return status;
    }

    @Override
    public void setValue(Boolean value) {
        this.status = value;
    }
}
