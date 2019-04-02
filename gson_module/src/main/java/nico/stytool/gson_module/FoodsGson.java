package nico.stytool.gson_module;

public class FoodsGson {

        /**
         * id : 1
         * foodName : 巧师傅鲜果千层系列
         * foodsPrice : 133.00
         * foodsSize : 个
         * foodPicture : http://syxfoods.oss-cn-beijing.aliyuncs.com/巧师傅鲜果千层系列.jpg?x-oss-process=style/press
         * activityId : 3
         * kindId : 20
         * foodsOwner : 1
         * isShow : 1
         * needCount : 3
         * foodsOrign : null
         */

        private int id;
        private String foodName;
        private String foodsPrice;
        private String foodsSize;
        private String foodPicture;
        private int activityId;
        private int kindId;
        private String foodsOwner;
        private String isShow;
        private String needCount;
        private Object foodsOrign;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFoodName() {
            return foodName;
        }

        public void setFoodName(String foodName) {
            this.foodName = foodName;
        }

        public String getFoodsPrice() {
            return foodsPrice;
        }

        public void setFoodsPrice(String foodsPrice) {
            this.foodsPrice = foodsPrice;
        }

        public String getFoodsSize() {
            return foodsSize;
        }

        public void setFoodsSize(String foodsSize) {
            this.foodsSize = foodsSize;
        }

        public String getFoodPicture() {
            return foodPicture;
        }

        public void setFoodPicture(String foodPicture) {
            this.foodPicture = foodPicture;
        }

        public int getActivityId() {
            return activityId;
        }

        public void setActivityId(int activityId) {
            this.activityId = activityId;
        }

        public int getKindId() {
            return kindId;
        }

        public void setKindId(int kindId) {
            this.kindId = kindId;
        }

        public String getFoodsOwner() {
            return foodsOwner;
        }

        public void setFoodsOwner(String foodsOwner) {
            this.foodsOwner = foodsOwner;
        }

        public String getIsShow() {
            return isShow;
        }

        public void setIsShow(String isShow) {
            this.isShow = isShow;
        }

        public String getNeedCount() {
            return needCount;
        }

        public void setNeedCount(String needCount) {
            this.needCount = needCount;
        }

        public Object getFoodsOrign() {
            return foodsOrign;
        }

        public void setFoodsOrign(Object foodsOrign) {
            this.foodsOrign = foodsOrign;
        }
}
