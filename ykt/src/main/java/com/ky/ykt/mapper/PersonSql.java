package com.ky.ykt.mapper;

import com.ky.ykt.mybatis.BaseProvider;
import com.ky.ykt.utils.GetDepartmentSql;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class PersonSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "person";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{"name", "phone", "idCardNo", "projectId",
                "grantAmount", "county", "address", "bankCardNo", "status", "failReason", "departmentId", "puid", "userId"
        };
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("SELECT p.*,ac.cname AS cname,d.departmentName AS departmentName FROM person p LEFT JOIN areas_county ac ON p.county = ac.id LEFT JOIN department d ON d.id = p.departmentId WHERE 1 = 1");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "phone"))) {
            builder.append(" and p.phone = #{phone}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "idCardNo"))) {
            builder.append(" and p.idCardNo = #{idCardNo}");
        }

        if (StringUtils.isNotBlank(MapUtils.getString(map, "projectId"))) {
            builder.append(" and p.projectId = #{projectId}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "userId"))) {
            builder.append(" and p.userId = #{userId}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "name"))) {
            builder.append(" and p.name like concat('%',#{name},'%')");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "bankCardNo"))) {
            builder.append(" and p.bankCardNo = #{bankCardNo}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "status"))) {
            builder.append(" and p.status = #{status}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "record"))) {
            builder.append(" and p.status != 3");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "flag")) && map.get("flag").equals("1")) {
            builder.append(GetDepartmentSql.getUserBuilder("d.departmentId"));
        } else if (StringUtils.isNotBlank(MapUtils.getString(map, "flag")) && map.get("flag").equals("2")) {
            if (StringUtils.isNotBlank(MapUtils.getString(map, "departmentIdListFlag")) && map.get("departmentIdListFlag").equals("departmentIdListFlag")) {
                if (StringUtils.isNotBlank(MapUtils.getString(map, "departmentIdList"))) {
                    builder.append(" and p.departmentId in (");
                    if (map.get("departmentIdList") instanceof List) {
                        List<String> departmentIdList = (List) map.get("departmentIdList");
                        for (String id : departmentIdList) {
                            if (departmentIdList.indexOf(id) > 0)
                                builder.append(",");
                            builder.append("'").append(id).append("'");
                        }
                    } else {
                        builder.append(map.get("departmentIdList"));
                    }
                    builder.append(")");
                }
            } else {
                builder.append(GetDepartmentSql.getUserBuilder("p.departmentId"));
            }
        }
        builder.append(" order by p.updateTime desc");
        return builder.toString();
    }

    public String _queryByPage(Map map) {
        StringBuilder builder = new StringBuilder("SELECT p.*,ac.cname AS cname,d.departmentName AS departmentName,pd.projectName as projectName FROM person p LEFT JOIN areas_county ac ON p.county = ac.id LEFT JOIN department d ON d.id = p.departmentId LEFT JOIN project_detail  pd ON pd.id = p.projectId  WHERE 1 = 1");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "phone"))) {
            builder.append(" and p.phone = #{phone}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "idCardNo"))) {
            builder.append(" and p.idCardNo = #{idCardNo}");
        }

        if (StringUtils.isNotBlank(MapUtils.getString(map, "projectId"))) {
            builder.append(" and p.projectId = #{projectId}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "userId"))) {
            builder.append(" and p.userId = #{userId}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "name"))) {
            builder.append(" and p.name like concat('%',#{name},'%')");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "bankCardNo"))) {
            builder.append(" and p.bankCardNo = #{bankCardNo}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "status"))) {
            builder.append(" and p.status = #{status}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "flag")) && map.get("flag").equals("1")) {
            builder.append(GetDepartmentSql.getUserBuilder("d.departmentId"));
        } else if (StringUtils.isNotBlank(MapUtils.getString(map, "flag")) && map.get("flag").equals("2")) {
            builder.append(GetDepartmentSql.getUserBuilder("p.departmentId"));
        }
        builder.append(" order by p.updateTime desc");
        return builder.toString();
    }

    public String statistics(Map map) {
        StringBuilder builder = new StringBuilder("SELECT\n" +
                "p.`name` AS userName,\n" +
                "p.phone AS phone,\n" +
                "p.idCardNo AS idCardNo,\n" +
                "p.county AS county,\n" +
                "p.address AS address,\n" +
                "pp.projectName AS projectName,\n" +
                "p.`status` AS status,\n" +
                "p.bankCardNo AS bankCardNo,\n" +
                "p.grantAmount AS grantAmount,\n" +
                "pt.`name` AS projectTypeName,\n" +
                "d.departmentName AS departmentName\n" +
                "FROM\n" +
                "person p\n" +
                "LEFT JOIN project_detail pd ON p.projectId = pd.id\n" +
                "LEFT JOIN project pp ON pd.projectId = pp.id\n" +
                "LEFT JOIN project_type pt ON pp.projectType = pt.id\n" +
                "LEFT JOIN department d ON d.id=pd.operDepartment where 1=1 ");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "idCardNo"))) {
            builder.append(" and p.idCardNo = #{idCardNo}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "userName"))) {
            builder.append(" and p.name = #{userName}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "projectType"))) {
            builder.append(" and pp.projectType = #{projectType}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "operDepartment"))) {
            builder.append(" and pd.operDepartment = #{operDepartment}");
        }
        String startTime = "";
        String endTime = "";
        if (!StringUtils.isBlank(MapUtils.getString(map, "startTime"))) {
            startTime = dealStartEndDate(map.get("startTime").toString(), "startTime");
        }
        if (!StringUtils.isBlank(MapUtils.getString(map, "endTime"))) {
            endTime = dealStartEndDate(map.get("endTime").toString(), "endTime");
        }
        if (!startTime.equals("") || !endTime.equals("")) {
            builder.append(" and  p.createTime between '" + startTime + "' and '" + endTime + "' ");
        }
        builder.append(this.pageHelp(MapUtils.getLongValue(map, "page"), MapUtils.getLongValue(map, "rows")));
        return builder.toString();
    }

    public String statisticsCount(Map map) {
        StringBuilder builder = new StringBuilder("SELECT\n" +
                "COUNT(*) FROM\n" +
                "person p\n" +
                "LEFT JOIN project_detail pd ON p.projectId = pd.id\n" +
                "LEFT JOIN project pp ON pd.projectId = pp.id\n" +
                "LEFT JOIN project_type pt ON pp.projectType = pt.id\n" +
                "LEFT JOIN department d ON d.id=pd.operDepartment  where 1=1 ");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "idCardNo"))) {
            builder.append(" and p.idCardNo = #{idCardNo}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "userName"))) {
            builder.append(" and p.name = #{userName}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "projectType"))) {
            builder.append(" and pp.projectType = #{projectType}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "operDepartment"))) {
            builder.append(" and pd.operDepartment = #{operDepartment}");
        }
        String startTime = "";
        String endTime = "";
        if (!StringUtils.isBlank(MapUtils.getString(map, "startTime"))) {
            startTime = dealStartEndDate(map.get("startTime").toString(), "startTime");
        }
        if (!StringUtils.isBlank(MapUtils.getString(map, "endTime"))) {
            endTime = dealStartEndDate(map.get("endTime").toString(), "endTime");
        }
        if (!startTime.equals("") || !endTime.equals("")) {
            builder.append(" and  p.createTime between '" + startTime + "' and '" + endTime + "' ");
        }
        return builder.toString();
    }

    public StringBuilder pageHelp(long currentPage, long pageSize) {
        long count = (currentPage - 1) * pageSize;
        if (count != 0) {
            count = count;
        }
        StringBuilder builder = new StringBuilder(" limit ");
        builder.append(count);
        builder.append(" ,");
        builder.append(pageSize);
        return builder;
    }

    private String dealStartEndDate(String date, String type) {
        if (type.contains("start")) {
            return date + " 00:00:00";
        }
        if (type.contains("end")) {
            return date + " 23:59:59";
        }
        return date;
    }

}
