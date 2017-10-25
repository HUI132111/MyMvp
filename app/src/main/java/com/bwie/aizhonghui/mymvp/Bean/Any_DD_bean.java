package com.bwie.aizhonghui.mymvp.Bean;

import java.util.List;

/**
 * Created by DANGEROUS_HUI on 2017/10/22.
 */

public class Any_DD_bean {

    /**
     * msg : 请求成功
     * code : 0
     * data : [{"createtime":"2017-10-21T16:07:47","orderid":500,"price":11800,"status":0,"uid":97},{"createtime":"2017-10-21T16:12:07","orderid":505,"price":14799,"status":0,"uid":97},{"createtime":"2017-10-21T16:42:20","orderid":576,"price":11800,"status":0,"uid":97},{"createtime":"2017-10-22T14:24:24","orderid":840,"price":11800,"status":0,"uid":97},{"createtime":"2017-10-22T14:24:37","orderid":841,"price":11800,"status":0,"uid":97},{"createtime":"2017-10-22T14:26:16","orderid":842,"price":23600,"status":0,"uid":97},{"createtime":"2017-10-22T14:27:23","orderid":843,"price":23600,"status":0,"uid":97},{"createtime":"2017-10-22T14:40:25","orderid":858,"price":23711.99,"status":0,"uid":97}]
     * page : 1
     */

    private String msg;
    private String code;
    private String page;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * createtime : 2017-10-21T16:07:47
         * orderid : 500
         * price : 11800.0
         * status : 0
         * uid : 97
         */

        private String createtime;
        private int orderid;
        private double price;
        private int status;
        private int uid;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
