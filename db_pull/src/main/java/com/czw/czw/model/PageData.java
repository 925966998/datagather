package com.czw.czw.model;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.czw.czw.util.Logger;
import com.czw.czw.exception.DataException;
import com.czw.czw.util.ConvertUtil;

public class PageData extends HashMap implements Map {

	private static final long serialVersionUID = 1L;

	Map map = null;
	HttpServletRequest request;

	public PageData(HttpServletRequest request) {
		this.request = request;
		Map properties = request.getParameterMap();
		Map returnMap = new HashMap();
		Iterator entries = properties.entrySet().iterator();
		Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				value = "";
				for (int i = 0; i < values.length; i++) {
					value += values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		map = returnMap;
	}

	public PageData() {
		map = new HashMap();
	}

	@Override
	public Object get(Object key) {
		Object obj = null;
		if (map.get(key) instanceof Object[]) {
			Object[] arr = (Object[]) map.get(key);
			obj = request == null ? arr : (request.getParameter((String) key) == null ? arr : arr[0]);
		} else {
			obj = map.get(key);
		}
		return obj;
	}

	public String getString(Object key) {
		Object value = get(key);
		return value == null ? "" : (String) value;
	}

	public int getInt(Object key) {
		int i = 0;
		Object object = get(key);
		if (object != null && !"".equals(object)) {
			if (object instanceof Integer) {
				i = (Integer) object;
			} else {
				i = Integer.parseInt(object.toString());
			}
		}
		return i;
	}

	public long getLong(Object key) {
		long i = 0;
		Object object = get(key);
		if (object != null && !"".equals(object)) {
			if (object instanceof Long) {
				i = (Long) object;
			} else if (object instanceof String) {
				i = Long.parseLong(object.toString());
			}
		}
		return i;
	}

	public double getDouble(Object key) {
		double i = 0;
		Object object = get(key);
		if (object != null && !"".equals(object)) {
			if (object instanceof Double) {
				i = (Double) object;
			} else {
				i = Double.parseDouble(object.toString());
			}
		}
		return i;
	}

    public List<Object>  toArrayObject(List<PageData> pd) {
        try {
            List<Object>  list = new ArrayList<>();
            for(PageData data:pd){
                Object ob = data.toObject(Object.class);
                list.add(ob);
            }
            return list;
        }  catch (Exception e){
            throw new DataException("数据转换错误");
        }

    }

	/**
	 * pd转成对象
	 * @param clazz
	 * @return
	 */
	public <T> T toObject(Class<T> clazz) {
		Object value = null;

		Object obj = null;

		try {
			// 创建实例
			obj = clazz.newInstance();
			Field[] f = clazz.getDeclaredFields();
			List<Field[]> flist = new ArrayList<Field[]>();
			flist.add(f);

			Class superClazz = clazz.getSuperclass();
			while (superClazz != null) {
				f = superClazz.getFields();
				flist.add(f);
				superClazz = superClazz.getSuperclass();
			}

			for (Field[] fields : flist) {
				for (Field field : fields) {
					String fieldName = field.getName();
					value = this.get(fieldName);
					if(value==null)
					{
						String first=fieldName.substring(0,1);
						value=this.get(fieldName.replaceFirst(first,first.toUpperCase()));
					}
					if(value==null)
					{
						String first=fieldName.substring(0,1);
						value=this.get(fieldName.replaceFirst(first,first.toLowerCase()));
					}
					if (value != null) {
						Class paramType = field.getType();
						PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
						// 获得set方法
						Method method = pd.getWriteMethod();
						if(value.getClass().getName().equals(paramType.getName()))
						{
							method.invoke(obj, value);
						}
						else {
							method.invoke(obj, ConvertUtil.getValue(value.toString(), fieldName, paramType));
						}						
					}
				}
			}
		} catch (Exception e) {
			Logger.getLogger(PageData.class).error(e, e);
			throw new DataException("数据转换错误");
		}
		return (T) obj;
	}
	
	/**
	 * 对象转成pd
	 * 
	 * @param obj
	 * @return
	 */
	public static PageData objToPd(Object obj) {
		try {
			Class<?> c = obj.getClass();
			Field[] fields = c.getDeclaredFields();
			PageData pageData = new PageData();
			for (Field field : fields) {
				PropertyDescriptor pd = new PropertyDescriptor(field.getName(), c);
				// 获得get方法
				Method get = pd.getReadMethod();
				Object getValue = get.invoke(obj, new Object[] {});
				pageData.put(field.getName(), getValue);
			}
			return pageData;
		} catch (Exception e) {
			Logger.getLogger(PageData.class).error(e, e);
			throw new DataException("数据转换错误");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object put(Object key, Object value) {
		return map.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}

	public void clear() {
		map.clear();
	}

	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return map.containsKey(key);
	}

	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return map.containsValue(value);
	}

	public Set entrySet() {
		// TODO Auto-generated method stub
		return map.entrySet();
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return map.isEmpty();
	}

	public Set keySet() {
		// TODO Auto-generated method stub
		return map.keySet();
	}

	@SuppressWarnings("unchecked")
	public void putAll(Map t) {
		// TODO Auto-generated method stub
		map.putAll(t);
	}

	public int size() {
		// TODO Auto-generated method stub
		return map.size();
	}

	public Collection values() {
		// TODO Auto-generated method stub
		return map.values();
	}

}
