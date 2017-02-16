package cn.suxiangbao.sopark.entity;



import java.util.List;

/**
 * Created by Administrator on 2017/1/23.
 * 用户信息类
 */

public class UserInfo {
    private Long uid;
    private String username;
    private String password;
    private String nick;
    private String email;
    private String icon;
    private String identifySign;
    private String realName;
    /**
     * 身份的认证类型
     */
    private AuthType authType;
    private String phoneNum;
    /**
     * 身份证照片 包含正反面
     */

    private List<String> idCards;
    /**
     * 驾驶证照片
     */
    private List<String> driverLicenses ;
    /**
     * 邮箱是否通过验证
     */
    private Boolean emailAuth ;
    /**
     * 手机是否通过验证
     */
    private Boolean phoneAuth;



    /**
     * PASS 通过认证
     * UNPASS 未通过认证
     * NO_AUTH 认证不通过
     * AUTH_ING 认证中
     */
    public enum AuthType {

        PASS(1), UNPASS(2), NO_AUTH(3), AUTH_ING(4);

        AuthType(int value) {
            this.value = value;
        }
        int value;

        public int getValue() {
            return value;
        }
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIdentifySign() {
        return identifySign;
    }

    public void setIdentifySign(String identifySign) {
        this.identifySign = identifySign;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public AuthType getAuthType() {
        return authType;
    }

    public void setAuthType(AuthType authType) {
        this.authType = authType;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public List<String> getIdCards() {
        return idCards;
    }

    public void setIdCards(List<String> idCards) {
        this.idCards = idCards;
    }

    public List<String> getDriverLicenses() {
        return driverLicenses;
    }

    public void setDriverLicenses(List<String> driverLicenses) {
        this.driverLicenses = driverLicenses;
    }

    public Boolean getEmailAuth() {
        return emailAuth;
    }

    public void setEmailAuth(Boolean emailAuth) {
        this.emailAuth = emailAuth;
    }

    public Boolean getPhoneAuth() {
        return phoneAuth;
    }

    public void setPhoneAuth(Boolean phoneAuth) {
        this.phoneAuth = phoneAuth;
    }



    @Override
    public String toString() {
        return "UserInfo{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nick='" + nick + '\'' +
                ", email='" + email + '\'' +
                ", icon='" + icon + '\'' +
                ", identifySign='" + identifySign + '\'' +
                ", realName='" + realName + '\'' +
                ", authType=" + authType +
                ", phoneNum='" + phoneNum + '\'' +
                ", idCards=" + idCards +
                ", driverLicenses=" + driverLicenses +
                ", emailAuth=" + emailAuth +
                ", phoneAuth=" + phoneAuth +
                '}';
    }
}
