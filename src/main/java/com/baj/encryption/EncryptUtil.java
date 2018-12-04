package com.baj.encryption;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: LiuYi
 * @Description:
 * @Date: Created in 2018/12/4 11:16
 */
public class EncryptUtil {

    public static String encrypt(String key, String secret, String url) throws Exception {
        if (StringUtil.isTrimEmpty(key)) {
            throw new Exception("key is invalid");
        }
        if (StringUtil.isTrimEmpty(secret)) {
            throw new Exception("secret is invalid");
        }
        if (StringUtil.isTrimEmpty(url)) {
            throw new Exception("url is invalid");
        }

        //计算签名
        int parameterBegin = url.indexOf("?");
        List<String> parameterList = new ArrayList<>();
        parameterList.add("key");
        parameterList.add("sign");
        parameterList.add("timestamp");
        if (parameterBegin > 0) {
            String substring = url.substring(parameterBegin+1);
            if (StringUtil.isTrimEmpty(substring)){
                List<String> paramPairList = StringUtil.spiltToString(substring, "&");
                paramPairList.forEach(paramPair -> {
                    String[] params = paramPair.split("=");
                    parameterList.add(params[0]);
                });
            }
        }
        parameterList.sort(String::compareToIgnoreCase);

        long timestamp = new Date().getTime();
        StringBuffer singBuffer = new StringBuffer();
        singBuffer.append(secret).append(timestamp);
        parameterList.forEach(parameter -> {
            singBuffer.append(parameter.toLowerCase());
        });
        singBuffer.append(secret);
        String signature = DigestUtils.md5Hex(singBuffer.toString());

        //拼接url
        StringBuffer urlBuffer = new StringBuffer(url);
        if (parameterBegin < 0) {
            urlBuffer.append("?");
        } else {
            urlBuffer.append("&");
        }
        urlBuffer.append("sign=").append(signature).append("&");
        urlBuffer.append("key=").append(key).append("&");
        urlBuffer.append("timestamp=").append(timestamp);
        return urlBuffer.toString();
    }
}
