package com.kirimatt.wasdAndroid.dtos.settings;

public class RowFieldSetting extends RowSetting<Float> {
    private Float delay;

    public RowFieldSetting(String nameSetting, String aliasSetting, Float delay) {
        super(nameSetting, aliasSetting, RowSettingType.FIELD);
        this.delay = delay;
    }

    @Override
    public Float getValue() {
        return delay;
    }


    @Override
    public void setValue(Float value) {
        this.delay = value;
    }
}
