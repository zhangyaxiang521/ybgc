package mykolin.kolin.com.ybgc.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/6/2.
 */

public class YBGCXQBean {

    /**
     * result : true
     * reList : [{"tzkzDetailId":5,"tzkzId":51082,"organId":81,"tzkzDetailGcmc":"坡面防护","tzkzDetailGcbh":"PMFH","tzkzDetailQszh":"ES1.1565","tzkzDetailZzzh":"ES9.4894","zjModeCount":null,"cjModeCount":null},{"tzkzDetailId":4,"tzkzId":51082,"organId":81,"tzkzDetailGcmc":"路基混凝工程","tzkzDetailGcbh":"HNY","tzkzDetailQszh":"EWE.13215","tzkzDetailZzzh":"EWE.76574","zjModeCount":null,"cjModeCount":null},{"tzkzDetailId":3,"tzkzId":51082,"organId":81,"tzkzDetailGcmc":"土石方工程","tzkzDetailGcbh":"TDS","tzkzDetailQszh":"SDF.1561","tzkzDetailZzzh":"SDF.9844","zjModeCount":null,"cjModeCount":null}]
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
         * tzkzDetailId : 5
         * tzkzId : 51082
         * organId : 81
         * tzkzDetailGcmc : 坡面防护
         * tzkzDetailGcbh : PMFH
         * tzkzDetailQszh : ES1.1565
         * tzkzDetailZzzh : ES9.4894
         * zjModeCount : null
         * cjModeCount : null
         */

        private int tzkzDetailId;
        private int tzkzId;
        private int organId;
        private String tzkzDetailGcmc;
        private String tzkzDetailGcbh;
        private String tzkzDetailQszh;
        private String tzkzDetailZzzh;
        private Object zjModeCount;
        private Object cjModeCount;

        public int getTzkzDetailId() {
            return tzkzDetailId;
        }

        public void setTzkzDetailId(int tzkzDetailId) {
            this.tzkzDetailId = tzkzDetailId;
        }

        public int getTzkzId() {
            return tzkzId;
        }

        public void setTzkzId(int tzkzId) {
            this.tzkzId = tzkzId;
        }

        public int getOrganId() {
            return organId;
        }

        public void setOrganId(int organId) {
            this.organId = organId;
        }

        public String getTzkzDetailGcmc() {
            return tzkzDetailGcmc;
        }

        public void setTzkzDetailGcmc(String tzkzDetailGcmc) {
            this.tzkzDetailGcmc = tzkzDetailGcmc;
        }

        public String getTzkzDetailGcbh() {
            return tzkzDetailGcbh;
        }

        public void setTzkzDetailGcbh(String tzkzDetailGcbh) {
            this.tzkzDetailGcbh = tzkzDetailGcbh;
        }

        public String getTzkzDetailQszh() {
            return tzkzDetailQszh;
        }

        public void setTzkzDetailQszh(String tzkzDetailQszh) {
            this.tzkzDetailQszh = tzkzDetailQszh;
        }

        public String getTzkzDetailZzzh() {
            return tzkzDetailZzzh;
        }

        public void setTzkzDetailZzzh(String tzkzDetailZzzh) {
            this.tzkzDetailZzzh = tzkzDetailZzzh;
        }

        public Object getZjModeCount() {
            return zjModeCount;
        }

        public void setZjModeCount(Object zjModeCount) {
            this.zjModeCount = zjModeCount;
        }

        public Object getCjModeCount() {
            return cjModeCount;
        }

        public void setCjModeCount(Object cjModeCount) {
            this.cjModeCount = cjModeCount;
        }
    }
}
