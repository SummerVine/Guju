package com.example.guju.entity;

/**
 * Created by liushuxin on 2016/7/5.
 */
public class DecoratePlan {
    private  String planName;
    private  String planImgUrl;
    private String planAuthor;
    private  String planDetail;
    private String planAuthorIcon;
    public DecoratePlan(){
    }
    public DecoratePlan(String planName,String planImgUrl,String planAuthor,String planDetail,String planAuthorIcon){
        this.planName=planName;
        this.planAuthor=planAuthor;
        this.planDetail=planDetail;
        this.planImgUrl=planImgUrl;
       this.planAuthorIcon=planAuthorIcon;
    }

    public String getPlanName(){
    return planName;
}
    public  void setPlanName(String planName)
    {
        this.planName=planName;
    }

    public String getPlanImgUrl(){
        return planImgUrl;
    }
    public  void setPlanImgUrl(String planImgUrl)
    {
        this.planImgUrl=planImgUrl;
    }

    public String getPlanAuthor(){
        return planAuthor;
    }
    public  void setPlanAuthor(String planAuthor)
    {
        this.planAuthor=planAuthor;
    }

    public String getPlanAuthorIcon(){
        return planAuthorIcon;
    }
    public  void setPlanAuthorIcon(String planAuthorIcon)
    {
        this.planAuthorIcon=planAuthorIcon;
    }

    public String getPlanDetail(){
        return planDetail;
    }
    public  void setPanDetail(String planDetail)
    {
        this.planDetail=planDetail;
    }

}
