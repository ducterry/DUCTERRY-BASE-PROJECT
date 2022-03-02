
package com.ducterry.base.utils;

import com.ducterry.base.enums.ErrorStatus;
import com.ducterry.base.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponentsBuilder;


import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;


@Slf4j
public class CommonUtils {

    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String NAME_PATTERN = "^[a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêếìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹý ]+$";
    public static final String CUSTOMER_NAME_PATTERN = "^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêếìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹý ]+$";
    public static final String ADDRESS_PATTERN = "^[a-zA-Z0-9\\,\\*\\/ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêếìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹý ]+$";
    public static final String ALPHABETA_PATTERN = "^[A-Za-z0-9]+$";
    public static String MID_PATTERN = "^[a-zA-Z0-9]{15}$";
    public static String TID_PATTERN = "^[0-9]{8}$";
    public static String NUMBER_PATTERN = "\\d{1,20}";
    public static final String PHONE_NUMBER_PATTERN = "^[0-9]+$";
    public static String PHONE_PATTERN = "^(086|096|097|098|032|033|034|035|036|037|038|039|091|094|088|083|084|085|081|082|089|090|093|070|079|077|076|078|092|056|058|099|059)[0-9]{7}$";
    static final String POSTCODE_PATTERN = "[0-9 ]*";
    static final String SHORTNAME_PATTERN = "[a-zA-Z0-9_\\s]{1,17}";
    static final String PIN6_PATTERN = "^[0-9]{6}$";
    static final String MUID_PATTERN = "^[A-Za-z0-9-_]{3,25}$";
    static final String[] PHONE_NUMBERS = new String[]{"086", "096", "097", "098", "032", "033", "034", "035", "036", "037", "038", "039", "091", "094", "088", "083", "084", "085", "081", "082", "089", "090", "093", "070", "079", "077", "076", "078", "092", "056", "058", "099", "059"};


    public static boolean validateEmail(String email) {
        return email.matches(EMAIL_PATTERN);
    }

    public static boolean validatePhone(String phone) {
        return phone.matches(PHONE_PATTERN);
    }

    public static <T> List<List<T>> devideList(int partSize, List<T> originalList) {
        List<List<T>> partitions = new ArrayList();
        for (int i = 0; i < originalList.size(); i += partSize) {
            partitions.add(originalList.subList(i, Math.min(i + partSize, originalList.size())));
        }
        return partitions;
    }

    // datetime
    public static Date getStartDayFromDate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getLastDayFromDate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static void sleep(long miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException ex) {
            log.error("Thread sleep interrupt", ex);
        }
    }

    public static boolean checkRequire(Object... params) {
        for (Object p : params) {
            if (p == null) return false;
            if (p instanceof String) {
                if (StringUtils.isBlank((String) p)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static String getResourceFullpath(String filePath) {
        ClassLoader classLoader = CommonUtils.class.getClassLoader();
        URL url = classLoader.getResource("./");
        log.info("url: {}" + url);
        return url.getPath();
    }

    public static String decodeTimeHHmm(Long timeHHmm) {
        if (timeHHmm == null) return "";
        long h = (long) Math.floor(timeHHmm / 60);
        long m = timeHHmm - h * 60;
        return String.format("%02d:%02d", h, m);
    }

    public static String formatTime(Date date, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public static long getTimeHHmm(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY) * 60 + calendar.get(Calendar.MINUTE);
    }

    public static Date getTimeAdditional(Date currentDate, int fieldTime, int additionAmount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(fieldTime, additionAmount);
        return calendar.getTime();
    }

    public static int getRandom4Digit() {
        Random random = new Random();
        int i = random.nextInt(8999) + 1000;
        return i;
    }

    /**
     * Dinh dang so dien thoai theo dang 0..
     *
     * @param msisdn So dien thoai can xu ly
     * @return So dien thoai da dc dinh dang
     */
    public static String formatMobileZero(String msisdn) {
        String strResult = "";
        if (msisdn != null && !msisdn.equals("")) {
            if (msisdn.startsWith("0")) {
                strResult = msisdn;
            } else if (msisdn.startsWith("84")) {
                strResult = "0" + msisdn.substring(2);
            } else {
                strResult = "0" + msisdn;
            }
        }
        return strResult;
    }

    public static boolean checkMsisdnInternal(String msisdn) {
        String[] dauso = PHONE_NUMBERS;
        msisdn = formatMobileZero(msisdn);
        for (int i = 0; i < dauso.length; i++) {
            String dau = msisdn.substring(0, 3);
            if (dau.contains(dauso[i])) {
                return true;
            }
        }
        return false;
    }


    public static Object retrieveObjectValue(Object obj, String property) {
        if (property.contains(".")) {
            // we need to recurse down to final object
            String[] props = property.split("\\.");
            try {
                Object ivalue = null;
                if (Map.class.isAssignableFrom(obj.getClass())) {
                    Map map = (Map) obj;
                    ivalue = map.get(props[0]);
                } else {
                    String prop = props[0];
                    String propName = prop.substring(0, 1).toUpperCase() + prop.substring(1);
                    Method method = obj.getClass().getMethod("get" + propName);
                    ivalue = method.invoke(obj);
                }
                if (ivalue == null)
                    return null;
                return retrieveObjectValue(ivalue, property.substring(props[0].length() + 1));
            } catch (Exception e) {
                throw new RuntimeException("Failed to retrieve value for " + property, e);
            }
        } else {
            // let's get the object value directly
            try {
                if (Map.class.isAssignableFrom(obj.getClass())) {
                    Map map = (Map) obj;
                    return map.get(property);
                } else {
                    String prop = property;
                    String propName = prop.substring(0, 1).toUpperCase() + prop.substring(1);
                    Method method = obj.getClass().getMethod("get" + propName);
                    return method.invoke(obj);
                }
            } catch (Exception e) {
                throw new RuntimeException("Failed to retrieve value for " + property, e);
            }
        }
    }

    public static void checkRequireFields(Object... fields) {
        for (Object f : fields) {
            if (f == null || StringUtils.isBlank(f.toString())) {
                throw new ApiException(HttpStatus.BAD_REQUEST,ErrorStatus.DO_SERVICE_SUCCESSFUL);
            }
        }
    }

    public static String uriComponentBuild(String url, Map<String, Object> payload) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
        for (String key : payload.keySet()) {
            builder.queryParam(key, payload.get(key));
        }
        return builder.toUriString();
    }

}
