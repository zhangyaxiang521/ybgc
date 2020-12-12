package mykolin.kolin.com.ybgc.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/6/2.
 */

public class YBGCBean {
    /**
     * result : true
     * reList : [{"TZKZ_CODE":"LJ01-TSFGC","TZKZ_NAME":"路基土石方工程","TZKZ_ID":51082,"ORGAN_ID":81},{"TZKZ_CODE":"LJ02-TSFGC-01","TZKZ_NAME":"K7+871～K8+930路基土石方工程","TZKZ_ID":51083,"ORGAN_ID":81},{"TZKZ_CODE":"LJ03-BREBEAT","TZKZ_NAME":"BREBEAT","TZKZ_ID":51086,"ORGAN_ID":81}]
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
         * TZKZ_CODE : LJ01-TSFGC
         * TZKZ_NAME : 路基土石方工程
         * TZKZ_ID : 51082
         * ORGAN_ID : 81
         */

        private String TZKZ_CODE;
        private String TZKZ_NAME;
        private int TZKZ_ID;
        private int ORGAN_ID;

        public String getTZKZ_CODE() {
            return TZKZ_CODE;
        }

        public void setTZKZ_CODE(String TZKZ_CODE) {
            this.TZKZ_CODE = TZKZ_CODE;
        }

        public String getTZKZ_NAME() {
            return TZKZ_NAME;
        }

        public void setTZKZ_NAME(String TZKZ_NAME) {
            this.TZKZ_NAME = TZKZ_NAME;
        }

        public int getTZKZ_ID() {
            return TZKZ_ID;
        }

        public void setTZKZ_ID(int TZKZ_ID) {
            this.TZKZ_ID = TZKZ_ID;
        }

        public int getORGAN_ID() {
            return ORGAN_ID;
        }

        public void setORGAN_ID(int ORGAN_ID) {
            this.ORGAN_ID = ORGAN_ID;
        }
    }
}
