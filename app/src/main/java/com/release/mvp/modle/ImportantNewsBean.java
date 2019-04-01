package com.release.mvp.modle;

import java.util.List;

/**
 * @author Mr.release
 * @create 2019/3/26
 * @Describe
 */
public class ImportantNewsBean {

    /**
     * ret : 1
     * data : [{"id":"8289","title":"油焖大虾","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/9/8289.jpg","collect_num":"1667","food_str":"大虾 葱 生姜 植物油 料酒","num":1667},{"id":"2127","title":"四川回锅肉","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/3/2127.jpg","collect_num":"1590","food_str":"猪肉 青蒜 青椒 红椒 姜片","num":1590},{"id":"30630","title":"超简单芒果布丁","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/31/30630.jpg","collect_num":"1538","food_str":"QQ糖 牛奶 芒果","num":1538},{"id":"9073","title":"家常红烧鱼","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/10/9073.jpg","collect_num":"1424","food_str":"鲜鱼 姜 葱 蒜 花椒","num":1424},{"id":"10097","title":"家常煎豆腐","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/11/10097.jpg","collect_num":"1417","food_str":"豆腐 新鲜红椒 青椒 葱花 油","num":1417},{"id":"10509","title":"水煮肉片","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/11/10509.jpg","collect_num":"1339","food_str":"瘦猪肉 生菜 豆瓣酱 干辣椒 花椒","num":1339},{"id":"46968","title":"红糖苹果银耳汤","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/47/46968.jpg","collect_num":"1252","food_str":"银耳 苹果 红糖","num":1252},{"id":"10191","title":"麻婆豆腐","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/11/10191.jpg","collect_num":"1220","food_str":"豆腐 肉末 生抽 白糖 芝麻油","num":1220},{"id":"2372","title":"皮蛋瘦肉粥","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/3/2372.jpg","collect_num":"1151","food_str":"大米 皮蛋 猪肉 油条 香葱","num":1151},{"id":"2166","title":"蚂蚁上树","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/3/2166.jpg","collect_num":"1143","food_str":"红薯粉 肉 姜 蒜 花椒","num":1143},{"id":"2262","title":"糖醋肉","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/3/2262.jpg","collect_num":"1077","food_str":"猪肉 红椒 黄椒 洋葱 蛋清","num":1077},{"id":"9971","title":"鱼香豆腐","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/10/9971.jpg","collect_num":"1009","food_str":"豆腐 木耳 胡萝卜 香葱 番茄酱","num":1009},{"id":"10172","title":"干煸四季豆","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/11/10172.jpg","collect_num":"991","food_str":"四季豆 干辣椒 蒜头 酱油 糖","num":991},{"id":"2685","title":"胡萝卜肉末蒸蛋","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/3/2685.jpg","collect_num":"926","food_str":"胡萝卜 肉 蛋 生抽 盐","num":926},{"id":"9972","title":"虎皮青椒","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/10/9972.jpg","collect_num":"891","food_str":"青辣椒 大蒜 香醋 白糖 生抽","num":891},{"id":"10437","title":"叉烧排骨","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/11/10437.jpg","collect_num":"801","food_str":"排骨 李锦记叉烧酱 植物油 清水 油菜","num":801},{"id":"2892","title":"\u201c五行\u201d彩蔬汤","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/3/2892.jpg","collect_num":"760","food_str":"黑木耳 玉米 牛蒡 胡萝卜 西兰花","num":760},{"id":"33783","title":"美人豆浆","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/34/33783.jpg","collect_num":"757","food_str":"黄豆 红豆 绿豆 黑豆 黑米","num":757},{"id":"2348","title":"麻辣肉丝面","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/3/2348.jpg","collect_num":"756","food_str":"面条 肉丝 淀粉 酱油 辣椒","num":756},{"id":"10044","title":"土豆炖翅根","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/11/10044.jpg","collect_num":"754","food_str":"土豆 翅根 葱 姜 料酒","num":754}]
     */

    private int ret;
    private List<DataBean> data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 8289
         * title : 油焖大虾
         * pic : http://www.qubaobei.com/ios/cf/uploadfile/132/9/8289.jpg
         * collect_num : 1667
         * food_str : 大虾 葱 生姜 植物油 料酒
         * num : 1667
         */

        private String id;
        private String title;
        private String pic;
        private String collect_num;
        private String food_str;
        private int num;

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

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getCollect_num() {
            return collect_num;
        }

        public void setCollect_num(String collect_num) {
            this.collect_num = collect_num;
        }

        public String getFood_str() {
            return food_str;
        }

        public void setFood_str(String food_str) {
            this.food_str = food_str;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
}
