package com.example.guju.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/7/5.
 */
public class Strategy {
   private String categoryName;//安装名
    private String description;//具体描述
    private String title;//标题
    private String typeName;//安装标题
    private String covorPhotoId;//图片id
    private String userType;//好评
    private String context;//详细解说

 public Strategy() {
 }

 public Strategy(String categoryName, String context, String userType, String covorPhotoId, String typeName, String title, String description) {
  this.categoryName = categoryName;
  this.context = context;
  this.userType = userType;
  this.covorPhotoId = covorPhotoId;
  this.typeName = typeName;
  this.title = title;
  this.description = description;
 }

 public String getCovorPhotoId() {
  return covorPhotoId;
 }

 public void setCovorPhotoId(String covorPhotoId) {
  this.covorPhotoId = covorPhotoId;
 }

 public String getCategoryName() {
  return categoryName;
 }

 public void setCategoryName(String categoryName) {
  this.categoryName = categoryName;
 }

 public String getDescription() {
  return description;
 }

 public void setDescription(String description) {
  this.description = description;
 }

 public String getTitle() {
  return title;
 }

 public void setTitle(String title) {
  this.title = title;
 }

 public String getTypeName() {
  return typeName;
 }

 public void setTypeName(String typeName) {
  this.typeName = typeName;
 }

 public String getUserType() {
  return userType;
 }

 public void setUserType(String userType) {
  this.userType = userType;
 }

 public String getContext() {
  return context;
 }

 public void setContext(String context) {
  this.context = context;
 }
}
