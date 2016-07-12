package com.example.guju.bean;

/**
 * Created by green on 2016/7/10.
 */
public class Edithor {
    private String  StartNumber;
    private String HeadPhotos;
    private String Name;
    private Boolean Certified;
    private int editorId;
    private int LikeNum;
    private  int CommentNum;

    public int getLikeNum() {
        return LikeNum;
    }

    public void setLikeNum(int likeNum) {
        LikeNum = likeNum;
    }

    public int getCommentNum() {

        return CommentNum;
    }

    public void setCommentNum(int commentNum) {
        CommentNum = commentNum;
    }

    public int getEditorId() {
        return editorId;
    }

    public void setEditorId(int editorId) {
        this.editorId = editorId;
    }

    public Boolean getCertified() {
        return Certified;
    }

    public void setCertified(Boolean certified) {
        Certified = certified;
    }

    public Edithor() {
    }

    public Edithor(String  StartNumber, String HeadPhotos,
                   String Name,Boolean Certified, int editorId,int LikeNum,int CommentNum) {
        this.StartNumber=StartNumber;
        this.HeadPhotos = HeadPhotos;
        this.Name=Name;
        this.Certified=Certified;
        this.editorId=editorId;
        this.LikeNum=LikeNum;
        this.CommentNum=CommentNum;
    }


    public String getStartNumber() {
        return StartNumber;
    }

    public void setStartNumber(String startNumber) {
        StartNumber = startNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getHeadPhotos() {
        return HeadPhotos;
    }

    public void setHeadPhotos(String headPhotos) {
        HeadPhotos = headPhotos;
    }
}
