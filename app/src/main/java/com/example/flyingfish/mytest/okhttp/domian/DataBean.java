package com.example.flyingfish.mytest.okhttp.domian;

import java.util.List;

/**
 * Created by Flyingfish on 2017/7/26.
 */

public class DataBean {

    private List<ItemData> trailers;

    public List<ItemData> getTrailers() {
        return trailers;
    }

    public void setTrailers(List<ItemData> trailers) {
        this.trailers = trailers;
    }

    public static class ItemData {
        /**
         * id : 66750
         * movieName : 《正义联盟》中文预告
         * coverImg : http://img5.mtime.cn/mg/2017/07/23/173925.91198625.jpg
         * movieId : 70233
         * url : http://vfx.mtime.cn/Video/2017/07/23/mp4/170723125607378868.mp4
         * hightUrl : http://vfx.mtime.cn/Video/2017/07/23/mp4/170723125607378868.mp4
         * videoTitle : 正义联盟 繁体中文SDCC预告片
         * videoLength : 251
         * rating : -1
         * type : ["动作","冒险","奇幻","科幻"]
         * summary : 派荒原狼现身 超人将回归？
         */

        private int id;
        private String movieName;
        private String coverImg;
        private int movieId;
        private String url;
        private String hightUrl;
        private String videoTitle;
        private int videoLength;
        private int rating;
        private String summary;
        private List<String> type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public String getCoverImg() {
            return coverImg;
        }

        public void setCoverImg(String coverImg) {
            this.coverImg = coverImg;
        }

        public int getMovieId() {
            return movieId;
        }

        public void setMovieId(int movieId) {
            this.movieId = movieId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHightUrl() {
            return hightUrl;
        }

        public void setHightUrl(String hightUrl) {
            this.hightUrl = hightUrl;
        }

        public String getVideoTitle() {
            return videoTitle;
        }

        public void setVideoTitle(String videoTitle) {
            this.videoTitle = videoTitle;
        }

        public int getVideoLength() {
            return videoLength;
        }

        public void setVideoLength(int videoLength) {
            this.videoLength = videoLength;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public List<String> getType() {
            return type;
        }

        public void setType(List<String> type) {
            this.type = type;
        }
    }
}
