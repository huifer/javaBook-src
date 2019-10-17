package com.huifer.xz.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Date: 2019-10-15
 */
@Data
@NoArgsConstructor
public class Linex implements Serializable {

    /**
     * keywords : 行者骑行软件
     * creator : gpx.py -- https://github.com/tkrajina/gpxpy
     * version : 1.0
     * name : 2019-10-13 上午 运动
     * time : 2019-10-13T07:14:21Z
     * schemaLocation : http://www.topografix.com/GPX/1/0       http://www.topografix.com/GPX/1/0/gpx.xsd       http://www.topografix.com/GPX/1/0
     * trk : {"trkseg":{"trkpt":[{"lat":"39.924921","lon":"119.574201","time":"2019-10-13T07:14:21Z","ele":"-30.2"},{"lat":"39.924921","lon":"119.574201","time":"2019-10-13T07:14:21Z","ele":"-30.2"}]},"name":"行者骑行软件"}
     * desc : 行者骑行软件
     */

    private String keywords;
    private String creator;
    private String version;
    private String name;
    private Date time;
    private String schemaLocation;
    private TrkBean trk;
    private String desc;

    @NoArgsConstructor
    @Data
    public static class TrkBean {
        /**
         * trkseg : {"trkpt":[{"lat":"39.924921","lon":"119.574201","time":"2019-10-13T07:14:21Z","ele":"-30.2"},{"lat":"39.924921","lon":"119.574201","time":"2019-10-13T07:14:21Z","ele":"-30.2"}]}
         * name : 行者骑行软件
         */

        private TrksegBean trkseg;
        private String name;

        @NoArgsConstructor
        @Data
        public static class TrksegBean {
            private List<TrkptBean> trkpt;

            @NoArgsConstructor
            @Data
            public static class TrkptBean {
                /**
                 * lat : 39.924921
                 * lon : 119.574201
                 * time : 2019-10-13T07:14:21Z
                 * ele : -30.2
                 */

                private String lat;
                private String lon;
                private String time;
                private String ele;
            }
        }
    }
}
