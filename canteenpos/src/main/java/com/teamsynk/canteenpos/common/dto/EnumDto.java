package com.teamsynk.canteenpos.common.dto;

public class EnumDto {
    private String name;
    private String label;

    public EnumDto(String name, String label) {
        this.name = name;
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }
}
