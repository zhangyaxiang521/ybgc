package mykolin.kolin.com.ybgc.bean;


import java.util.List;

/**
 * Created by Administrator on 2020/3/16.
 */

public class ZHGLBean {

    /**
     * message : 登录成功
     * result : true
     * dbsy : [{"dbsj":0,"title":"work"}]
     * staff : {"staffId":51,"organId":78,"roleId":45,"staffName":"运维工程师","staffAcct":"yw","staffPwd":"2020","staffDuty":"","staffTel":"","roleName":null,"organName":null,"isAdmin":0,"lastLoginTime":null,"staffIp":null,"staffBrowser":null}
     * mobileSessionId : 02b0dbf2-0c94-4815-8422-22909bb377c4
     * gjxx : [{"gjsj":"20","title":"bhz"},{"gjsj":"26","title":"sys"},{"gjsj":"81","title":"zljk"},{"gjsj":"1","title":"yjjk"}]
     * rules : {"aqjc":true,"zljc":true}
     */

    private String message;
    private boolean result;
    private StaffBean staff;
    private String mobileSessionId;
    private RulesBean rules;
    private List<DbsyBean> dbsy;
    private List<GjxxBean> gjxx;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public StaffBean getStaff() {
        return staff;
    }

    public void setStaff(StaffBean staff) {
        this.staff = staff;
    }

    public String getMobileSessionId() {
        return mobileSessionId;
    }

    public void setMobileSessionId(String mobileSessionId) {
        this.mobileSessionId = mobileSessionId;
    }

    public RulesBean getRules() {
        return rules;
    }

    public void setRules(RulesBean rules) {
        this.rules = rules;
    }

    public List<DbsyBean> getDbsy() {
        return dbsy;
    }

    public void setDbsy(List<DbsyBean> dbsy) {
        this.dbsy = dbsy;
    }

    public List<GjxxBean> getGjxx() {
        return gjxx;
    }

    public void setGjxx(List<GjxxBean> gjxx) {
        this.gjxx = gjxx;
    }

    public static class StaffBean {
        /**
         * staffId : 51
         * organId : 78
         * roleId : 45
         * staffName : 运维工程师
         * staffAcct : yw
         * staffPwd : 2020
         * staffDuty :
         * staffTel :
         * roleName : null
         * organName : null
         * isAdmin : 0
         * lastLoginTime : null
         * staffIp : null
         * staffBrowser : null
         */

        private int staffId;
        private int organId;
        private int roleId;
        private String staffName;
        private String staffAcct;
        private String staffPwd;
        private String staffDuty;
        private String staffTel;
        private Object roleName;
        private Object organName;
        private int isAdmin;
        private Object lastLoginTime;
        private Object staffIp;
        private Object staffBrowser;

        public int getStaffId() {
            return staffId;
        }

        public void setStaffId(int staffId) {
            this.staffId = staffId;
        }

        public int getOrganId() {
            return organId;
        }

        public void setOrganId(int organId) {
            this.organId = organId;
        }

        public int getRoleId() {
            return roleId;
        }

        public void setRoleId(int roleId) {
            this.roleId = roleId;
        }

        public String getStaffName() {
            return staffName;
        }

        public void setStaffName(String staffName) {
            this.staffName = staffName;
        }

        public String getStaffAcct() {
            return staffAcct;
        }

        public void setStaffAcct(String staffAcct) {
            this.staffAcct = staffAcct;
        }

        public String getStaffPwd() {
            return staffPwd;
        }

        public void setStaffPwd(String staffPwd) {
            this.staffPwd = staffPwd;
        }

        public String getStaffDuty() {
            return staffDuty;
        }

        public void setStaffDuty(String staffDuty) {
            this.staffDuty = staffDuty;
        }

        public String getStaffTel() {
            return staffTel;
        }

        public void setStaffTel(String staffTel) {
            this.staffTel = staffTel;
        }

        public Object getRoleName() {
            return roleName;
        }

        public void setRoleName(Object roleName) {
            this.roleName = roleName;
        }

        public Object getOrganName() {
            return organName;
        }

        public void setOrganName(Object organName) {
            this.organName = organName;
        }

        public int getIsAdmin() {
            return isAdmin;
        }

        public void setIsAdmin(int isAdmin) {
            this.isAdmin = isAdmin;
        }

        public Object getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(Object lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public Object getStaffIp() {
            return staffIp;
        }

        public void setStaffIp(Object staffIp) {
            this.staffIp = staffIp;
        }

        public Object getStaffBrowser() {
            return staffBrowser;
        }

        public void setStaffBrowser(Object staffBrowser) {
            this.staffBrowser = staffBrowser;
        }
    }

    public static class RulesBean {
        /**
         * aqjc : true
         * zljc : true
         */

        private boolean aqjc;
        private boolean zljc;

        public boolean isAqjc() {
            return aqjc;
        }

        public void setAqjc(boolean aqjc) {
            this.aqjc = aqjc;
        }

        public boolean isZljc() {
            return zljc;
        }

        public void setZljc(boolean zljc) {
            this.zljc = zljc;
        }
    }

    public static class DbsyBean {
        /**
         * dbsj : 0
         * title : work
         */

        private int dbsj;
        private String title;

        public int getDbsj() {
            return dbsj;
        }

        public void setDbsj(int dbsj) {
            this.dbsj = dbsj;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class GjxxBean {
        /**
         * gjsj : 20
         * title : bhz
         */

        private String gjsj;
        private String title;

        public String getGjsj() {
            return gjsj;
        }

        public void setGjsj(String gjsj) {
            this.gjsj = gjsj;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
