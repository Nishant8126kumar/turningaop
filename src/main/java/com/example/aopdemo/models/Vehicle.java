package com.example.aopdemo.models;

import java.util.ArrayList;

public class Vehicle {

    String vehicleId;
    String vehicleRnNumber;
    String chassisNo;
    String driverId;
    String dlNumber;
    String dlExpDate;
    String vehicleOwnerName;
    String vtsDeviceId;
    ArrayList<CustomFields> customFields;
    private long timeStamp;

    public Vehicle() {
    }

    public Vehicle(String vehicleId, String vehicleRnNumber, String chassisNo, String driverId, String dlNumber, String dlExpDate, String vehicleOwnerName, String vtsDeviceId, ArrayList<CustomFields> customFields, long timeStamp) {
        this.vehicleId = vehicleId;
        this.vehicleRnNumber = vehicleRnNumber;
        this.chassisNo = chassisNo;
        this.driverId = driverId;
        this.dlNumber = dlNumber;
        this.dlExpDate = dlExpDate;
        this.vehicleOwnerName = vehicleOwnerName;
        this.vtsDeviceId = vtsDeviceId;
        this.customFields = customFields;
        this.timeStamp = timeStamp;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public ArrayList<CustomFields> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(ArrayList<CustomFields> customFields) {
        this.customFields = customFields;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleRnNumber() {
        return vehicleRnNumber;
    }

    public void setVehicleRnNumber(String vehicleRnNumber) {
        this.vehicleRnNumber = vehicleRnNumber;
    }

    public String getChassisNo() {
        return chassisNo;
    }

    public void setChassisNo(String chassisNo) {
        this.chassisNo = chassisNo;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getDlNumber() {
        return dlNumber;
    }

    public void setDlNumber(String dlNumber) {
        this.dlNumber = dlNumber;
    }

    public String getDlExpDate() {
        return dlExpDate;
    }

    public void setDlExpDate(String dlExpDate) {
        this.dlExpDate = dlExpDate;
    }

    public String getVehicleOwnerName() {
        return vehicleOwnerName;
    }

    public void setVehicleOwnerName(String vehicleOwnerName) {
        this.vehicleOwnerName = vehicleOwnerName;
    }

    public String getVtsDeviceId() {
        return vtsDeviceId;
    }

    public void setVtsDeviceId(String vtsDeviceId) {
        this.vtsDeviceId = vtsDeviceId;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId='" + vehicleId + '\'' +
                ", vehicleRnNumber='" + vehicleRnNumber + '\'' +
                ", chassisNo='" + chassisNo + '\'' +
                ", driverId='" + driverId + '\'' +
                ", dlNumber='" + dlNumber + '\'' +
                ", dlExpDate='" + dlExpDate + '\'' +
                ", vehicleOwnerName='" + vehicleOwnerName + '\'' +
                ", vtsDeviceId='" + vtsDeviceId + '\'' +
                ", customFields=" + customFields +
                ", timeStamp='" + timeStamp + '\'' +
                '}';
    }
}
