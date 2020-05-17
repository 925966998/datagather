package com.ky.ykt.entity;

import com.ky.ykt.mybatis.BaseEntity;

/**
 * @ClassName ProjectStandardEntity
 * @Description: TODO
 * @Author czw
 * @Date 2020/5/17
 **/
public class ProjectStandardEntity extends BaseEntity {
    private String name;
    private String note;
    private String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
