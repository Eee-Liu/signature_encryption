package com.baj.encryption;

/**
 * @Author: LiuYi
 * @Description:
 * @Date: Created in 2018/12/4 11:08
 */
public class SignatureEncryptService {
    private String key;
    private String secret;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public SignatureEncryptService(String key, String secret) {
        this.key = key;
        this.secret = secret;
    }

    public String encrypt(String url) throws Exception{
        if (StringUtil.isTrimEmpty(url)){
            throw new Exception("url is invalid");
        }
        return EncryptUtil.encrypt(key, secret, url);
    }
}
