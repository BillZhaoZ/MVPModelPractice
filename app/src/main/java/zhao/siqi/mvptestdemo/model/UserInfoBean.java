package zhao.siqi.mvptestdemo.model;


import zhao.siqi.mvptestdemo.network.MResponse;

/**
 * Created by zhangzhi on 2017/3/22.
 */
public class UserInfoBean implements MResponse {

    /**
     * code : 1
     * message :
     * data : {"uuid":476,"oldid":"RU56","userno":"CN170585","username":"13261162746","pinyin":"zhangzhi","password":"ef70ef9094481a7955b06160e6c4573a","realname":"张志","nickname":null,"email":"zhi.zhang@medbanks.cn","phone":"13261162746","status":1,"identity":0,"subsys_id":0,"dept_id":"BM000022MO2H","dept_name":"思派-研发部","post_id":"GW000047D8FA","post_name":"工程师-安卓","created_at":"2017-03-24 10:52:04","updated_at":"2017-06-29 10:34:36","isadmin":null,"last_login_time":"2017-06-29 15:55:03","remember_token":null,"errno":0,"work_area":"","headimg":"http://test-avatarlibrary.oss-cn-beijing.aliyuncs.com/size2017-06-28-16-00-07-595362070b9ad.jpg","token":"5444bba96c63056563385089bd5f91f9","im_identifier":"sipai_im_13261162746","im_sig":"eJxFzttOg0AUBdB-4dnoXBimmPig0FpsaVOuiTGZjDClx7ZAYFpbjP8uEoyve*3sc76MaBneyiyrTqUW*lor497AlJnYuBkIclVq2IJqemihliDgKDAlFsYW4aY19mRdQy6kFrTJ*yYa4zbfi4F*Z02EEKWMkxHVpYZGCbnVwzpmjJG*MupZNS1UZQ8EYYYJRegfNRyHT017wgmxEf27B0Uf*9PY8TZuV7snn784XSoT8zldffgRmSVq104OHa3e7vji6XHuJjyK7Y1XBF6wrrKVTsJ1-Bn6Z75PsRNe48U0fN0tC*sA3A7ms-drVTwY3z8Zilxc"}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uuid : 476
         * oldid : RU56
         * userno : CN170585
         * username : 13261162746
         * pinyin : zhangzhi
         * password : ef70ef9094481a7955b06160e6c4573a
         * realname : 张志
         * nickname : null
         * email : zhi.zhang@medbanks.cn
         * phone : 13261162746
         * status : 1
         * identity : 0
         * subsys_id : 0
         * dept_id : BM000022MO2H
         * dept_name : 思派-研发部
         * post_id : GW000047D8FA
         * post_name : 工程师-安卓
         * created_at : 2017-03-24 10:52:04
         * updated_at : 2017-06-29 10:34:36
         * isadmin : null
         * last_login_time : 2017-06-29 15:55:03
         * remember_token : null
         * errno : 0
         * work_area :
         * headimg : http://test-avatarlibrary.oss-cn-beijing.aliyuncs.com/size2017-06-28-16-00-07-595362070b9ad.jpg
         * token : 5444bba96c63056563385089bd5f91f9
         * im_identifier : sipai_im_13261162746
         * im_sig : eJxFzttOg0AUBdB-4dnoXBimmPig0FpsaVOuiTGZjDClx7ZAYFpbjP8uEoyve*3sc76MaBneyiyrTqUW*lor497AlJnYuBkIclVq2IJqemihliDgKDAlFsYW4aY19mRdQy6kFrTJ*yYa4zbfi4F*Z02EEKWMkxHVpYZGCbnVwzpmjJG*MupZNS1UZQ8EYYYJRegfNRyHT017wgmxEf27B0Uf*9PY8TZuV7snn784XSoT8zldffgRmSVq104OHa3e7vji6XHuJjyK7Y1XBF6wrrKVTsJ1-Bn6Z75PsRNe48U0fN0tC*sA3A7ms-drVTwY3z8Zilxc
         */
        private int uuid;
        private String oldid;
        private String userno;
        private String username;
        private String pinyin;
        private String password;
        private String realname;
        private Object nickname;
        private String email;
        private String phone;
        private int status;
        private int identity;
        private int subsys_id;
        private String dept_id;
        private String dept_name;
        private String post_id;
        private String post_name;
        private String created_at;
        private String updated_at;
        private Object isadmin;
        private String last_login_time;
        private Object remember_token;
        private int errno;
        private String work_area;
        private String headimg;
        private String token;
        private String im_identifier;
        private String im_sig;

        public int getUuid() {
            return uuid;
        }

        public void setUuid(int uuid) {
            this.uuid = uuid;
        }

        public String getOldid() {
            return oldid;
        }

        public void setOldid(String oldid) {
            this.oldid = oldid;
        }

        public String getUserno() {
            return userno;
        }

        public void setUserno(String userno) {
            this.userno = userno;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPinyin() {
            return pinyin;
        }

        public void setPinyin(String pinyin) {
            this.pinyin = pinyin;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public Object getNickname() {
            return nickname;
        }

        public void setNickname(Object nickname) {
            this.nickname = nickname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getIdentity() {
            return identity;
        }

        public void setIdentity(int identity) {
            this.identity = identity;
        }

        public int getSubsys_id() {
            return subsys_id;
        }

        public void setSubsys_id(int subsys_id) {
            this.subsys_id = subsys_id;
        }

        public String getDept_id() {
            return dept_id;
        }

        public void setDept_id(String dept_id) {
            this.dept_id = dept_id;
        }

        public String getDept_name() {
            return dept_name;
        }

        public void setDept_name(String dept_name) {
            this.dept_name = dept_name;
        }

        public String getPost_id() {
            return post_id;
        }

        public void setPost_id(String post_id) {
            this.post_id = post_id;
        }

        public String getPost_name() {
            return post_name;
        }

        public void setPost_name(String post_name) {
            this.post_name = post_name;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public Object getIsadmin() {
            return isadmin;
        }

        public void setIsadmin(Object isadmin) {
            this.isadmin = isadmin;
        }

        public String getLast_login_time() {
            return last_login_time;
        }

        public void setLast_login_time(String last_login_time) {
            this.last_login_time = last_login_time;
        }

        public Object getRemember_token() {
            return remember_token;
        }

        public void setRemember_token(Object remember_token) {
            this.remember_token = remember_token;
        }

        public int getErrno() {
            return errno;
        }

        public void setErrno(int errno) {
            this.errno = errno;
        }

        public String getWork_area() {
            return work_area;
        }

        public void setWork_area(String work_area) {
            this.work_area = work_area;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getIm_identifier() {
            return im_identifier;
        }

        public void setIm_identifier(String im_identifier) {
            this.im_identifier = im_identifier;
        }

        public String getIm_sig() {
            return im_sig;
        }

        public void setIm_sig(String im_sig) {
            this.im_sig = im_sig;
        }
    }
}
