package mykolin.kolin.com.ybgc.bean;

/**
 * Created by Administrator on 2020/6/17.
 */

public class UpBean {
    /**
     * message : 账号不存在
     * result : false
     */

    private String message;
    private boolean result;

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
}
