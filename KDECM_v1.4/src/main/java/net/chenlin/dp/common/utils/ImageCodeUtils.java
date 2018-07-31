package net.chenlin.dp.common.utils;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

import java.util.Properties;

public class ImageCodeUtils {

    private static ImageCodeUtils instance = null;

    public synchronized static ImageCodeUtils getInstance() {
        if (instance == null) {
            instance = new ImageCodeUtils();
        }
        return instance;
    }

    private DefaultKaptcha producer = null;

    public Producer getProducer() {
        if (producer == null) {
            producer = new DefaultKaptcha();
            Properties props = new Properties();
            props.put("kaptcha.border", "no");
            props.put("kaptcha.textproducer.font.color", "black");
            props.put("kaptcha.textproducer.char.space", "5");
            Config config = new Config(props);
            producer.setConfig(config);
        }
        return producer;
    }

}
