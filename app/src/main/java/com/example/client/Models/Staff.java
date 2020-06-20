package com.example.client.Models;

public class Staff {
    public String functionStaff;
    public String nameStaff;
    public String surnameStaff;
    public String numPhoneStaff;
    public String seriesPassportStaff;
    public String numberPassportStaff;
    public String passportIssuingAuthorityStaff;
    public String dateIssuePassportStaff;
    public String dateBirthStaff;

    public Staff(String functionStaff, String nameStaff, String surnameStaff, String numPhoneStaff, String seriesPassportStaff, String numberPassportStaff, String passportIssuingAuthorityStaff, String dateIssuePassportStaff, String dateBirthStaff) {
        this.functionStaff = functionStaff;
        this.nameStaff = nameStaff;
        this.surnameStaff = surnameStaff;
        this.numPhoneStaff = numPhoneStaff;
        this.seriesPassportStaff = seriesPassportStaff;
        this.numberPassportStaff = numberPassportStaff;
        this.passportIssuingAuthorityStaff = passportIssuingAuthorityStaff;
        this.dateIssuePassportStaff = dateIssuePassportStaff;
        this.dateBirthStaff = dateBirthStaff;
    }

    public Staff() {
    }

    public String getFunctionStaff() {
        return functionStaff;
    }

    public void setFunctionStaff(String functionStaff) {
        this.functionStaff = functionStaff;
    }

    public String getNameStaff() {
        return nameStaff;
    }

    public void setNameStaff(String nameStaff) {
        this.nameStaff = nameStaff;
    }

    public String getSurnameStaff() {
        return surnameStaff;
    }

    public void setSurnameStaff(String surnameStaff) {
        this.surnameStaff = surnameStaff;
    }

    public String getNumPhoneStaff() {
        return numPhoneStaff;
    }

    public void setNumPhoneStaff(String numPhoneStaff) {
        this.numPhoneStaff = numPhoneStaff;
    }

    public String getSeriesPassportStaff() {
        return seriesPassportStaff;
    }

    public void setSeriesPassportStaff(String seriesPassportStaff) {
        this.seriesPassportStaff = seriesPassportStaff;
    }

    public String getNumberPassportStaff() {
        return numberPassportStaff;
    }

    public void setNumberPassportStaff(String numberPassportStaff) {
        this.numberPassportStaff = numberPassportStaff;
    }

    public String getPassportIssuingAuthorityStaff() {
        return passportIssuingAuthorityStaff;
    }

    public void setPassportIssuingAuthorityStaff(String passportIssuingAuthorityStaff) {
        this.passportIssuingAuthorityStaff = passportIssuingAuthorityStaff;
    }

    public String getDateIssuePassportStaff() {
        return dateIssuePassportStaff;
    }

    public void setDateIssuePassportStaff(String dateIssuePassportStaff) {
        this.dateIssuePassportStaff = dateIssuePassportStaff;
    }

    public String getDateBirthStaff() {
        return dateBirthStaff;
    }

    public void setDateBirthStaff(String dateBirthStaff) {
        this.dateBirthStaff = dateBirthStaff;
    }
}
