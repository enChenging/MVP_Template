package com.release.mvp.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by long on 2016/10/11.
 * 视频实体
 */
@Entity
public class VideoInfo {

    /**
     * collect : false
     * cover : http://vimg3.ws.126.net/image/snapshot/2018/6/G/U/VDJTRBNGU.jpg
     * downloadSpeed : 0
     * downloadStatus : 0
     * isCollect : false
     * length : 15
     * loadedSize : 0
     * m3u8_url : http://flv.bn.netease.com/tvmrepo/2018/6/M/C/EDJTRBIMC/SD/movie_index.m3u8
     * mp4_url : http://flv3.bn.netease.com/tvmrepo/2018/6/M/C/EDJTRBIMC/SD/EDJTRBIMC-mobile.mp4
     * ptime : 2018-06-21 19:11:07
     * sectiontitle :
     * title : 这才是吃龙虾的正确方式……
     * totalSize : 0
     * vid : VDJTRBO7M
     */

    private boolean collect;
    private String cover;
    private int downloadSpeed;
    private int downloadStatus;
    private boolean isCollect;
    private int length;
    private int loadedSize;
    private String m3u8_url;
    private String mp4_url;
    private String ptime;
    private String sectiontitle;
    private String title;
    private int totalSize;
    private String vid;

    @Generated(hash = 689960324)
    public VideoInfo(boolean collect, String cover, int downloadSpeed, int downloadStatus,
                     boolean isCollect, int length, int loadedSize, String m3u8_url, String mp4_url,
                     String ptime, String sectiontitle, String title, int totalSize, String vid) {
        this.collect = collect;
        this.cover = cover;
        this.downloadSpeed = downloadSpeed;
        this.downloadStatus = downloadStatus;
        this.isCollect = isCollect;
        this.length = length;
        this.loadedSize = loadedSize;
        this.m3u8_url = m3u8_url;
        this.mp4_url = mp4_url;
        this.ptime = ptime;
        this.sectiontitle = sectiontitle;
        this.title = title;
        this.totalSize = totalSize;
        this.vid = vid;
    }

    @Generated(hash = 296402066)
    public VideoInfo() {
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getDownloadSpeed() {
        return downloadSpeed;
    }

    public void setDownloadSpeed(int downloadSpeed) {
        this.downloadSpeed = downloadSpeed;
    }

    public int getDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(int downloadStatus) {
        this.downloadStatus = downloadStatus;
    }

    public boolean isIsCollect() {
        return isCollect;
    }

    public void setIsCollect(boolean isCollect) {
        this.isCollect = isCollect;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLoadedSize() {
        return loadedSize;
    }

    public void setLoadedSize(int loadedSize) {
        this.loadedSize = loadedSize;
    }

    public String getM3u8_url() {
        return m3u8_url;
    }

    public void setM3u8_url(String m3u8_url) {
        this.m3u8_url = m3u8_url;
    }

    public String getMp4_url() {
        return mp4_url;
    }

    public void setMp4_url(String mp4_url) {
        this.mp4_url = mp4_url;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getSectiontitle() {
        return sectiontitle;
    }

    public void setSectiontitle(String sectiontitle) {
        this.sectiontitle = sectiontitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public boolean getCollect() {
        return this.collect;
    }

    public boolean getIsCollect() {
        return this.isCollect;
    }
}
