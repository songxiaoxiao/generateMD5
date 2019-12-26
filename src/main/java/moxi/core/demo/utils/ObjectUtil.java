package moxi.core.demo.utils;

import groovy.lang.Tuple2;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class ObjectUtil {
    private static final Logger logger = LoggerFactory.getLogger(ObjectUtil.class);

    private ObjectUtil() {
    }

    public static String getTimeText(Tuple2<TimeUnit, Long> config) {
        if (config != null && config.getFirst() != null && config.getSecond() != null) {
            String rtn = "";
            switch (config.getFirst()) {
                case DAYS:
                    rtn = config.getSecond() + "天";
                    break;
                case HOURS:
                    rtn = config.getSecond() + "小时";
                    break;
                case MINUTES:
                    rtn = config.getSecond() + "分钟";
                    break;
                case SECONDS:
                    rtn = config.getSecond() + "秒";
                    break;
                case MILLISECONDS:
                    rtn = config.getSecond() + "毫秒";
                    break;
                case MICROSECONDS:
                    rtn = config.getSecond() + "微秒";
                    break;
                case NANOSECONDS:
                    rtn = config.getSecond() + "纳秒";
                    break;
                default:
                    break;
            }

            return rtn;
        }
        return "";
    }

    public static boolean anyIsEmpty(Collection... values) {
        if (ArrayUtils.isNotEmpty(values)) {
            if (values.length == 1) {
                return CollectionUtils.isEmpty(values[0]);
            } else {
                return Stream.of(values).anyMatch(CollectionUtils::isEmpty);
            }
        }
        return false;
    }

    public static boolean anyIsNotEmpty(Collection... values) {
        if (ArrayUtils.isNotEmpty(values)) {
            if (values.length == 1) {
                return CollectionUtils.isNotEmpty(values[0]);
            } else {
                return Stream.of(values).anyMatch(CollectionUtils::isNotEmpty);
            }
        }
        return false;
    }

    public static boolean allIsEmpty(Collection... values) {
        if (ArrayUtils.isNotEmpty(values)) {
            if (values.length == 1) {
                return CollectionUtils.isEmpty(values[0]);
            } else {
                return Stream.of(values).allMatch(CollectionUtils::isEmpty);
            }
        }
        return false;
    }

    public static boolean allIsNotEmpty(Collection... values) {
        if (ArrayUtils.isNotEmpty(values)) {
            if (values.length == 1) {
                return CollectionUtils.isNotEmpty(values[0]);
            } else {
                return Stream.of(values).allMatch(CollectionUtils::isNotEmpty);
            }
        }
        return false;
    }

    /**
     * 任何一个参数为null就为true
     *
     * @param values
     * @return
     */
    public static boolean anyIsNull(Object... values) {
        if (ArrayUtils.isNotEmpty(values)) {
            if (values.length == 1) {
                return Objects.isNull(values[0]);
            } else {
                return Stream.of(values).anyMatch(Objects::isNull);
            }
        }
        return false;
    }

    /**
     * 任何一个字符串为null或者空就为true
     *
     * @param values
     * @return
     */
    public static boolean anyIsBlank(String... values) {
        if (ArrayUtils.isNotEmpty(values)) {
            if (values.length == 1) {
                return StringUtils.isBlank(values[0]);
            } else {
                return Stream.of(values).anyMatch(StringUtils::isBlank);
            }
        }
        return false;
    }

    /**
     * 所有参数为null就为true
     *
     * @param values
     * @return
     */
    public static boolean allIsNull(Object... values) {
        if (ArrayUtils.isNotEmpty(values)) {
            if (values.length == 1) {
                return Objects.isNull(values[0]);
            } else {
                return Stream.of(values).allMatch(Objects::isNull);
            }
        }
        return false;
    }

    public static boolean allIsNotNull(Object... values) {
        if (ArrayUtils.isNotEmpty(values)) {
            if (values.length == 1) {
                return Objects.nonNull(values[0]);
            } else {
                return Stream.of(values).allMatch(Objects::nonNull);
            }
        }
        return false;
    }

    /**
     * 所有字符串为null或者空就为true
     *
     * @param values
     * @return
     */
    public static boolean allIsBlank(String... values) {
        if (ArrayUtils.isNotEmpty(values)) {
            if (values.length == 1) {
                return StringUtils.isBlank(values[0]);
            } else {
                return Stream.of(values).allMatch(StringUtils::isBlank);
            }
        }
        return false;
    }

    /**
     * 所有字符串不为为null或者空就为true
     *
     * @param values
     * @return
     */
    public static boolean allIsNotBlank(String... values) {
        if (ArrayUtils.isNotEmpty(values)) {
            if (values.length == 1) {
                return StringUtils.isNotBlank(values[0]);
            } else {
                return Stream.of(values).allMatch(StringUtils::isNotBlank);
            }
        }
        return false;
    }
}
