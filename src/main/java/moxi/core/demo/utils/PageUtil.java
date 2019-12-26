package moxi.core.demo.utils;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author 01
 */
public class PageUtil {
    private PageUtil() {
    }


    /**
     * 自带的selectOne方法是通过selectList来实现的,然后再get(0)
     *
     * @param service 具体实现类
     * @param wrapper 查询条件
     * @param <T>
     * @return
     */
    public static <T> Optional<T> getOne(IService<T> service, Wrapper<T> wrapper) {
        T defaultValue = null;
        if (service != null && wrapper != null) {
            Page<T> page = service.selectPage(new Page<T>(1, 1) {{
                setSearchCount(false);
            }}, wrapper);
            if (page != null && CollectionUtils.isNotEmpty(page.getRecords())) {
                defaultValue = page.getRecords().get(0);
            }
        }

        return Optional.ofNullable(defaultValue);
    }

    public static <T> List<T> getAllSerial(int batchSize, IService<T> service, Wrapper<T> wrapper) {
        if (batchSize > 0 && wrapper != null && service != null) {
            //查询总数
            int count = service.selectCount(wrapper);
            if (count > 0) {
                //确认总页数
                int pageCount = count % batchSize == 0 ? count / batchSize : count / batchSize + 1;
                //分页查询
                return getAll(batchSize, wrapper, service, IntStream.rangeClosed(1, pageCount));
            }
        }
        return null;
    }

    public static <T> List<T> getAllParallel(int batchSize, IService<T> service, Wrapper<T> wrapper) {
        if (batchSize > 0 && wrapper != null && service != null) {
            //查询总数
            int count = service.selectCount(wrapper);
            if (count > 0) {
                //确认总页数
                int pageCount = count % batchSize == 0 ? count / batchSize : count / batchSize + 1;
                //分页查询
                return getAll(batchSize, wrapper, service, IntStream.rangeClosed(1, pageCount).parallel());
            }
        }
        return null;
    }

    private static <T> List<T> getAll(int batchSize, Wrapper<T> wrapper, IService<T> service, IntStream intStream) {
        if (batchSize > 0 && wrapper != null && service != null && intStream != null) {
            return intStream.mapToObj(it -> {
                Page<T> page = service.selectPage(new Page<T>(it, batchSize) {{
                    setSearchCount(false);
                }}, wrapper);
                if (page != null && CollectionUtils.isNotEmpty(page.getRecords())) {
                    return page.getRecords();
                }
                return null;
            }).filter(Objects::nonNull).flatMap(Collection::stream).collect(Collectors.toList());
        }
        return null;
    }
}
