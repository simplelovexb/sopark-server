package cn.suxiangbao.sopark.entity;


import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/1/2.
 * 车位实体类
 */
public class CarPort {
    private Long pid ;
    private String name;
    /**
     * 车位所有人 的id
     */
    private Long owner;
    /**
     * 对车位的简单描述
     */
    private String comment;
    /**
     * 车位所在的全球经纬度地址
     */
    private Double[] coordinate;

    private Integer status ;

    private Integer verify;

    private Date createDate;
    private Date updateDate;

    private String pic1;
    private String pic2;

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double[] getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Double[] coordinate) {
        this.coordinate = coordinate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getVerify() {
        return verify;
    }

    public void setVerify(Integer verify) {
        this.verify = verify;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1;
    }

    public String getPic2() {
        return pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2;
    }

    @Override
    public String toString() {
        return "CarPort{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                ", comment='" + comment + '\'' +
                ", coordinate=" + Arrays.toString(coordinate) +
                ", status=" + status +
                ", verify=" + verify +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", pic1='" + pic1 + '\'' +
                ", pic2='" + pic2 + '\'' +
                '}';
    }

    public static class Field{
        public static final String FIELD_COORDINATE = "coordinate";
        public static final String FIELD_ID = "pid";
        public static final String FIELD_STATUS = "status";
        public static final String FIELD_OWNER = "owner";
        public static final String FIELD_NAME = "name";
        public static final String FIELD_COMMENT = "comment";

    }

    public enum StatusEnum {
        CouldUse(0, "可以使用"),
        HadUsed(1, "已经被使用"),
        CouldNotUse(2, "不能使用"),
        ;

        int type;
        String name;

        StatusEnum(int type, String name) {
            this.type = type;
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public String getName(){
            return this.name;
        }


        public static StatusEnum findByValue(int type) {
            for (StatusEnum recommendType : values()) {
                if (recommendType.type == type) {
                    return recommendType;
                }
            }

            return null;
        }
    }


    public enum VerifyEnum {
        UnVerify(0, "未认证"),
        Verifying(1,"认证中"),
        PASS(2, "通过认证"),
        UnPass(3, "未通过认证"),
        ;

        int type;
        String name;

        VerifyEnum(int type, String name) {
            this.type = type;
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public String getName(){
            return this.name;
        }


        public static VerifyEnum findByValue(int type) {
            for (VerifyEnum verifyEnum : values()) {
                if (verifyEnum.type == type) {
                    return verifyEnum;
                }
            }

            return null;
        }
    }

}
