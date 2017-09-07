package zhao.siqi.mvptestdemo.bean;

import java.util.List;

import zhao.siqi.mvptestdemo.network.MResponse;

/**
 * 项目进展---单个
 * Created by Bill on 2017/8/3.
 */
public class ProjectProgressList implements MResponse {

    /**
     * code : 1
     * message :
     * data : [{"id":1,"week":"第31周","day_time":"2017/08","screening_num":"1","random_num":"5","sae_num":"6","core_content":"西藏阿里地区人民医院"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * week : 第31周
         * day_time : 2017/08
         * screening_num : 1
         * random_num : 5
         * sae_num : 6
         * core_content : 西藏阿里地区人民医院
         */

        private int id;
        private String week;
        private String day_time;
        private String screening_num;
        private String random_num;
        private String sae_num;
        private String core_content;
        private String core_crc;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getDay_time() {
            return day_time;
        }

        public void setDay_time(String day_time) {
            this.day_time = day_time;
        }

        public String getScreening_num() {
            return screening_num;
        }

        public void setScreening_num(String screening_num) {
            this.screening_num = screening_num;
        }

        public String getRandom_num() {
            return random_num;
        }

        public void setRandom_num(String random_num) {
            this.random_num = random_num;
        }

        public String getSae_num() {
            return sae_num;
        }

        public void setSae_num(String sae_num) {
            this.sae_num = sae_num;
        }

        public String getCore_content() {
            return core_content;
        }

        public void setCore_content(String core_content) {
            this.core_content = core_content;
        }

        public String getCore_crc() {
            return core_crc;
        }

        public void setCore_crc(String core_crc) {
            this.core_crc = core_crc;
        }
    }
}
