package com.gamerole.common

/**
 * 页面跳转路由
 */
class RoutePath {
    companion object {
        const val COMMON_LOGOUT="/commom/service/logout"
        const val APP_MAIN = "/app/main"const val APP_FRAGMENT_HOME="/app/fragmentHome"

        /**
         * 登录
         */
        const val LOGIN_LOGIN = "/login/login"
        const val LOGIN_REGISTER = "/login/register"
        const val LOGIN_FORGET_PWD = "/login/forgetPwd"

        /**
         * 我的
         */
        const val MINE_FRAGMENT_MAIN = "/mine/fragmentMain"
        const val MINE_USER_DATA = "/mine/userData"
        const val MINE_MY_COURSE = "/mine/myCourse"
        const val MINE_QUESTION_RECORD = "/mine/questionRecord"
        const val MINE_BUY_COURSE_RECORD = "/mine/buyCourseRecord"
        const val MINE_UPDATE_PWD = "/mine/updatePwd"
        const val MINE_DIALOG_EDIT = "/mine/dialogEdit"
        /**
         * 课程
         */
        const val COURSE_FRAGMENT_COURSE="/course/fragmentCourse"
        const val COURSE_FRAGMENT_CLASS="/course/fragmentClass"
        const val COURSE_KIND="/course/kind"
        const val COURSE_WATCH="/course/watch"
        const val COURSE_ADD_MESSAGE="/course/addMessage"
        const val COURSE_MSG_LIST="/course/msgList"
        const val COURSE_CLASS_LIST="/course/classList"
        const val COURSE_VIDEO_LIST="/course/videoList"
        const val COURSE_CHAPTER_LIST="/course/chapterList"
        /**
         * （首页）咨询
         */

        const val ZIXUN_FRAGMENT_HOME="/zixun/fragmentHome"
        const val ZIXUN_ZIXUN_DEATIL="/zixun/zixunDetail"
        const val ZIXUN_FRAGMENT_ZIXUN_LIST="/zixun/fragmentZixunList"
        const val ZIXUN_ZIXUN_TAB="/zixun/zixunTab"
        const val ZIXUN_NOTICE_LIST="/zixun/noticeList"
        const val ZIXUN_DIALOG_NO_OPEN="/zixun/dialogNoOpen"
    }

}

/**
 * 路由页面跳转传递参数
 */
class RouteParam {
    companion object {

        const val DATA="data"
        const val TOP_TITLE="topTitle"
        const val CODE: String = "code"
        const val TYPE: String = "type"
        const val CONTENT: String = "content"
        const val TITLE: String = "title"
        const val COURSE_ID: String = "courseId"
        const val KEY_WORD: String = "keyword"
        const val CHAPTER_ID: String = "chapterId"
        const val IS_COVER: String = "isCover"
        const val IS_TRY: String = "isTry"
    }
}

class PlaceHold {
    companion object {
        const val IMG =
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202004%2F15%2F20200415114219_csnyf.thumb.400_0.webp&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1611456327&t=ce22e10fcc5d63c27edd3fb7c0243595"
        const val VIDEO =
            "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4"
        val DATA = mutableListOf("1","2","3","4","5","6","7","8","9","10")
    }
}
class DataStoreConfig {
    companion object {
        const val NOTICE_READ="noticeRead"
        const val TOKEN ="token"

    }
}
class RxBusKey {
    companion object {
        const val CHANGE_TYPE: String="changeType"
        const val NAME ="name"

    }
}
