package cn.suxiangbao.sopark.entity;


import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/1/23.
 * 汽车实体类
 */
public class Car {
    /**
     * 车 id
     */
    private Long cid;
    /**
     * 图标
     */
    private String icon;
    /**
     * 品牌  如宝马
     */
    private String brand;
    /**
     * 型号 x6
     */
    private String version;
    /**
     * 车牌号码
     */
    private String licencePlate;
    private Date createDate;
    private Date updateDate;
    private Long uid;
    /**
     * 行驶证 size = 2
     */
    private List<String> imgDriverLicence;
    /**
     *
     */
    private List<String> imgCarPic;
    /**
     * 年审照片
     */
    private String imgYearCheck;
    /**
     * 是否通过审核
     */
    private Boolean isChecked;

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
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

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public List<String> getImgDriverLicence() {
        return imgDriverLicence;
    }

    public void setImgDriverLicence(List<String> imgDriverLicence) {
        this.imgDriverLicence = imgDriverLicence;
    }

    public List<String> getImgCarPic() {
        return imgCarPic;
    }

    public void setImgCarPic(List<String> imgCarPic) {
        this.imgCarPic = imgCarPic;
    }

    public String getImgYearCheck() {
        return imgYearCheck;
    }

    public void setImgYearCheck(String imgYearCheck) {
        this.imgYearCheck = imgYearCheck;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (cid != null ? !cid.equals(car.cid) : car.cid != null) return false;
        if (brand != null ? !brand.equals(car.brand) : car.brand != null) return false;
        if (version != null ? !version.equals(car.version) : car.version != null) return false;
        if (licencePlate != null ? !licencePlate.equals(car.licencePlate) : car.licencePlate != null)
            return false;
        return uid != null ? uid.equals(car.uid) : car.uid == null;

    }

    @Override
    public int hashCode() {
        int result = cid != null ? cid.hashCode() : 0;
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (licencePlate != null ? licencePlate.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "cid=" + cid +
                ", icon='" + icon + '\'' +
                ", brand='" + brand + '\'' +
                ", version='" + version + '\'' +
                ", licencePlate='" + licencePlate + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", uid=" + uid +
                ", imgDriverLicence=" + imgDriverLicence +
                ", imgCarPic=" + imgCarPic +
                ", imgYearCheck='" + imgYearCheck + '\'' +
                ", isChecked=" + isChecked +
                '}';
    }
    public static class Field{
        public static final String FIELD_CID = "cid";
        public static final String FIELD_ICON = "icon";
        public static final String FIELD_BRAND = "brand";
        public static final String FIELD_VERSION = "version";
        public static final String FIELD_LICENCE_PLATE = "licencePlate";
        public static final String FIELD_CREATE_DATE = "createDate";
        public static final String FIELD_UPDATE_DATA = "updateDate";
        public static final String FIELD_OWNER = "OWNER";
        public static final String FIELD_CREDENTIALS = "credentials";
        public static final String FIELD_IS_CHECKED = "isChecked";
    }





}