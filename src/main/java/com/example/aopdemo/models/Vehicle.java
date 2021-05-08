package com.example.aopdemo.models;

import java.util.ArrayList;

public class Vehicle {
    private String vehicleRnNumber;
    private String chassisNo;
    private String driverId;
    private String dlNumber;
    private String vehicleOwnerName;
    private String vtsDeviceId;
    private String status;
    private ArrayList<CustomFields> customFields;
    private Long creationTime;
    private Long updateTime;
    private String source;
    private String vehicleModel;
    private String loadCapacity;
    private String floorType;
    private Boolean isTrackingEnabled;
    private String uuid;
    private String isDeleted;
    private String vehicleType;


    public Vehicle() {
    }


    public Vehicle(String vehicleRnNumber, String chassisNo, String driverId, String dlNumber, String vehicleOwnerName, String vtsDeviceId, String status, ArrayList<CustomFields> customFields, Long creationTime, Long updateTime, String source, String vehicleModel, String loadCapacity, String floorType, Boolean isTrackingEnabled, String uuid, String isDeleted, String vehicleType) {
        this.vehicleRnNumber = vehicleRnNumber;
        this.chassisNo = chassisNo;
        this.driverId = driverId;
        this.dlNumber = dlNumber;
        this.vehicleOwnerName = vehicleOwnerName;
        this.vtsDeviceId = vtsDeviceId;
        this.status = status;
        this.customFields = customFields;
        this.creationTime = creationTime;
        this.updateTime = updateTime;
        this.source = source;
        this.vehicleModel = vehicleModel;
        this.loadCapacity = loadCapacity;
        this.floorType = floorType;
        this.isTrackingEnabled = isTrackingEnabled;
        this.uuid = uuid;
        this.isDeleted = isDeleted;
        this.vehicleType = vehicleType;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<CustomFields> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(ArrayList<CustomFields> customFields) {
        this.customFields = customFields;
    }

    public Long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(String loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public String getFloorType() {
        return floorType;
    }

    public void setFloorType(String floorType) {
        this.floorType = floorType;
    }

    public Boolean getTrackingEnabled() {
        return isTrackingEnabled;
    }

    public void setTrackingEnabled(Boolean trackingEnabled) {
        isTrackingEnabled = trackingEnabled;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                ", vehicleRnNumber='" + vehicleRnNumber + '\'' +
                ", chassisNo='" + chassisNo + '\'' +
                ", driverId='" + driverId + '\'' +
                ", dlNumber='" + dlNumber + '\'' +
                ", vehicleOwnerName='" + vehicleOwnerName + '\'' +
                ", vtsDeviceId='" + vtsDeviceId + '\'' +
                ", status='" + status + '\'' +
                ", customFields=" + customFields +
                ", creationTime=" + creationTime +
                ", updateTime=" + updateTime +
                ", source='" + source + '\'' +
                ", vehicleModel='" + vehicleModel + '\'' +
                ", loadCapacity='" + loadCapacity + '\'' +
                ", floorType='" + floorType + '\'' +
                ", isTrackingEnabled=" + isTrackingEnabled +
                ", uuid='" + uuid + '\'' +
                ", isDeleted='" + isDeleted + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                '}';
    }
}
