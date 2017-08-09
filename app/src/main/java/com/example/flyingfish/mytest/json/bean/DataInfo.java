package com.example.flyingfish.mytest.json.bean;

import java.util.List;

/**
 * Created by Flyingfish on 2017/8/9.
 */

public class DataInfo {

    /**
     * data : {"count":5,"items":[{"id":45,"title":"坚果"},{"id":132,"title":"炒货"},{"id":166,"title":"蜜饯"},{"id":185,"title":"果脯"},{"id":196,"title":"礼盒"}]}
     * rs_code : 1000
     * rs_msg : success
     */

    private DataBean data;
    private int rs_code;
    private String rs_msg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getRs_code() {
        return rs_code;
    }

    public void setRs_code(int rs_code) {
        this.rs_code = rs_code;
    }

    public String getRs_msg() {
        return rs_msg;
    }

    public void setRs_msg(String rs_msg) {
        this.rs_msg = rs_msg;
    }

    public static class DataBean {
        /**
         * count : 5
         * items : [{"id":45,"title":"坚果"},{"id":132,"title":"炒货"},{"id":166,"title":"蜜饯"},{"id":185,"title":"果脯"},{"id":196,"title":"礼盒"}]
         */

        private int count;
        private List<ItemsBean> items;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "count=" + count +
                    ", items=" + items +
                    '}';
        }

        public static class ItemsBean {
            /**
             * id : 45
             * title : 坚果
             */

            private int id;
            private String title;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            @Override
            public String toString() {
                return "ItemsBean{" +
                        "id=" + id +
                        ", title='" + title + '\'' +
                        '}';
            }
        }
    }

    @Override
    public String toString() {
        return "DataInfo{" +
                "data=" + data +
                ", rs_code=" + rs_code +
                ", rs_msg='" + rs_msg + '\'' +
                '}';
    }
}
