package com.example.guju.entity;
public class Strategy{
 private int total;
private DataEntity strategyList;
 private int count;
 private String type;

 public String getType() {
  return type;
 }

 public void setType(String type) {
  this.type = type;
 }

 public int getTotal() {
  return total;
 }

 public void setTotal(int total) {
  this.total = total;
 }

 public DataEntity getStrategyList() {
  return strategyList;
 }

 public void setStrategyList(DataEntity strategyList) {
  this.strategyList = strategyList;
 }

 public int getCount() {
  return count;
 }

 public void setCount(int count) {
  this.count = count;
 }

 public static class DataEntity{
  private String categoryName;
  private String title;
  private String tuijian;
  private String description;
  private String covorPhotoId;
  private UserEntity user;

  public String getCategoryName() {
   return categoryName;
  }

  public void setCategoryName(String categoryName) {
   this.categoryName = categoryName;
  }

  public String getTitle() {
   return title;
  }

  public void setTitle(String title) {
   this.title = title;
  }

  public String getTuijian() {
   return tuijian;
  }

  public void setTuijian(String tuijian) {
   this.tuijian = tuijian;
  }

  public String getDescription() {
   return description;
  }

  public void setDescription(String description) {
   this.description = description;
  }

  public String getCovorPhotoId() {
   return covorPhotoId;
  }

  public void setCovorPhotoId(String covorPhotoId) {
   this.covorPhotoId = covorPhotoId;
  }

  public UserEntity getUser() {
   return user;
  }

  public void setUser(UserEntity user) {
   this.user = user;
  }

  public static class UserEntity{
   private String userImageSmall;
   private String userName;

   public String getUserImageSmall() {
    return userImageSmall;
   }

   public void setUserImageSmall(String userImageSmall) {
    this.userImageSmall = userImageSmall;
   }

   public String getUserName() {
    return userName;
   }

   public void setUserName(String userName) {
    this.userName = userName;
   }
  }
 }

/*{
        "total": 2804,
        "start": 0,
        "count": 10,
        "strategyList": [
        {
        "date": "",
        "featured": 0,
        "hasPublish": 0,
        "likeCount": 18,
        "contextRequired": 0,
        "checkName": "",
        "type": 0,
        "province": 0,
        "context": "",
        "checked": 0,
        "isUserCheck": 0,
        "createrId": 0,
        "id": 3325,
        "state": 0,
        "order": "COMMEND",
        "page_size": 0,
        "commendCity": 0,
        "covorPhotoId": 1409447,
        "likeState": 0,
        "createrName": "",
        "commentCount": 5,
        "tags": [],
        "categoryType": 0,
        "item_index": 0,
        "zhidin": 0,
        "noPublish": 0,
        "ids": [],
        "loginUserId": 0,
        "userType": 0,
        "categoryType2": 0,
        "contentGuide": "",
        "majia": 0,
        "city": 0,
        "tuijian": 0,
        "typeName": "",
        "description": "导语 \n\n\n\t现如今在传统装修行业树大根深、互联网家装风起云涌的大环境下，好窝装修作为互联网家装行业的翘楚，以改变装修行业现状为使命在汹涌的浪潮中奋斗着，这时有人就开始问，装修已经",
        "dateEnd": "",
        "title": "装饰工程施工进度表 谷居云管家每天施工看的见",
        "categoryName": "装修要点",
        "professionImage": "",
        "dateStart": "",
        "value": "",
        "categoryTop": 0,
        "noMaJiaLikeCount": 0,
        "sortStr": "",
        "commendDate": "",
        "commend": 0,
        "typeTitle": "",
        "checkDate": "",
        "userName": "",
        "userId": 340575,
        "keyWord": "",
        "category": 14,
        "user": {
        "followState": 0,
        "followCount": 21,
        "followedCount": 13,
        "userImageSmall": "http://img.guju.com.cn/user_images/86f0523a8cf5055a_0.jpg",
        "certified": false,
        "id": 340575,
        "userType": 0,
        "userName": "树没有叶子",
        "gujuService": 0,
        "userImageLarge": "http://img.guju.com.cn/user_images/86f0523a8cf5055a_2.jpg",
        "userImageMiddle": "http://img.guju.com.cn/user_images/86f0523a8cf5055a_1.jpg"
        },
        "categoryId": 0
        },
        }*/
}
