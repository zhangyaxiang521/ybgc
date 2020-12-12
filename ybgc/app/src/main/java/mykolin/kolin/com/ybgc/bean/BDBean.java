package mykolin.kolin.com.ybgc.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/6/4.
 */

public class BDBean {

    /**
     * result : true
     * reList : [{"organId":81,"organPreId":80,"organType":4,"organLevel":4,"projId":1,"enterpriseId":37,"orderId":4,"projName":null,"typeName":null,"enterpriseName":null,"organNo":"TJ01","organName":"TJ01","remark":null},{"organId":82,"organPreId":80,"organType":4,"organLevel":4,"projId":1,"enterpriseId":38,"orderId":5,"projName":null,"typeName":null,"enterpriseName":null,"organNo":"TJ02","organName":"TJ02","remark":null},{"organId":83,"organPreId":88,"organType":4,"organLevel":4,"projId":1,"enterpriseId":39,"orderId":7,"projName":null,"typeName":null,"enterpriseName":null,"organNo":"TJ03","organName":"TJ03","remark":null},{"organId":98,"organPreId":88,"organType":4,"organLevel":4,"projId":1,"enterpriseId":68,"orderId":7,"projName":null,"typeName":null,"enterpriseName":null,"organNo":"LH3","organName":"LH3","remark":null},{"organId":101,"organPreId":88,"organType":4,"organLevel":4,"projId":1,"enterpriseId":65,"orderId":7,"projName":null,"typeName":null,"enterpriseName":null,"organNo":"LM1","organName":"LM1","remark":null},{"organId":87,"organPreId":89,"organType":4,"organLevel":4,"projId":1,"enterpriseId":42,"orderId":9,"projName":null,"typeName":null,"enterpriseName":null,"organNo":"TJ04","organName":"TJ04","remark":null},{"organId":102,"organPreId":89,"organType":4,"organLevel":4,"projId":1,"enterpriseId":66,"orderId":10,"projName":null,"typeName":null,"enterpriseName":null,"organNo":"LM2","organName":"LM2","remark":null},{"organId":92,"organPreId":89,"organType":4,"organLevel":4,"projId":1,"enterpriseId":43,"orderId":10,"projName":null,"typeName":null,"enterpriseName":null,"organNo":"TJ05","organName":"TJ05","remark":null},{"organId":99,"organPreId":89,"organType":4,"organLevel":4,"projId":1,"enterpriseId":63,"orderId":11,"projName":null,"typeName":null,"enterpriseName":null,"organNo":"LH3","organName":"LH3-2","remark":null},{"organId":93,"organPreId":90,"organType":4,"organLevel":4,"projId":1,"enterpriseId":44,"orderId":12,"projName":null,"typeName":null,"enterpriseName":null,"organNo":"TJ06","organName":"TJ06","remark":null},{"organId":103,"organPreId":90,"organType":4,"organLevel":4,"projId":1,"enterpriseId":67,"orderId":12,"projName":null,"typeName":null,"enterpriseName":null,"organNo":"LM3","organName":"LM3","remark":null},{"organId":100,"organPreId":90,"organType":4,"organLevel":4,"projId":1,"enterpriseId":64,"orderId":13,"projName":null,"typeName":null,"enterpriseName":null,"organNo":"LH3","organName":"LH3-3","remark":null},{"organId":94,"organPreId":91,"organType":4,"organLevel":4,"projId":1,"enterpriseId":45,"orderId":14,"projName":null,"typeName":null,"enterpriseName":null,"organNo":"TJ07","organName":"TJ07","remark":null},{"organId":95,"organPreId":91,"organType":4,"organLevel":4,"projId":1,"enterpriseId":46,"orderId":15,"projName":null,"typeName":null,"enterpriseName":null,"organNo":"TJ08","organName":"TJ08","remark":null}]
     */

    private boolean result;
    private List<ReListBean> reList;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public List<ReListBean> getReList() {
        return reList;
    }

    public void setReList(List<ReListBean> reList) {
        this.reList = reList;
    }

    public static class ReListBean {
        /**
         * organId : 81
         * organPreId : 80
         * organType : 4
         * organLevel : 4
         * projId : 1
         * enterpriseId : 37
         * orderId : 4
         * projName : null
         * typeName : null
         * enterpriseName : null
         * organNo : TJ01
         * organName : TJ01
         * remark : null
         */

        private int organId;
        private int organPreId;
        private int organType;
        private int organLevel;
        private int projId;
        private int enterpriseId;
        private int orderId;
        private Object projName;
        private Object typeName;
        private Object enterpriseName;
        private String organNo;
        private String organName;
        private Object remark;

        public int getOrganId() {
            return organId;
        }

        public void setOrganId(int organId) {
            this.organId = organId;
        }

        public int getOrganPreId() {
            return organPreId;
        }

        public void setOrganPreId(int organPreId) {
            this.organPreId = organPreId;
        }

        public int getOrganType() {
            return organType;
        }

        public void setOrganType(int organType) {
            this.organType = organType;
        }

        public int getOrganLevel() {
            return organLevel;
        }

        public void setOrganLevel(int organLevel) {
            this.organLevel = organLevel;
        }

        public int getProjId() {
            return projId;
        }

        public void setProjId(int projId) {
            this.projId = projId;
        }

        public int getEnterpriseId() {
            return enterpriseId;
        }

        public void setEnterpriseId(int enterpriseId) {
            this.enterpriseId = enterpriseId;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public Object getProjName() {
            return projName;
        }

        public void setProjName(Object projName) {
            this.projName = projName;
        }

        public Object getTypeName() {
            return typeName;
        }

        public void setTypeName(Object typeName) {
            this.typeName = typeName;
        }

        public Object getEnterpriseName() {
            return enterpriseName;
        }

        public void setEnterpriseName(Object enterpriseName) {
            this.enterpriseName = enterpriseName;
        }

        public String getOrganNo() {
            return organNo;
        }

        public void setOrganNo(String organNo) {
            this.organNo = organNo;
        }

        public String getOrganName() {
            return organName;
        }

        public void setOrganName(String organName) {
            this.organName = organName;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }
    }
}
