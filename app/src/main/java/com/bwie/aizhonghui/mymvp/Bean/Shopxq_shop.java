package com.bwie.aizhonghui.mymvp.Bean;

/**
 * Created by DANGEROUS_HUI on 2017/10/17.
 */

public class Shopxq_shop {

    /**
     * msg :
     * seller : {"description":"我是商家3","icon":"http://120.27.23.105/images/icon.png","name":"商家3","productNums":999,"score":5,"sellerid":3}
     * code : 0
     * data : {"bargainPrice":5599,"createtime":"2017-10-10T17:30:32","detailUrl":"https://item.m.jd.com/product/4824715.html?utm#_source=androidapp&utm#_medium=appshare&utm#_campaign=t#_335139774&utm#_term=QQfriends","images":"https://m.360buyimg.com/n12/jfs/t7768/184/1153704394/148460/f42e1432/599a930fN8a85626b.jpg!q70.jpg","itemtype":0,"pid":59,"price":5599,"pscid":40,"salenum":675,"sellerid":3,"subhead":"游戏本选择4G独显，拒绝掉帧】升级版IPS全高清防眩光显示屏，WASD方向键颜色加持，三大出风口立体散热！","title":"戴尔DELL灵越游匣15PR-6648B GTX1050 15.6英寸游戏笔记本电脑(i5-7300HQ 8G 128GSSD+1T 4G独显 IPS)黑"}
     */

    private String msg;
    private SellerBean seller;
    private String code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public SellerBean getSeller() {
        return seller;
    }

    public void setSeller(SellerBean seller) {
        this.seller = seller;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class SellerBean {
        /**
         * description : 我是商家3
         * icon : http://120.27.23.105/images/icon.png
         * name : 商家3
         * productNums : 999
         * score : 5.0
         * sellerid : 3
         */

        private String description;
        private String icon;
        private String name;
        private int productNums;
        private double score;
        private int sellerid;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getProductNums() {
            return productNums;
        }

        public void setProductNums(int productNums) {
            this.productNums = productNums;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public int getSellerid() {
            return sellerid;
        }

        public void setSellerid(int sellerid) {
            this.sellerid = sellerid;
        }
    }

    public static class DataBean {
        /**
         * bargainPrice : 5599.0
         * createtime : 2017-10-10T17:30:32
         * detailUrl : https://item.m.jd.com/product/4824715.html?utm#_source=androidapp&utm#_medium=appshare&utm#_campaign=t#_335139774&utm#_term=QQfriends
         * images : https://m.360buyimg.com/n12/jfs/t7768/184/1153704394/148460/f42e1432/599a930fN8a85626b.jpg!q70.jpg
         * itemtype : 0
         * pid : 59
         * price : 5599.0
         * pscid : 40
         * salenum : 675
         * sellerid : 3
         * subhead : 游戏本选择4G独显，拒绝掉帧】升级版IPS全高清防眩光显示屏，WASD方向键颜色加持，三大出风口立体散热！
         * title : 戴尔DELL灵越游匣15PR-6648B GTX1050 15.6英寸游戏笔记本电脑(i5-7300HQ 8G 128GSSD+1T 4G独显 IPS)黑
         */

        private double bargainPrice;
        private String createtime;
        private String detailUrl;
        private String images;
        private int itemtype;
        private int pid;
        private double price;
        private int pscid;
        private int salenum;
        private int sellerid;
        private String subhead;
        private String title;

        public double getBargainPrice() {
            return bargainPrice;
        }

        public void setBargainPrice(double bargainPrice) {
            this.bargainPrice = bargainPrice;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getDetailUrl() {
            return detailUrl;
        }

        public void setDetailUrl(String detailUrl) {
            this.detailUrl = detailUrl;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public int getItemtype() {
            return itemtype;
        }

        public void setItemtype(int itemtype) {
            this.itemtype = itemtype;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getPscid() {
            return pscid;
        }

        public void setPscid(int pscid) {
            this.pscid = pscid;
        }

        public int getSalenum() {
            return salenum;
        }

        public void setSalenum(int salenum) {
            this.salenum = salenum;
        }

        public int getSellerid() {
            return sellerid;
        }

        public void setSellerid(int sellerid) {
            this.sellerid = sellerid;
        }

        public String getSubhead() {
            return subhead;
        }

        public void setSubhead(String subhead) {
            this.subhead = subhead;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
