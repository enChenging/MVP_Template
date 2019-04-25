package com.release.mvp.bean;

import java.util.List;

/**
 * @author Mr.release
 * @create 2019/4/23
 * @Describe
 */
public class RecommendPageBean {

    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2019-04-23 13:20","title":"微软日本公司宣布今年8月每周休三天 并发放补助","description":"网易IT","picUrl":"http://cms-bucket.ws.126.net/2019/04/23/1ec7cc26d084402196be1d884e293110.png?imageView&thumbnail=200y140","url":"https://tech.163.com/19/0423/13/EDEUJ4U100097U7T.html"},{"ctime":"2019-04-23 08:43","title":"特斯拉发布自动驾驶定制芯片：称比英伟达好太多","description":"网易IT","picUrl":"http://cms-bucket.ws.126.net/2019/04/23/4e6adc5252de4acb80cf7a5d066cc63a.png?imageView&thumbnail=200y140","url":"https://tech.163.com/19/0423/08/EDEEO34300097U7T.html"},{"ctime":"2019-04-23 08:35","title":"苹果成亚马逊AWS大客户：一个月要花3000万美元","description":"网易IT","picUrl":"http://cms-bucket.ws.126.net/2019/04/23/fc5cd91d411846a09dc0c4d094bfb030.png?imageView&thumbnail=200y140","url":"https://tech.163.com/19/0423/08/EDEEA0L600097U7T.html"},{"ctime":"2019-04-23 08:32","title":"要与英伟达分手？马斯克称特斯拉有最好芯片","description":"网易IT","picUrl":"http://cms-bucket.ws.126.net/2019/04/23/372676c70b24484ab7b4baa6d915706b.png?imageView&thumbnail=200y140","url":"https://tech.163.com/19/0423/08/EDEE3GGG00097U7T.html"},{"ctime":"2019-04-23 08:22","title":"马斯克：明年将有百万辆特斯拉自动驾驶出租车上","description":"网易IT","picUrl":"http://cms-bucket.ws.126.net/2019/04/23/8d38c0f46f5c47de9a4e34c03a73e59e.png?imageView&thumbnail=200y140","url":"https://tech.163.com/19/0423/08/EDEDH89H00097U7T.html"},{"ctime":"2019-04-22 22:51","title":"Q1或落后华为三分之一 降价的iPhone销量继续下","description":"网易IT","picUrl":"http://cms-bucket.ws.126.net/2019/04/22/0a80cb7e282641ee8338f8cb2b02dcdc.png?imageView&thumbnail=200y140","url":"https://tech.163.com/19/0422/22/EDDCRHHU00097U7T.html"},{"ctime":"2019-04-22 17:14","title":"爆燃特斯拉车主现身:未私接电线/事发时未充电","description":"网易IT","picUrl":"http://cms-bucket.ws.126.net/2019/04/22/8eb8ff9f6eec4c558edad942b66f2008.png?imageView&thumbnail=200y140","url":"https://tech.163.com/19/0422/17/EDCPI4ER00097U7T.html"},{"ctime":"2019-04-22 15:48","title":"蔚来ES8维修时着火 官方回应：已开启调查","description":"网易IT","picUrl":"http://cms-bucket.ws.126.net/2019/04/22/c9073f509be645a68a8d4c9206617cac.png?imageView&thumbnail=200y140","url":"https://tech.163.com/19/0422/15/EDCKLHJ300097U7T.html"},{"ctime":"2019-04-22 15:45","title":"国科微与龙芯合作 发布首款全国产固态硬盘控制","description":"网易IT","picUrl":"http://cms-bucket.ws.126.net/2019/04/22/1ce77851095c425fa16c9a81ccac455f.png?imageView&thumbnail=200y140","url":"https://tech.163.com/19/0422/15/EDCKG6S600097U7T.html"},{"ctime":"2019-04-22 07:36","title":"特斯拉在地库中突然自燃 公司回应：正核实情况","description":"网易IT","picUrl":"http://cms-bucket.ws.126.net/2019/04/22/29d223001daf4ce4951229903ceb3bea.png?imageView&thumbnail=200y140","url":"https://tech.163.com/19/0422/07/EDBOG97M00097U7T.html"},{"ctime":"2019-04-21 16:19","title":"命途多舛 三星折叠屏手机临时取消中国区发布会","description":"网易IT","picUrl":"http://cms-bucket.ws.126.net/2019/04/21/389ca12b5f3f484387b7424ffc2b57c1.png?imageView&thumbnail=200y140","url":"https://tech.163.com/19/0421/16/EDA41MTP00097U7T.html"},{"ctime":"2019-04-21 15:27","title":"三星折叠屏手机遭遇滑铁卢 或严重伤害消费者兴","description":"网易IT","picUrl":"http://cms-bucket.ws.126.net/2019/04/21/a8e5bf6aad404e1c9dbba95953f0731e.png?imageView&thumbnail=200y140","url":"https://tech.163.com/19/0421/15/EDA12DVV00097U7T.html"},{"ctime":"2019-04-21 14:30","title":"索尼高桥洋：不会放弃手机业务 但业务复兴还要","description":"网易IT","picUrl":"http://cms-bucket.ws.126.net/2019/04/21/a4e4479ea6394716b78623680e8481d9.png?imageView&thumbnail=200y140","url":"https://tech.163.com/19/0421/14/ED9TPELP00097U7T.html"},{"ctime":"2019-04-21 14:23","title":"外媒：因屏幕问题三星Galaxy Fold首销或推迟","description":"网易IT","picUrl":"http://cms-bucket.ws.126.net/2019/04/21/db2e6a8e28014acbbf4f632d32af111c.png?imageView&thumbnail=200y140","url":"https://tech.163.com/19/0421/14/ED9TD5T500097U7T.html"},{"ctime":"2019-04-21 13:55","title":"民法典新草案有规定:AI换脸一时爽 侵权违法太酸","description":"网易IT","picUrl":"http://cms-bucket.ws.126.net/2019/04/21/155fb15afe034cd0aad8d2849d9b4e4d.png?imageView&thumbnail=200y140","url":"https://tech.163.com/19/0421/13/ED9ROURK00097U7T.html"},{"ctime":"2019-04-21 12:54","title":"特斯拉Roadster 2惊人加速视频放出：1.9秒破百","description":"网易IT","picUrl":"http://cms-bucket.ws.126.net/2019/04/21/5dc26c2dd5bb4b388e400d25c35335b0.png?imageView&thumbnail=200y140","url":"https://tech.163.com/19/0421/12/ED9OATUH00097U7T.html"},{"ctime":"2019-04-21 11:12","title":"支持996?郭台铭:如果我孩子睡到自然醒 就打断他","description":"网易IT","picUrl":"http://cms-bucket.ws.126.net/2019/04/21/c2de1aa427964b8db7bd10b4edea8f78.png?imageView&thumbnail=200y140","url":"https://tech.163.com/19/0421/11/ED9IFJTO00097U7T.html"},{"ctime":"2019-04-21 10:53","title":"SpaceX载人龙飞船测试出现异常，现场升起棕色烟","description":"网易IT","picUrl":"http://cms-bucket.ws.126.net/2019/04/21/9ee7e48be7ef4a1cac7f7271e1304d4e.png?imageView&thumbnail=200y140","url":"https://tech.163.com/19/0421/10/ED9HBQON00097U7T.html"},{"ctime":"2019-04-21 10:49","title":"疑似苹果2019款iPhone模具曝光，三角形三摄稳了","description":"网易IT","picUrl":"http://cms-bucket.ws.126.net/2019/04/21/c6ab134df8934ec4bfc18e0a3e698264.png?imageView&thumbnail=200y140","url":"https://tech.163.com/19/0421/10/ED9H4TAL00097U7T.html"},{"ctime":"2019-04-21 09:21","title":"苹果再次输掉跟Swatch官司:因为one more thing","description":"网易IT","picUrl":"http://cms-bucket.ws.126.net/2019/04/21/b67c789dab044431be7b9f28ac06316b.png?imageView&thumbnail=200y140","url":"https://tech.163.com/19/0421/09/ED9C4NRS00097U7T.html"}]
     */

    private int code;
    private String msg;
    private List<NewslistBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
        /**
         * ctime : 2019-04-23 13:20
         * title : 微软日本公司宣布今年8月每周休三天 并发放补助
         * description : 网易IT
         * picUrl : http://cms-bucket.ws.126.net/2019/04/23/1ec7cc26d084402196be1d884e293110.png?imageView&thumbnail=200y140
         * url : https://tech.163.com/19/0423/13/EDEUJ4U100097U7T.html
         */

        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
