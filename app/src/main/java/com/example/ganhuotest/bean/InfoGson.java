package com.example.ganhuotest.bean;

import java.util.List;

/**
 * Created by sl on 2018/2/11.
 */

public class InfoGson {

    private boolean error;
    private List<Info> results;

    public InfoGson(boolean error, List<Info> results) {
        this.error = error;
        this.results = results;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Info> getResults() {
        return results;
    }

    public void setResults(List<Info> results) {
        this.results = results;
    }

    public static class Info{
        private String _id;
        private String createdAt;
        private String desc;
        private String[] images;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private String used;
        private String who;

        public Info(String _id, String createdAt, String desc, String[] images, String publishedAt, String source, String type, String url, String used, String who) {
            this._id = _id;
            this.createdAt = createdAt;
            this.desc = desc;
            this.images = images;
            this.publishedAt = publishedAt;
            this.source = source;
            this.type = type;
            this.url = url;
            this.used = used;
            this.who = who;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String[] getImages() {
            return images;
        }

        public void setImages(String[] images) {
            this.images = images;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsed() {
            return used;
        }

        public void setUsed(String used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
