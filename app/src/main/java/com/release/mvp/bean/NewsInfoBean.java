package com.release.mvp.bean;

import java.util.List;

/**
 * @author Mr.release
 * @create 2019/4/17
 * @Describe
 */
public  class NewsInfoBean{


    /**
     * boardid : news2_bbs
     * digest : 图/朝中社图/朝中社海外网4月17日电据朝中社报道，朝鲜最高领导人金正恩16日突击视察朝鲜空军部队，他现场指导战斗飞行训练，并对飞行员的表现表示极大满意。图/朝
     * docid : ECUV1JO700018AOR
     * hasAD : 0
     * hasCover : false
     * hasHead : 0
     * hasIcon : false
     * hasImg : 0
     * imgextra : [{"imgsrc":"http://cms-bucket.ws.126.net/2019/04/17/9940c409f7d2445ea3a3013bbacea032.png"},{"imgsrc":"http://cms-bucket.ws.126.net/2019/04/17/93b725b8609b48518f39a3093e01403c.png"}]
     * imgsrc : http://cms-bucket.ws.126.net/2019/04/17/a3f6dafbd9d94fb28bacc63504a45883.png
     * lmodify : 2019-04-17 09:10:48
     * order : 0
     * postid : ECUV1JO700018AOR
     * priority : 92
     * ptime : 2019-04-17 08:18:00
     * replyCount : 0
     * source : 海外网
     * title : 金正恩突击视察空军部队 朝鲜战斗机近景曝光
     * votecount : 0
     */

    private String boardid;
    private String digest;
    private String docid;
    private int hasAD;
    private boolean hasCover;
    private int hasHead;
    private boolean hasIcon;
    private int hasImg;
    private String imgsrc;
    private String lmodify;
    private int order;
    private String postid;
    private int priority;
    private String ptime;
    private int replyCount;
    private String source;
    private String title;
    private int votecount;
    private List<ImgextraBean> imgextra;
    private List<AdData> ads;
    private String skipID;
    private String alias;
    private String skipType;
    private String tname;
    private String specialID;
    private String photosetID;

    public String getBoardid() {
        return boardid;
    }

    public void setBoardid(String boardid) {
        this.boardid = boardid;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public int getHasAD() {
        return hasAD;
    }

    public void setHasAD(int hasAD) {
        this.hasAD = hasAD;
    }

    public boolean isHasCover() {
        return hasCover;
    }

    public void setHasCover(boolean hasCover) {
        this.hasCover = hasCover;
    }

    public int getHasHead() {
        return hasHead;
    }

    public void setHasHead(int hasHead) {
        this.hasHead = hasHead;
    }

    public boolean isHasIcon() {
        return hasIcon;
    }

    public void setHasIcon(boolean hasIcon) {
        this.hasIcon = hasIcon;
    }

    public int getHasImg() {
        return hasImg;
    }

    public void setHasImg(int hasImg) {
        this.hasImg = hasImg;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getLmodify() {
        return lmodify;
    }

    public void setLmodify(String lmodify) {
        this.lmodify = lmodify;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVotecount() {
        return votecount;
    }

    public void setVotecount(int votecount) {
        this.votecount = votecount;
    }

    public List<ImgextraBean> getImgextra() {
        return imgextra;
    }

    public void setImgextra(List<ImgextraBean> imgextra) {
        this.imgextra = imgextra;
    }
    public List<AdData> getAds() {
        return ads;
    }

    public void setAds(List<AdData> ads) {
        this.ads = ads;
    }


    public String getSkipID() {
        return skipID;
    }

    public void setSkipID(String skipID) {
        this.skipID = skipID;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getSkipType() {
        return skipType;
    }

    public void setSkipType(String skipType) {
        this.skipType = skipType;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getSpecialID() {
        return specialID;
    }

    public void setSpecialID(String specialID) {
        this.specialID = specialID;
    }

    public String getPhotosetID() {
        return photosetID;
    }

    public void setPhotosetID(String photosetID) {
        this.photosetID = photosetID;
    }

    @Override
    public String toString() {
        return "NewsInfoBean{" +
                "boardid='" + boardid + '\'' +
                ", digest='" + digest + '\'' +
                ", docid='" + docid + '\'' +
                ", hasAD=" + hasAD +
                ", hasCover=" + hasCover +
                ", hasHead=" + hasHead +
                ", hasIcon=" + hasIcon +
                ", hasImg=" + hasImg +
                ", imgsrc='" + imgsrc + '\'' +
                ", lmodify='" + lmodify + '\'' +
                ", order=" + order +
                ", postid='" + postid + '\'' +
                ", priority=" + priority +
                ", ptime='" + ptime + '\'' +
                ", replyCount=" + replyCount +
                ", source='" + source + '\'' +
                ", title='" + title + '\'' +
                ", votecount=" + votecount +
                ", imgextra=" + imgextra +
                ", ads=" + ads +
                ", skipID='" + skipID + '\'' +
                ", alias='" + alias + '\'' +
                ", skipType='" + skipType + '\'' +
                ", tname='" + tname + '\'' +
                ", specialID='" + specialID + '\'' +
                ", photosetID='" + photosetID + '\'' +
                '}';
    }


    public static class ImgextraBean {
        /**
         * imgsrc : http://cms-bucket.ws.126.net/2019/04/17/9940c409f7d2445ea3a3013bbacea032.png
         */

        private String imgsrc;

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }
    }

    public static class AdData {
        private String title;
        private String tag;
        private String imgsrc;
        private String subtitle;
        private String url;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
