package com.example.guju.entity;

/**
 * Created by liushuxin on 2016/7/5.
 */
public class DecoratePlan {
    private  String buildingId;// 4183
    private  String buildingName;// "远洋香奈"
    private  String title;//"亲子设计，超强清新收纳！"
    private  String styleShow;// "简约"
    private  String typeShow;// "公寓"
    private  String areaShow;//80平米
    private  String costShow;//"13.5"
    private  String coverPhoto;//1444528
    private  String roomStylePhotoId;// 1448843

    public DecoratePlan(  String buildingId,String roomStylePhotoId, String coverPhoto, String costShow, String typeShow, String styleShow, String title, String buildingName, String areaShow) {
       this.buildingId=buildingId;
        this.roomStylePhotoId = roomStylePhotoId;
        this.coverPhoto = coverPhoto;
        this.costShow = costShow;
        this.typeShow = typeShow;
        this.styleShow = styleShow;
        this.title = title;
        this.buildingName = buildingName;
        this.areaShow = areaShow;
    }

    public DecoratePlan(){
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getAreaShow() {
        return areaShow;
    }

    public void setAreaShow(String areaShow) {
        this.areaShow = areaShow;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStyleShow() {
        return styleShow;
    }

    public void setStyleShow(String styleShow) {
        this.styleShow = styleShow;
    }

    public String getTypeShow() {
        return typeShow;
    }

    public void setTypeShow(String typeShow) {
        this.typeShow = typeShow;
    }

    public String getCostShow() {
        return costShow;
    }

    public void setCostShow(String costShow) {
        this.costShow = costShow;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public String getRoomStylePhotoId() {
        return roomStylePhotoId;
    }

    public void setRoomStylePhotoId(String roomStylePhotoId) {
        this.roomStylePhotoId = roomStylePhotoId;
    }
}
