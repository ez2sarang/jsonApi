package com.fast2.zimin.util.tag;

import com.fast2.zimin.util.MessageSourceAccessor;
import com.fast2.zimin.util.TheLogger;
import org.apache.commons.lang.StringUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Functions {
	public static Object getConstant(String subClass, String field) {
		try {
			@SuppressWarnings("rawtypes")
			Class cl;
			if (StringUtils.isBlank(subClass)) {
				cl = Class.forName("com.fast2.zimin.util.Constants");
			} else {
				cl = Class
						.forName("com.fast2.zimin.util.Constants$" + subClass);
			}

			return cl.getField(field).get(null);
		} catch (Exception e) {
			return null;
		}
	}

	public static Object getContentAssetConstant(String field) {
		try {
			@SuppressWarnings("rawtypes")
			Class cl = Class.forName("com.fast2.zimin.util.ContentAssetConstantUtil");

			return cl.getField(field).get(null);
		} catch (Exception e) {
			return null;
		}
	}
	
	/*public static String getFileTypeName(String contentSubsetType,
			String fileType) {
		try {
			return ContentAssetConstantUtil.getContentAssetConstant(
					contentSubsetType, fileType).getFileTypeName();
		} catch (Exception e) {
			TheLogger.warn(e.getMessage());
			return "FileTypeName Error";
		}
	}*/

	public static String message(MessageSourceAccessor wmSource, String key) {
		try {
			return wmSource.getMessage(key);
		} catch (Exception e) {
			TheLogger.warn(e.getMessage());
			return "";
		}
	}

	/**
	 * 입력받은 두개의 문자를 합친다.
	 * 
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	public static String concat(Object arg1, Object arg2) {
		return String
				.format("%s%s", String.valueOf(arg1), String.valueOf(arg2));
	}

	public static String format(String format, Object[] args) {
		return String.format(format, args);
	}

    public static <K, V> Map<K, V> convertListToMap(Collection<V> sourceList, ListToMapConverter<K, V> converter) {
        Map<K, V> newMap = new HashMap<K, V>();
        if(sourceList != null) {
            for (V item : sourceList) {
                newMap.put( converter.getKey(item), item );
            }
        }
        return newMap;
    }

    public static interface ListToMapConverter<K, V> {
        public K getKey(V item);
    }

    /**
     * Collection type 을 원하는 Collection type 으로 변경한다.
     * example:
     * Functions.collectionToCollection(source, target, new Functions.collectionConverter<Long, Category>() {
     *     @Override
     *     public Long get(Category item) {
     *         return item.getId();
     *     }
     * });
     * @param source 원본 Collection
     * @param target 대상 Collection
     * @param converter 변경 구현체
     * @param <T> 대상 Collection 에 담길 객체형
     * @param <V> 원본 Collection 에 담겨있는 객체형
     * @return target
     */
    public static <T, V> Collection collectionToCollection(Collection<V> source, Collection<T> target/*guava라이브러리를 사용하면 해당 파라메터가 빠질 수 있다.*/, collectionConverter<T, V> converter) {
        for (V item : source) {
            target.add(converter.get(item));
        }
        return target;
    }

    public static interface collectionConverter<T, V> {
        public T get(V value);
    }
}