package com.release.mvp.bean;

import java.util.List;

/**
 * @author Mr.release
 * @create 2019/4/17
 * @Describe
 */
public class PhotoSetInfoBean {

    /**
     * autoid :
     * boardid : photoview_bbs
     * clientadurl :
     * commenturl : http://comment.news.163.com/photoview_bbs/PHOT2675A000100A.html
     * cover : http://pic-bucket.ws.126.net/photo/0001/2019-04-17/ECUU51H500AO0001NOS.jpg
     * createdate : 2019-04-17 08:04:54
     * creator : 尤园园
     * datatime : 2019-04-17 08:05:22
     * desc : 当地时间4月16日，法国消防部门宣布巴黎圣母院大火被完全扑灭。巴黎圣母院的大部分顶部被烧毁，屋顶出现一个大洞。火灾过后，大教堂内部四处散落着烧焦的碎片。
     * imgsum : 3
     * neteasecode :
     * photos : [{"cimgurl":"http://pic-bucket.ws.126.net/photo/0001/2019-04-17/ECUU51H200AO0001NOS.jpg","imgtitle":"","imgurl":"http://pic-bucket.ws.126.net/photo/0001/2019-04-17/ECUU51H200AO0001NOS.jpg","newsurl":"#","note":"当地时间4月16日，法国消防部门宣布巴黎圣母院大火被完全扑灭。巴黎圣母院的大部分顶部被烧毁，屋顶出现一个大洞。火灾过后，大教堂内部四处散落着烧焦的碎片。（来源：中国新闻网）","photohtml":"http://news.163.com/photoview/00AO0001/2301098.html#p=ECUU51H200AO0001NOS","photoid":"ECUU51H200AO0001NOS","simgurl":"http://pic-bucket.ws.126.net/photo/0001/2019-04-17/ECUU51H200AO0001NOS.jpg?imageView&thumbnail=100y75","squareimgurl":"http://pic-bucket.ws.126.net/photo/0001/2019-04-17/ECUU51H200AO0001NOS.jpg?imageView&thumbnail=400y400","timgurl":"http://pic-bucket.ws.126.net/photo/0001/2019-04-17/ECUU51H200AO0001NOS.jpg?imageView&thumbnail=160y120"},{"cimgurl":"http://pic-bucket.ws.126.net/photo/0001/2019-04-17/ECUU51H500AO0001NOS.jpg","imgtitle":"","imgurl":"http://pic-bucket.ws.126.net/photo/0001/2019-04-17/ECUU51H500AO0001NOS.jpg","newsurl":"#","note":"当地时间4月16日，法国消防部门宣布巴黎圣母院大火被完全扑灭。巴黎圣母院的大部分顶部被烧毁，屋顶出现一个大洞。火灾过后，大教堂内部四处散落着烧焦的碎片。（来源：中国新闻网）","photohtml":"http://news.163.com/photoview/00AO0001/2301098.html#p=ECUU51H500AO0001NOS","photoid":"ECUU51H500AO0001NOS","simgurl":"http://pic-bucket.ws.126.net/photo/0001/2019-04-17/ECUU51H500AO0001NOS.jpg?imageView&thumbnail=100y75","squareimgurl":"http://pic-bucket.ws.126.net/photo/0001/2019-04-17/ECUU51H500AO0001NOS.jpg?imageView&thumbnail=400y400","timgurl":"http://pic-bucket.ws.126.net/photo/0001/2019-04-17/ECUU51H500AO0001NOS.jpg?imageView&thumbnail=160y120"},{"cimgurl":"http://pic-bucket.ws.126.net/photo/0001/2019-04-17/ECUU51H700AO0001NOS.jpg","imgtitle":"","imgurl":"http://pic-bucket.ws.126.net/photo/0001/2019-04-17/ECUU51H700AO0001NOS.jpg","newsurl":"#","note":"当地时间4月16日，法国消防部门宣布巴黎圣母院大火被完全扑灭。巴黎圣母院的大部分顶部被烧毁，屋顶出现一个大洞。火灾过后，大教堂内部四处散落着烧焦的碎片。（来源：中国新闻网）","photohtml":"http://news.163.com/photoview/00AO0001/2301098.html#p=ECUU51H700AO0001NOS","photoid":"ECUU51H700AO0001NOS","simgurl":"http://pic-bucket.ws.126.net/photo/0001/2019-04-17/ECUU51H700AO0001NOS.jpg?imageView&thumbnail=100y75","squareimgurl":"http://pic-bucket.ws.126.net/photo/0001/2019-04-17/ECUU51H700AO0001NOS.jpg?imageView&thumbnail=400y400","timgurl":"http://pic-bucket.ws.126.net/photo/0001/2019-04-17/ECUU51H700AO0001NOS.jpg?imageView&thumbnail=160y120"}]
     * postid : PHOT2675A000100A
     * relatedids : []
     * reporter :
     * scover : http://pic-bucket.ws.126.net/photo/0001/2019-04-17/ECUU51H500AO0001NOS.jpg?imageView&thumbnail=100y75
     * series :
     * setname : 巴黎圣母院灾后画面曝光 屋顶洞开遍地焦炭
     * settag : 巴黎圣母院，大火，消防
     * source : 中国新闻网
     * tcover : http://pic-bucket.ws.126.net/photo/0001/2019-04-17/ECUU51H500AO0001NOS.jpg?imageView&thumbnail=160y120
     * url : http://news.163.com/photoview/00AO0001/2301098.html
     */

    private String autoid;
    private String boardid;
    private String clientadurl;
    private String commenturl;
    private String cover;
    private String createdate;
    private String creator;
    private String datatime;
    private String desc;
    private String imgsum;
    private String neteasecode;
    private String postid;
    private String reporter;
    private String scover;
    private String series;
    private String setname;
    private String settag;
    private String source;
    private String tcover;
    private String url;
    private List<PhotosBean> photos;
    private List<?> relatedids;

    public String getAutoid() {
        return autoid;
    }

    public void setAutoid(String autoid) {
        this.autoid = autoid;
    }

    public String getBoardid() {
        return boardid;
    }

    public void setBoardid(String boardid) {
        this.boardid = boardid;
    }

    public String getClientadurl() {
        return clientadurl;
    }

    public void setClientadurl(String clientadurl) {
        this.clientadurl = clientadurl;
    }

    public String getCommenturl() {
        return commenturl;
    }

    public void setCommenturl(String commenturl) {
        this.commenturl = commenturl;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDatatime() {
        return datatime;
    }

    public void setDatatime(String datatime) {
        this.datatime = datatime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImgsum() {
        return imgsum;
    }

    public void setImgsum(String imgsum) {
        this.imgsum = imgsum;
    }

    public String getNeteasecode() {
        return neteasecode;
    }

    public void setNeteasecode(String neteasecode) {
        this.neteasecode = neteasecode;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getScover() {
        return scover;
    }

    public void setScover(String scover) {
        this.scover = scover;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getSetname() {
        return setname;
    }

    public void setSetname(String setname) {
        this.setname = setname;
    }

    public String getSettag() {
        return settag;
    }

    public void setSettag(String settag) {
        this.settag = settag;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTcover() {
        return tcover;
    }

    public void setTcover(String tcover) {
        this.tcover = tcover;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<PhotosBean> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotosBean> photos) {
        this.photos = photos;
    }

    public List<?> getRelatedids() {
        return relatedids;
    }

    public void setRelatedids(List<?> relatedids) {
        this.relatedids = relatedids;
    }

    @Override
    public String toString() {
        return "PhotoSetInfoBean{" +
                "autoid='" + autoid + '\'' +
                ", boardid='" + boardid + '\'' +
                ", clientadurl='" + clientadurl + '\'' +
                ", commenturl='" + commenturl + '\'' +
                ", cover='" + cover + '\'' +
                ", createdate='" + createdate + '\'' +
                ", creator='" + creator + '\'' +
                ", datatime='" + datatime + '\'' +
                ", desc='" + desc + '\'' +
                ", imgsum='" + imgsum + '\'' +
                ", neteasecode='" + neteasecode + '\'' +
                ", postid='" + postid + '\'' +
                ", reporter='" + reporter + '\'' +
                ", scover='" + scover + '\'' +
                ", series='" + series + '\'' +
                ", setname='" + setname + '\'' +
                ", settag='" + settag + '\'' +
                ", source='" + source + '\'' +
                ", tcover='" + tcover + '\'' +
                ", url='" + url + '\'' +
                ", photos=" + photos +
                ", relatedids=" + relatedids +
                '}';
    }

    public static class PhotosBean {
        /**
         * cimgurl : http://pic-bucket.ws.126.net/photo/0001/2019-04-17/ECUU51H200AO0001NOS.jpg
         * imgtitle :
         * imgurl : http://pic-bucket.ws.126.net/photo/0001/2019-04-17/ECUU51H200AO0001NOS.jpg
         * newsurl : #
         * note : 当地时间4月16日，法国消防部门宣布巴黎圣母院大火被完全扑灭。巴黎圣母院的大部分顶部被烧毁，屋顶出现一个大洞。火灾过后，大教堂内部四处散落着烧焦的碎片。（来源：中国新闻网）
         * photohtml : http://news.163.com/photoview/00AO0001/2301098.html#p=ECUU51H200AO0001NOS
         * photoid : ECUU51H200AO0001NOS
         * simgurl : http://pic-bucket.ws.126.net/photo/0001/2019-04-17/ECUU51H200AO0001NOS.jpg?imageView&thumbnail=100y75
         * squareimgurl : http://pic-bucket.ws.126.net/photo/0001/2019-04-17/ECUU51H200AO0001NOS.jpg?imageView&thumbnail=400y400
         * timgurl : http://pic-bucket.ws.126.net/photo/0001/2019-04-17/ECUU51H200AO0001NOS.jpg?imageView&thumbnail=160y120
         */

        private String cimgurl;
        private String imgtitle;
        private String imgurl;
        private String newsurl;
        private String note;
        private String photohtml;
        private String photoid;
        private String simgurl;
        private String squareimgurl;
        private String timgurl;

        public String getCimgurl() {
            return cimgurl;
        }

        public void setCimgurl(String cimgurl) {
            this.cimgurl = cimgurl;
        }

        public String getImgtitle() {
            return imgtitle;
        }

        public void setImgtitle(String imgtitle) {
            this.imgtitle = imgtitle;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getNewsurl() {
            return newsurl;
        }

        public void setNewsurl(String newsurl) {
            this.newsurl = newsurl;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getPhotohtml() {
            return photohtml;
        }

        public void setPhotohtml(String photohtml) {
            this.photohtml = photohtml;
        }

        public String getPhotoid() {
            return photoid;
        }

        public void setPhotoid(String photoid) {
            this.photoid = photoid;
        }

        public String getSimgurl() {
            return simgurl;
        }

        public void setSimgurl(String simgurl) {
            this.simgurl = simgurl;
        }

        public String getSquareimgurl() {
            return squareimgurl;
        }

        public void setSquareimgurl(String squareimgurl) {
            this.squareimgurl = squareimgurl;
        }

        public String getTimgurl() {
            return timgurl;
        }

        public void setTimgurl(String timgurl) {
            this.timgurl = timgurl;
        }

        @Override
        public String toString() {
            return "PhotosBean{" +
                    "cimgurl='" + cimgurl + '\'' +
                    ", imgtitle='" + imgtitle + '\'' +
                    ", imgurl='" + imgurl + '\'' +
                    ", newsurl='" + newsurl + '\'' +
                    ", note='" + note + '\'' +
                    ", photohtml='" + photohtml + '\'' +
                    ", photoid='" + photoid + '\'' +
                    ", simgurl='" + simgurl + '\'' +
                    ", squareimgurl='" + squareimgurl + '\'' +
                    ", timgurl='" + timgurl + '\'' +
                    '}';
        }
    }
}
