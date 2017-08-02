package com.demo.jiyun.pandatv.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by iu on 2017/7/28.
 */

public class PandaeyeListBean implements Serializable {


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Serializable {
        /**
         * daytime : 2017-06-14
         * id : TITE1497403381691376
         * image : http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2017/6/14/1497403375650_254.jpg
         * order : 1
         * pid : 942717445a0446eb90513607b0a2122d
         * title : 英国一年一度抬床赛 比赛过程欢乐多
         * type : 2
         * url : http://panview.ipanda.com/2017/06/14/VIDE9KtPTNDzejj693BpDdWJ170614.shtml
         * vid :
         * videoLength : 01:01
         */

        private String daytime;
        private String id;
        private String image;
        private String order;
        private String pid;
        private String title;
        private String type;
        private String url;
        private String vid;
        private String videoLength;

        public String getDaytime() {
            return daytime;
        }

        public void setDaytime(String daytime) {
            this.daytime = daytime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public String getVid() {
            return vid;
        }

        public void setVid(String vid) {
            this.vid = vid;
        }

        public String getVideoLength() {
            return videoLength;
        }

        public void setVideoLength(String videoLength) {
            this.videoLength = videoLength;
        }
    }
}
