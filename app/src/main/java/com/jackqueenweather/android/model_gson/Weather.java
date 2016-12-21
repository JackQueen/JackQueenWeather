package com.jackqueenweather.android.model_gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016-12-21.
 */

public class Weather {

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;

    private List<HeWeatherBean> HeWeather;

    public List<HeWeatherBean> getHeWeather() {
        return HeWeather;
    }

    public void setHeWeather(List<HeWeatherBean> HeWeather) {
        this.HeWeather = HeWeather;
    }

    public static class HeWeatherBean {
        /**
         * aqi : {"city":{"aqi":"48","pm10":"44","pm25":"33","qlty":"优"}}
         * basic : {"city":"太仓","cnty":"中国","id":"CN101190408","lat":"31.546000","lon":"121.154000","update":{"loc":"2016-12-21 09:54","utc":"2016-12-21 01:54"}}
         * daily_forecast : [{"astro":{"mr":"null","ms":"12:04","sr":"06:50","ss":"16:56"},"cond":{"code_d":"307","code_n":"305","txt_d":"大雨","txt_n":"小雨"},"date":"2016-12-21","hum":"91","pcpn":"9.1","pop":"100","pres":"1017","tmp":{"max":"17","min":"7"},"uv":"3","vis":"2","wind":{"deg":"189","dir":"东南风","sc":"微风","spd":"9"}},{"astro":{"mr":"00:23","ms":"12:37","sr":"06:51","ss":"16:56"},"cond":{"code_d":"104","code_n":"101","txt_d":"阴","txt_n":"多云"},"date":"2016-12-22","hum":"79","pcpn":"0.5","pop":"92","pres":"1022","tmp":{"max":"9","min":"3"},"uv":"2","vis":"10","wind":{"deg":"297","dir":"西北风","sc":"4-5","spd":"17"}},{"astro":{"mr":"01:17","ms":"13:10","sr":"06:51","ss":"16:57"},"cond":{"code_d":"100","code_n":"101","txt_d":"晴","txt_n":"多云"},"date":"2016-12-23","hum":"67","pcpn":"0.0","pop":"0","pres":"1027","tmp":{"max":"10","min":"3"},"uv":"4","vis":"10","wind":{"deg":"342","dir":"北风","sc":"微风","spd":"2"}}]
         * hourly_forecast : [{"cond":{"code":"306","txt":"中雨"},"date":"2016-12-21 10:00","hum":"95","pop":"92","pres":"1017","tmp":"17","wind":{"deg":"158","dir":"东南风","sc":"微风","spd":"19"}},{"cond":{"code":"307","txt":"大雨"},"date":"2016-12-21 13:00","hum":"89","pop":"96","pres":"1015","tmp":"17","wind":{"deg":"180","dir":"东南风","sc":"微风","spd":"25"}},{"cond":{"code":"307","txt":"大雨"},"date":"2016-12-21 16:00","hum":"87","pop":"95","pres":"1015","tmp":"13","wind":{"deg":"240","dir":"东南风","sc":"微风","spd":"29"}},{"cond":{"code":"104","txt":"阴"},"date":"2016-12-21 19:00","hum":"86","pop":"67","pres":"1017","tmp":"14","wind":{"deg":"282","dir":"东南风","sc":"微风","spd":"31"}},{"cond":{"code":"305","txt":"小雨"},"date":"2016-12-21 22:00","hum":"85","pop":"20","pres":"1019","tmp":"12","wind":{"deg":"298","dir":"西北风","sc":"4-5","spd":"29"}}]
         * now : {"cond":{"code":"101","txt":"多云"},"fl":"17","hum":"100","pcpn":"0","pres":"1018","tmp":"16","vis":"3","wind":{"deg":"140","dir":"东南风","sc":"4-5","spd":"21"}}
         * status : ok
         * suggestion : {"air":{"brf":"优","txt":"气象条件非常有利于空气污染物稀释、扩散和清除，可在室外正常活动。"},"comf":{"brf":"舒适","txt":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"},"cw":{"brf":"不宜","txt":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"},"drsg":{"brf":"较冷","txt":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"},"flu":{"brf":"易发","txt":"天冷风力较大且昼夜温差也很大，易发生感冒，请注意适当增减衣服。"},"sport":{"brf":"较不宜","txt":"有较强降水，建议您选择在室内进行健身休闲运动。"},"trav":{"brf":"一般","txt":"温度适宜，有微风同行，但较强降雨的天气将给您的出行带来很多的不便，若坚持旅行建议带上雨具。"},"uv":{"brf":"最弱","txt":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"}}
         */

        private AQI aqi;
        private Basic basic;
        private Now now;
        private String status;
        private Suggestion suggestion;
        private List<DailyForecast> daily_forecast;
        private List<HourlyForecastBean> hourly_forecast;

        public AQI getAqi() {
            return aqi;
        }

        public void setAqi(AQI aqi) {
            this.aqi = aqi;
        }

        public Basic getBasic() {
            return basic;
        }

        public void setBasic(Basic basic) {
            this.basic = basic;
        }

        public Now getNow() {
            return now;
        }

        public void setNow(Now now) {
            this.now = now;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Suggestion getSuggestion() {
            return suggestion;
        }

        public void setSuggestion(Suggestion suggestion) {
            this.suggestion = suggestion;
        }

        public List<DailyForecast> getDaily_forecast() {
            return daily_forecast;
        }

        public void setDaily_forecast(List<DailyForecast> daily_forecast) {
            this.daily_forecast = daily_forecast;
        }

        public List<HourlyForecastBean> getHourly_forecast() {
            return hourly_forecast;
        }

        public void setHourly_forecast(List<HourlyForecastBean> hourly_forecast) {
            this.hourly_forecast = hourly_forecast;
        }

        public static class AQI {
            /**
             * city : {"aqi":"48","pm10":"44","pm25":"33","qlty":"优"}
             */

            private AQICiyt city;

            public AQICiyt getCity() {
                return city;
            }

            public void setCity(AQICiyt city) {
                this.city = city;
            }

            public static class AQICiyt {
                /**
                 * aqi : 48
                 * pm10 : 44
                 * pm25 : 33
                 * qlty : 优
                 */

                private String aqi;
                private String pm10;
                private String pm25;
                private String qlty;

                public String getAqi() {
                    return aqi;
                }

                public void setAqi(String aqi) {
                    this.aqi = aqi;
                }

                public String getPm10() {
                    return pm10;
                }

                public void setPm10(String pm10) {
                    this.pm10 = pm10;
                }

                public String getPm25() {
                    return pm25;
                }

                public void setPm25(String pm25) {
                    this.pm25 = pm25;
                }

                public String getQlty() {
                    return qlty;
                }

                public void setQlty(String qlty) {
                    this.qlty = qlty;
                }
            }
        }

        public static class Basic {
            /**
             * city : 太仓
             * cnty : 中国
             * id : CN101190408
             * lat : 31.546000
             * lon : 121.154000
             * update : {"loc":"2016-12-21 09:54","utc":"2016-12-21 01:54"}
             */

            @SerializedName("city")
            private String cityName;
            private String cnty;
            @SerializedName("id")
            private String weatherId;
            private String lat;
            private String lon;
            private Update update;

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public String getCnty() {
                return cnty;
            }

            public void setCnty(String cnty) {
                this.cnty = cnty;
            }

            public String getWeatherId() {
                return weatherId;
            }

            public void setWeatherId(String weatherId) {
                this.weatherId = weatherId;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }

            public Update getUpdate() {
                return update;
            }

            public void setUpdate(Update update) {
                this.update = update;
            }

            public static class Update {
                /**
                 * loc : 2016-12-21 09:54
                 * utc : 2016-12-21 01:54
                 */

                private String loc;
                private String utc;

                public String getLoc() {
                    return loc;
                }

                public void setLoc(String loc) {
                    this.loc = loc;
                }

                public String getUtc() {
                    return utc;
                }

                public void setUtc(String utc) {
                    this.utc = utc;
                }
            }
        }

        public static class Now {
            /**
             * cond : {"code":"101","txt":"多云"}
             * fl : 17
             * hum : 100
             * pcpn : 0
             * pres : 1018
             * tmp : 16
             * vis : 3
             * wind : {"deg":"140","dir":"东南风","sc":"4-5","spd":"21"}
             */

            @SerializedName("cond")
            private More more;
            private String fl;
            private String hum;
            private String pcpn;
            private String pres;
            @SerializedName("tmp")
            private String temperature;
            private String vis;
            private WindBean wind;

            public More getMore() {
                return more;
            }

            public void setMore(More more) {
                this.more = more;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public WindBean getWind() {
                return wind;
            }

            public void setWind(WindBean wind) {
                this.wind = wind;
            }

            public static class More {
                /**
                 * code : 101
                 * txt : 多云
                 */

                private String code;
                private String txt;

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class WindBean {
                /**
                 * deg : 140
                 * dir : 东南风
                 * sc : 4-5
                 * spd : 21
                 */

                private String deg;
                private String dir;
                private String sc;
                private String spd;

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }

        public static class Suggestion {
            /**
             * air : {"brf":"优","txt":"气象条件非常有利于空气污染物稀释、扩散和清除，可在室外正常活动。"}
             * comf : {"brf":"舒适","txt":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"}
             * cw : {"brf":"不宜","txt":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"}
             * drsg : {"brf":"较冷","txt":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"}
             * flu : {"brf":"易发","txt":"天冷风力较大且昼夜温差也很大，易发生感冒，请注意适当增减衣服。"}
             * sport : {"brf":"较不宜","txt":"有较强降水，建议您选择在室内进行健身休闲运动。"}
             * trav : {"brf":"一般","txt":"温度适宜，有微风同行，但较强降雨的天气将给您的出行带来很多的不便，若坚持旅行建议带上雨具。"}
             * uv : {"brf":"最弱","txt":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"}
             */

            private AirBean air;
            @SerializedName("comf")
            private Comfort comfort;
            @SerializedName("cw")
            private CarWash carwash;
            private DrsgBean drsg;
            private FluBean flu;
            private Sport sport;
            private TravBean trav;
            private UvBean uv;

            public AirBean getAir() {
                return air;
            }

            public void setAir(AirBean air) {
                this.air = air;
            }

            public Comfort getComfort() {
                return comfort;
            }

            public void setComfort(Comfort comfort) {
                this.comfort = comfort;
            }

            public CarWash getCarwash() {
                return carwash;
            }

            public void setCarwash(CarWash carwash) {
                this.carwash = carwash;
            }

            public DrsgBean getDrsg() {
                return drsg;
            }

            public void setDrsg(DrsgBean drsg) {
                this.drsg = drsg;
            }

            public FluBean getFlu() {
                return flu;
            }

            public void setFlu(FluBean flu) {
                this.flu = flu;
            }

            public Sport getSport() {
                return sport;
            }

            public void setSport(Sport sport) {
                this.sport = sport;
            }

            public TravBean getTrav() {
                return trav;
            }

            public void setTrav(TravBean trav) {
                this.trav = trav;
            }

            public UvBean getUv() {
                return uv;
            }

            public void setUv(UvBean uv) {
                this.uv = uv;
            }

            public static class AirBean {
                /**
                 * brf : 优
                 * txt : 气象条件非常有利于空气污染物稀释、扩散和清除，可在室外正常活动。
                 */

                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class Comfort {
                /**
                 * brf : 舒适
                 * txt : 白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。
                 */

                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class CarWash {
                /**
                 * brf : 不宜
                 * txt : 不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。
                 */

                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class DrsgBean {
                /**
                 * brf : 较冷
                 * txt : 建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。
                 */

                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class FluBean {
                /**
                 * brf : 易发
                 * txt : 天冷风力较大且昼夜温差也很大，易发生感冒，请注意适当增减衣服。
                 */

                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class Sport {
                /**
                 * brf : 较不宜
                 * txt : 有较强降水，建议您选择在室内进行健身休闲运动。
                 */

                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class TravBean {
                /**
                 * brf : 一般
                 * txt : 温度适宜，有微风同行，但较强降雨的天气将给您的出行带来很多的不便，若坚持旅行建议带上雨具。
                 */

                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class UvBean {
                /**
                 * brf : 最弱
                 * txt : 属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。
                 */

                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }
        }

        public static class DailyForecast {
            /**
             * astro : {"mr":"null","ms":"12:04","sr":"06:50","ss":"16:56"}
             * cond : {"code_d":"307","code_n":"305","txt_d":"大雨","txt_n":"小雨"}
             * date : 2016-12-21
             * hum : 91
             * pcpn : 9.1
             * pop : 100
             * pres : 1017
             * tmp : {"max":"17","min":"7"}
             * uv : 3
             * vis : 2
             * wind : {"deg":"189","dir":"东南风","sc":"微风","spd":"9"}
             */

            private AstroBean astro;
            @SerializedName("cond")
            private More more;
            private String date;
            private String hum;
            private String pcpn;
            private String pop;
            private String pres;
            @SerializedName("tmp")
            private Temperature temperature;
            private String uv;
            private String vis;
            private WindBeanX wind;

            public AstroBean getAstro() {
                return astro;
            }

            public void setAstro(AstroBean astro) {
                this.astro = astro;
            }

            public More getMore() {
                return more;
            }

            public void setMore(More more) {
                this.more = more;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public Temperature getTemperature() {
                return temperature;
            }

            public void setTemperature(Temperature temperature) {
                this.temperature = temperature;
            }

            public String getUv() {
                return uv;
            }

            public void setUv(String uv) {
                this.uv = uv;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public WindBeanX getWind() {
                return wind;
            }

            public void setWind(WindBeanX wind) {
                this.wind = wind;
            }

            public static class AstroBean {
                /**
                 * mr : null
                 * ms : 12:04
                 * sr : 06:50
                 * ss : 16:56
                 */

                private String mr;
                private String ms;
                private String sr;
                private String ss;

                public String getMr() {
                    return mr;
                }

                public void setMr(String mr) {
                    this.mr = mr;
                }

                public String getMs() {
                    return ms;
                }

                public void setMs(String ms) {
                    this.ms = ms;
                }

                public String getSr() {
                    return sr;
                }

                public void setSr(String sr) {
                    this.sr = sr;
                }

                public String getSs() {
                    return ss;
                }

                public void setSs(String ss) {
                    this.ss = ss;
                }
            }

            public static class More {
                /**
                 * code_d : 307
                 * code_n : 305
                 * txt_d : 大雨
                 * txt_n : 小雨
                 */

                private String code_d;
                private String code_n;
                @SerializedName("txt_d")
                private String info;
                private String txt_n;

                public String getCode_d() {
                    return code_d;
                }

                public void setCode_d(String code_d) {
                    this.code_d = code_d;
                }

                public String getCode_n() {
                    return code_n;
                }

                public void setCode_n(String code_n) {
                    this.code_n = code_n;
                }

                public String getInfo() {
                    return info;
                }

                public void setInfo(String info) {
                    this.info = info;
                }

                public String getTxt_n() {
                    return txt_n;
                }

                public void setTxt_n(String txt_n) {
                    this.txt_n = txt_n;
                }
            }

            public static class Temperature {
                /**
                 * max : 17
                 * min : 7
                 */

                private String max;
                private String min;

                public String getMax() {
                    return max;
                }

                public void setMax(String max) {
                    this.max = max;
                }

                public String getMin() {
                    return min;
                }

                public void setMin(String min) {
                    this.min = min;
                }
            }

            public static class WindBeanX {
                /**
                 * deg : 189
                 * dir : 东南风
                 * sc : 微风
                 * spd : 9
                 */

                private String deg;
                private String dir;
                private String sc;
                private String spd;

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }

        public static class HourlyForecastBean {
            /**
             * cond : {"code":"306","txt":"中雨"}
             * date : 2016-12-21 10:00
             * hum : 95
             * pop : 92
             * pres : 1017
             * tmp : 17
             * wind : {"deg":"158","dir":"东南风","sc":"微风","spd":"19"}
             */

            private CondBeanXX cond;
            private String date;
            private String hum;
            private String pop;
            private String pres;
            private String tmp;
            private WindBeanXX wind;

            public CondBeanXX getCond() {
                return cond;
            }

            public void setCond(CondBeanXX cond) {
                this.cond = cond;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public WindBeanXX getWind() {
                return wind;
            }

            public void setWind(WindBeanXX wind) {
                this.wind = wind;
            }

            public static class CondBeanXX {
                /**
                 * code : 306
                 * txt : 中雨
                 */

                private String code;
                private String txt;

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class WindBeanXX {
                /**
                 * deg : 158
                 * dir : 东南风
                 * sc : 微风
                 * spd : 19
                 */

                private String deg;
                private String dir;
                private String sc;
                private String spd;

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }
    }
}
