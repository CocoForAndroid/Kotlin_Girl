package com.djc.kotlin.girl.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.util.Base64
import android.util.DisplayMetrics
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

import java.io.ByteArrayOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.GregorianCalendar
import java.util.Locale
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by djc on 2016/11/16.
 * 常用转换
 */
object LocalUtils {
    /**
     * 判断是否是手机号码
     *
     * @param phone
     * @return
     */
    fun isMobile(phone: String): Boolean {
        var p: Pattern? = null
        var m: Matcher? = null
        var b = false
        p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$") // 验证手机号
        m = p!!.matcher(phone)
        b = m!!.matches()
        return b
    }

    /**
     * 判断是否是合法的身份证号
     *
     * @param str
     * @return
     */
    fun IsIDcard(str: String): Boolean {
        val regex = "(^\\d{18}$)|(^\\d{15}$)"
        return match(regex, str)
    }

    /**
     * 正则表达式判断
     *
     * @param regex
     * @param str
     * @return
     */
    private fun match(regex: String, str: String): Boolean {
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(str)
        return matcher.matches()
    }

    /**
     * dp2px
     */
    fun dp2px(mContext: Context, dp: Float): Int {
        //拿到设备密度
        val scale = mContext.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

    /**
     * px2dp
     *
     * @param mContext
     * @param px
     * @return
     */
    fun px2dp(mContext: Context, px: Float): Int {
        val scale = mContext.resources.displayMetrics.density
        return (px / scale + 0.5f).toInt()
    }

    /**
     * 获取设备密度
     *
     * @param mContext
     * @return
     */
    fun getDensity(mContext: Context): Float {
        return mContext.resources.displayMetrics.density
    }

    // 获取系统版本名
    fun getVersionName(context: Context): String {
        // 获取packagemanager的实例
        val packageManager = context.packageManager
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        var packInfo: PackageInfo? = null
        try {
            packInfo = packageManager.getPackageInfo(context.packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {

            e.printStackTrace()
        }

        return packInfo!!.versionName
    }

    // 获取系统版本号
    fun getVersionCode(context: Context): Int {
        // 获取packagemanager的实例
        val packageManager = context.packageManager
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        var packInfo: PackageInfo? = null
        try {
            packInfo = packageManager.getPackageInfo(context.packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return packInfo!!.versionCode
    }

    /**
     * 获取屏幕宽度
     *
     * @param mContext
     * @return
     */
    fun getScreenWidth(mContext: Context): Int {
        val displayMetrics = mContext.resources.displayMetrics
        return displayMetrics.widthPixels
    }

    /**
     * 获取屏幕高度
     *
     * @param mContext
     * @return
     */
    fun getScreenHeight(mContext: Context): Int {
        val displayMetrics = mContext.resources.displayMetrics
        return displayMetrics.heightPixels
    }

    /**
     * 格式化时间
     */
    fun format(millisecond: Long): String {
        val formatter = SimpleDateFormat("mm:ss", Locale.CHINA)
        return formatter.format(Date(millisecond))
    }

    /**
     * 格式化时间
     */
    fun formatYMD(millisecond: Long): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)
        return formatter.format(Date(millisecond))
    }

    /**
     * 关闭软键盘
     */
    fun closeInputSoft(et: EditText, context: Context) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(et.windowToken, 0)
    }

    /**
     * bitmap转为base64
     *
     * @param bitmap
     * @return
     */
    fun bitmapToBase64(bitmap: Bitmap?): String? {

        var result: String? = null
        var baos: ByteArrayOutputStream? = null
        try {
            if (bitmap != null) {
                baos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos)
                baos.flush()
                baos.close()

                val bitmapBytes = baos.toByteArray()
                result = Base64.encodeToString(bitmapBytes, 0, bitmapBytes.size, Base64.DEFAULT)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                if (baos != null) {
                    baos.flush()
                    baos.close()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        return result
    }

    /**
     * 获取与今天时间差
     *
     * @param
     * @return
     */
    fun countDate(startTime: String): Double {

        val curDate = Date(System.currentTimeMillis())// 获取当前时间
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")// 输入日期的格式
        var date: Date? = null
        try {
            date = simpleDateFormat.parse(startTime)
            if (date == null) return 0.0
        } catch (e: java.text.ParseException) {
            return 0.0
            //    e.printStackTrace();
        }

        val cal1 = GregorianCalendar()
        val cal2 = GregorianCalendar()
        cal1.time = date
        cal2.time = curDate
        return ((cal2.timeInMillis - cal1.timeInMillis) / (1000 * 3600 * 24)).toDouble()
    }

    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    fun backgroundAlpha(context: Activity, bgAlpha: Float) {
        val lp = context.window.attributes
        lp.alpha = bgAlpha
        context.window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        context.window.attributes = lp
    }
}