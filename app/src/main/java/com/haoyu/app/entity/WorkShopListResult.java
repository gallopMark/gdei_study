package com.haoyu.app.entity;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.haoyu.app.base.BaseResponseResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建日期：2017/1/3 on 9:54
 * 描述:
 * 作者:马飞奔 Administrator
 */
public class WorkShopListResult extends BaseResponseResult<WorkShopListResult.MData> {
    public class MData {
        /**
         * mWorkshops	工作坊列表
         * notTemplateNum	全部工作坊数量
         * myRelativeNum	与我相关的工作坊数量
         * paginator	分页信息
         */
        @Expose
        @SerializedName("mWorkshops")
        private List<WorkShopMobileEntity> mWorkshops = new ArrayList<>();
        @Expose
        @SerializedName("notTemplateNum")
        private int notTemplateNum;
        @Expose
        @SerializedName("myRelativeNum")
        private int myRelativeNum;
        @Expose
        @SerializedName("paginator")
        private Paginator paginator;
        @Expose
        @SerializedName("mWorkshopUserMap")
        private Object mWorkshopUserMap;

        public List<WorkShopMobileEntity> getmWorkshops() {
            if (mWorkshops == null) {
                return new ArrayList<>();
            }
            return mWorkshops;
        }

        public void setmWorkshops(List<WorkShopMobileEntity> mWorkshops) {
            this.mWorkshops = mWorkshops;
        }

        public Paginator getPaginator() {
            return paginator;
        }

        public void setPaginator(Paginator paginator) {
            this.paginator = paginator;
        }

        public int getMyRelativeNum() {
            return myRelativeNum;
        }

        public void setMyRelativeNum(int myRelativeNum) {
            this.myRelativeNum = myRelativeNum;
        }

        public int getNotTemplateNum() {
            return notTemplateNum;
        }

        public void setNotTemplateNum(int notTemplateNum) {
            this.notTemplateNum = notTemplateNum;
        }

        public Map<String, WorkShopMobileUser> getmWorkshopUserMap() {
            try {
                Gson gson = new Gson();
                String toJson = gson.toJson(mWorkshopUserMap);
                Map<String, WorkShopMobileUser> map = gson.fromJson(toJson, new TypeToken<Map<String, WorkShopMobileUser>>() {
                }.getType());
                return map;
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
                return new HashMap<>();
            }
        }

        public void setmWorkshopUserMap(Object mWorkshopUserMap) {
            this.mWorkshopUserMap = mWorkshopUserMap;
        }
    }
}
