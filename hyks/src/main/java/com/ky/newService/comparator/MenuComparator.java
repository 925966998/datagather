package com.ky.newService.comparator;


import com.ky.newService.entity.MenuEntity;

import java.util.Comparator;

public class MenuComparator implements Comparator<MenuEntity> {
    @Override
    public int compare(MenuEntity o1, MenuEntity o2) {
        if (o1.getMenuSort() > o2.getMenuSort()) {
            return 1;
        } else {
            return -1;
        }
    }
}
