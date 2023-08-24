package top.chukongxiang.utils.file.mimetype;

import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 楚孔响
 * @version 1.0
 * @date 2023/8/24 11:51
 */
public class MimeTypeUtil {

    private static final MimeTypePool MIME_TYPE_POOL = new MimeTypePool();

    /**
     * Get the mime type for a format.
     * @param format the format
     * @param defaultValue the default mime type
     * @return the mime type
     */
    public static String getMimeType(String format, String defaultValue) {
        if (format == null) {
            return defaultValue;
        }
        String value = MIME_TYPE_POOL.get(format.toLowerCase());
        if (value == null) {
            value = URLConnection.getFileNameMap().getContentTypeFor("test." + format);
        }
        return value == null ? defaultValue : value;
    }

    /**
     * Get the mime type for a format. default is null.
     * @param format the format
     * @return the mime type
     */
    public static String getMimeType(String format) {
        return getMimeType(format, null);
    }

    /**
     * Get the mime type for a format. default is application/octet-stream.
     * @param format the format
     * @return the mime type
     */
    public static String getNonNullMimeType(String format) {
        return getMimeType(format, "application/octet-stream");
    }

    /**
     * Get the format for a mime type.
     * @param mimeType the mime type
     * @return the format list
     */
    public static List<String> getFormats(String mimeType) {
        List<String> formats = new ArrayList<>();
        if (mimeType == null) {
            return formats;
        }
        for (Map.Entry<String, String> entry : MIME_TYPE_POOL.entrySet()) {
            if (entry.getValue().equals(mimeType)) {
                formats.add(entry.getKey());
            }
        }

        return formats;
    }

    /**
     * Get the first format for a mime type.
     * @param mimeType the mime type
     * @param defaultMimeType the default mime type
     * @return the format
     */
    public static String getFirstFormat(String mimeType, String defaultMimeType) {
        List<String> formats = getFormats(mimeType);
        return formats.size() == 0 ? defaultMimeType : formats.get(0);
    }

    /**
     * Get the first format for a mime type. default is null.
     * @param mimeType the mime type
     * @return the format
     */
    public static String getFirstFormat(String mimeType) {
        return getFirstFormat(mimeType, null);
    }


}
