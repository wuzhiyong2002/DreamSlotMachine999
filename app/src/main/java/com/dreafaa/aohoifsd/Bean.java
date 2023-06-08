package com.dreafaa.aohoifsd;

public class Bean {

    /**
     * code : 0
     * msg : success
     * data : {"link":"https://www.juliang123.top/template/visit/d7845723-5909-8678-e994-bc5aa1878223"}
     */

    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * link : https://www.juliang123.top/template/visit/d7845723-5909-8678-e994-bc5aa1878223
         */

        private String link;

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }
}
