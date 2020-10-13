package com.yesway.api_lib.moudel.common.response;

/**
 * author : guojingbu
 * date   : 2020/5/28
 * desc   :
 */
public class UnreadCountResponse {
    private int confirmVehicleMaintainCount;
    private int finishVehicleMaintainCount;
    private int newVehicleMaintainCount;

    public int getConfirmVehicleMaintainCount() {
        return confirmVehicleMaintainCount;
    }

    public void setConfirmVehicleMaintainCount(int confirmVehicleMaintainCount) {
        this.confirmVehicleMaintainCount = confirmVehicleMaintainCount;
    }

    public int getFinishVehicleMaintainCount() {
        return finishVehicleMaintainCount;
    }

    public void setFinishVehicleMaintainCount(int finishVehicleMaintainCount) {
        this.finishVehicleMaintainCount = finishVehicleMaintainCount;
    }

    public int getNewVehicleMaintainCount() {
        return newVehicleMaintainCount;
    }

    public void setNewVehicleMaintainCount(int newVehicleMaintainCount) {
        this.newVehicleMaintainCount = newVehicleMaintainCount;
    }
}
