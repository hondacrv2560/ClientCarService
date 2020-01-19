package com.example.client.Models;

public class RepairWindshield {
    public String idRepairWindshield;
    public String titleRepairWindshield;
    public String priceRepairWindshield;

    public RepairWindshield() {
    }

    public RepairWindshield(String idRepairWindshield, String titleRepairWindshield, String priceRepairWindshield) {
        this.idRepairWindshield = idRepairWindshield;
        this.titleRepairWindshield = titleRepairWindshield;
        this.priceRepairWindshield = priceRepairWindshield;
    }

    public String getIdRepairWindshield() {
        return idRepairWindshield;
    }

    public void setIdRepairWindshield(String idRepairWindshield) {
        this.idRepairWindshield = idRepairWindshield;
    }

    public String getTitleRepairWindshield() {
        return titleRepairWindshield;
    }

    public void setTitleRepairWindshield(String titleRepairWindshield) {
        this.titleRepairWindshield = titleRepairWindshield;
    }

    public String getPriceRepairWindshield() {
        return priceRepairWindshield;
    }

    public void setPriceRepairWindshield(String priceRepairWindshield) {
        this.priceRepairWindshield = priceRepairWindshield;
    }
}
