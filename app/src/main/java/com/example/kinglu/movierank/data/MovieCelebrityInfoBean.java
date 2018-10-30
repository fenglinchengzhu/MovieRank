package com.example.kinglu.movierank.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 影人条目信息
 *
 * Created by 风临城城主 on 2018/10/10.
 */

public class MovieCelebrityInfoBean {

    private String website;
    private String mobile_url;
    private String name;
    private String name_en;
    private String gender;
    private AvatarsBean avatars;
    private String summary;
    private String birthday;
    private String alt;
    private String born_place;
    private String constellation;
    private String id;
    private List<String> aka_en;
    private List<WorksBean> works;
    private List<String> professions;
    private List<PhotosBean> photos;
    private List<String> aka;

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public AvatarsBean getAvatars() {
        return avatars;
    }

    public void setAvatars(AvatarsBean avatars) {
        this.avatars = avatars;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getBorn_place() {
        return born_place;
    }

    public void setBorn_place(String born_place) {
        this.born_place = born_place;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getAka_en() {
        return aka_en;
    }

    public void setAka_en(List<String> aka_en) {
        this.aka_en = aka_en;
    }

    public List<WorksBean> getWorks() {
        return works;
    }

    public void setWorks(List<WorksBean> works) {
        this.works = works;
    }

    public List<String> getProfessions() {
        return professions;
    }

    public void setProfessions(List<String> professions) {
        this.professions = professions;
    }

    public List<PhotosBean> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotosBean> photos) {
        this.photos = photos;
    }

    public List<String> getAka() {
        return aka;
    }

    public void setAka(List<String> aka) {
        this.aka = aka;
    }

    public static class AvatarsBean {

        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

    public static class WorksBean {

        private SubjectBean subject;
        private List<String> roles;

        public SubjectBean getSubject() {
            return subject;
        }

        public void setSubject(SubjectBean subject) {
            this.subject = subject;
        }

        public List<String> getRoles() {
            return roles;
        }

        public void setRoles(List<String> roles) {
            this.roles = roles;
        }

        public static class SubjectBean {

            private RatingBean rating;
            private String title;
            private int collect_count;
            private String mainland_pubdate;
            private boolean has_video;
            private String original_title;
            private String subtype;
            private String year;
            private ImagesBean images;
            private String alt;
            private String id;
            private List<String> genres;
            private List<CastsBean> casts;
            private List<String> durations;
            private List<DirectorsBean> directors;
            private List<String> pubdates;

            public RatingBean getRating() {
                return rating;
            }

            public void setRating(RatingBean rating) {
                this.rating = rating;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getCollect_count() {
                return collect_count;
            }

            public void setCollect_count(int collect_count) {
                this.collect_count = collect_count;
            }

            public String getMainland_pubdate() {
                return mainland_pubdate;
            }

            public void setMainland_pubdate(String mainland_pubdate) {
                this.mainland_pubdate = mainland_pubdate;
            }

            public boolean isHas_video() {
                return has_video;
            }

            public void setHas_video(boolean has_video) {
                this.has_video = has_video;
            }

            public String getOriginal_title() {
                return original_title;
            }

            public void setOriginal_title(String original_title) {
                this.original_title = original_title;
            }

            public String getSubtype() {
                return subtype;
            }

            public void setSubtype(String subtype) {
                this.subtype = subtype;
            }

            public String getYear() {
                return year;
            }

            public void setYear(String year) {
                this.year = year;
            }

            public ImagesBean getImages() {
                return images;
            }

            public void setImages(ImagesBean images) {
                this.images = images;
            }

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public List<String> getGenres() {
                return genres;
            }

            public void setGenres(List<String> genres) {
                this.genres = genres;
            }

            public List<CastsBean> getCasts() {
                return casts;
            }

            public void setCasts(List<CastsBean> casts) {
                this.casts = casts;
            }

            public List<String> getDurations() {
                return durations;
            }

            public void setDurations(List<String> durations) {
                this.durations = durations;
            }

            public List<DirectorsBean> getDirectors() {
                return directors;
            }

            public void setDirectors(List<DirectorsBean> directors) {
                this.directors = directors;
            }

            public List<String> getPubdates() {
                return pubdates;
            }

            public void setPubdates(List<String> pubdates) {
                this.pubdates = pubdates;
            }

            public static class RatingBean {

                private int max;
                private double average;
                private DetailsBean details;
                private String stars;
                private int min;

                public int getMax() {
                    return max;
                }

                public void setMax(int max) {
                    this.max = max;
                }

                public double getAverage() {
                    return average;
                }

                public void setAverage(double average) {
                    this.average = average;
                }

                public DetailsBean getDetails() {
                    return details;
                }

                public void setDetails(DetailsBean details) {
                    this.details = details;
                }

                public String getStars() {
                    return stars;
                }

                public void setStars(String stars) {
                    this.stars = stars;
                }

                public int getMin() {
                    return min;
                }

                public void setMin(int min) {
                    this.min = min;
                }

                public static class DetailsBean {

                    @SerializedName("1")
                    private int _$1;
                    @SerializedName("2")
                    private int _$2;
                    @SerializedName("3")
                    private int _$3;
                    @SerializedName("4")
                    private int _$4;
                    @SerializedName("5")
                    private int _$5;

                    public int get_$1() {
                        return _$1;
                    }

                    public void set_$1(int _$1) {
                        this._$1 = _$1;
                    }

                    public int get_$2() {
                        return _$2;
                    }

                    public void set_$2(int _$2) {
                        this._$2 = _$2;
                    }

                    public int get_$3() {
                        return _$3;
                    }

                    public void set_$3(int _$3) {
                        this._$3 = _$3;
                    }

                    public int get_$4() {
                        return _$4;
                    }

                    public void set_$4(int _$4) {
                        this._$4 = _$4;
                    }

                    public int get_$5() {
                        return _$5;
                    }

                    public void set_$5(int _$5) {
                        this._$5 = _$5;
                    }
                }
            }

            public static class ImagesBean {

                private String small;
                private String large;
                private String medium;

                public String getSmall() {
                    return small;
                }

                public void setSmall(String small) {
                    this.small = small;
                }

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }
            }

            public static class CastsBean {

                private AvatarsBeanX avatars;
                private String name_en;
                private String name;
                private String alt;
                private String id;

                public AvatarsBeanX getAvatars() {
                    return avatars;
                }

                public void setAvatars(AvatarsBeanX avatars) {
                    this.avatars = avatars;
                }

                public String getName_en() {
                    return name_en;
                }

                public void setName_en(String name_en) {
                    this.name_en = name_en;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getAlt() {
                    return alt;
                }

                public void setAlt(String alt) {
                    this.alt = alt;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public static class AvatarsBeanX {

                    private String small;
                    private String large;
                    private String medium;

                    public String getSmall() {
                        return small;
                    }

                    public void setSmall(String small) {
                        this.small = small;
                    }

                    public String getLarge() {
                        return large;
                    }

                    public void setLarge(String large) {
                        this.large = large;
                    }

                    public String getMedium() {
                        return medium;
                    }

                    public void setMedium(String medium) {
                        this.medium = medium;
                    }
                }
            }

            public static class DirectorsBean {

                private AvatarsBeanXX avatars;
                private String name_en;
                private String name;
                private String alt;
                private String id;

                public AvatarsBeanXX getAvatars() {
                    return avatars;
                }

                public void setAvatars(AvatarsBeanXX avatars) {
                    this.avatars = avatars;
                }

                public String getName_en() {
                    return name_en;
                }

                public void setName_en(String name_en) {
                    this.name_en = name_en;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getAlt() {
                    return alt;
                }

                public void setAlt(String alt) {
                    this.alt = alt;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public static class AvatarsBeanXX {

                    private String small;
                    private String large;
                    private String medium;

                    public String getSmall() {
                        return small;
                    }

                    public void setSmall(String small) {
                        this.small = small;
                    }

                    public String getLarge() {
                        return large;
                    }

                    public void setLarge(String large) {
                        this.large = large;
                    }

                    public String getMedium() {
                        return medium;
                    }

                    public void setMedium(String medium) {
                        this.medium = medium;
                    }
                }
            }
        }
    }

    public static class PhotosBean {

        private String thumb;
        private String image;
        private String cover;
        private String alt;
        private String id;
        private String icon;

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
}
