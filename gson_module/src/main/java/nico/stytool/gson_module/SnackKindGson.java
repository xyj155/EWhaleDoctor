package nico.stytool.gson_module;

public class SnackKindGson {

        /**
         * id : 1
         * snackName : 活力鲜果
         * snackPicture : https://img.alicdn.com/imgextra/i4/2772080898/O1CN01lhVxK61IVK9HMOkNj_!!2772080898.jpg_430x430q90.jpg
         */

        private int id;
        private String snackName;
        private String snackPicture;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSnackName() {
            return snackName;
        }

        public void setSnackName(String snackName) {
            this.snackName = snackName;
        }

        public String getSnackPicture() {
            return snackPicture;
        }

        public void setSnackPicture(String snackPicture) {
            this.snackPicture = snackPicture;
        }
}
