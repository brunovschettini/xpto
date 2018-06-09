package br.com.xptosystems.utils;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONS {

    public static JSONObject get(String uri) throws Exception {
        // BufferedReader reader = null;
        try {
            return (JSONObject) new JSONTokener(IOUtils.toString(new URL(uri), Charset.defaultCharset())).nextValue();
        } catch (IOException | JSONException e) {
            return null;
        }
    }

}
