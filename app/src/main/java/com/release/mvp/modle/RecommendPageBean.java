package com.release.mvp.modle;

import java.util.List;

/**
 * @author Mr.release
 * @create 2019/3/26
 * @Describe
 */
public class RecommendPageBean {
    /**
     * data : [{"id":"8913","title":"你必须要知道的：中国茶之道","name":"你必须要知道的：中国茶之道","link":"http://sns.maimaicha.com/news/detail/8913","content":"你必须要知道的：中国茶之道","image":"http://s1.sns.maimaicha.com/images/2017/08/29/d4a2242cf5936533a16640409479b2da.jpg","image_s":"http://s1.sns.maimaicha.com/images/2017/08/29/a5737d1a41435ba8a7e682d1572ec8e7.jpg"},{"id":"8912","title":"小茶童趵突泉取水惊艳泉城","name":"小茶童趵突泉取水惊艳泉城","link":"http://sns.maimaicha.com/news/detail/8912","content":"小茶童趵突泉取水惊艳泉城","image":"http://s1.sns.maimaicha.com/images/2017/08/29/79900309da4cb0baccb9327b54f0ffb0.jpg","image_s":"http://s1.sns.maimaicha.com/images/2017/08/29/bd9fd3459940b722b53dac0820aa5b05.jpg"},{"id":"8732","title":"你见过用茶叶手绘作画吗？","name":"你见过用茶叶手绘作画吗？","link":"http://sns.maimaicha.com/news/detail/8732","content":"","image":"http://s1.sns.maimaicha.com/images/2017/08/29/3b873ca55768f8dfc18d73a36d834c80.jpg","image_s":"http://s1.sns.maimaicha.com/images/2017/08/29/f23c0e2b94529a85bef37ab4f956507e.jpg"}]
     * errorMessage : success
     */

    private String errorMessage;
    private List<DataBean> data;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 8913
         * title : 你必须要知道的：中国茶之道
         * name : 你必须要知道的：中国茶之道
         * link : http://sns.maimaicha.com/news/detail/8913
         * content : 你必须要知道的：中国茶之道
         * image : http://s1.sns.maimaicha.com/images/2017/08/29/d4a2242cf5936533a16640409479b2da.jpg
         * image_s : http://s1.sns.maimaicha.com/images/2017/08/29/a5737d1a41435ba8a7e682d1572ec8e7.jpg
         */

        private String id;
        private String title;
        private String name;
        private String link;
        private String content;
        private String image;
        private String image_s;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getImage_s() {
            return image_s;
        }

        public void setImage_s(String image_s) {
            this.image_s = image_s;
        }
    }
}
