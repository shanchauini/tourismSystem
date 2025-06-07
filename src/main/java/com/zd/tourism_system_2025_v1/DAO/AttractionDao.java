package com.zd.tourism_system_2025_v1.dao;

import com.zd.tourism_system_2025_v1.Util.DBUtil;
import com.zd.tourism_system_2025_v1.model.Attraction;

import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AttractionDao {

    private final DBUtil dbUtil;

    @Autowired
    public AttractionDao(DBUtil dbUtil) {
        this.dbUtil = dbUtil;
    }

    // 添加新景点
    public int addAttraction(Attraction attraction) throws SQLException {
        String sql = "INSERT INTO attraction (attractionName, attractionLevel, attractionAddress, openingHours, introduction, coverImage) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, attraction.getAttractionName());
            pstmt.setString(2, attraction.getAttractionLevel());
            pstmt.setString(3, attraction.getAttractionAddress());
            pstmt.setString(4, attraction.getOpeningHours());
            pstmt.setString(5, attraction.getIntroduction());
            pstmt.setString(6, attraction.getCoverImage());

            return pstmt.executeUpdate();
        } finally {
            dbUtil.close(conn, pstmt, null);
        }
    }

    // 更新景点信息
    public int updateAttraction(Attraction attraction) throws SQLException {
        String sql = "UPDATE attraction SET attractionName=?, attractionLevel=?, attractionAddress=?, openingHours=?, introduction=?, coverImage=? WHERE attractionID=?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, attraction.getAttractionName());
            pstmt.setString(2, attraction.getAttractionLevel());
            pstmt.setString(3, attraction.getAttractionAddress());
            pstmt.setString(4, attraction.getOpeningHours());
            pstmt.setString(5, attraction.getIntroduction());
            pstmt.setString(6, attraction.getCoverImage());
            pstmt.setInt(7, attraction.getAttractionID());

            return pstmt.executeUpdate();
        } finally {
            dbUtil.close(conn, pstmt, null);
        }
    }

    // 删除景点
    public int deleteAttraction(int attractionID) throws SQLException {
        String sql = "DELETE FROM attraction WHERE attractionID=?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, attractionID);

            return pstmt.executeUpdate();
        } finally {
            dbUtil.close(conn, pstmt, null);
        }
    }

    // 根据ID查询景点
    public Attraction getAttractionById(int attractionID) throws SQLException {
        String sql = "SELECT * FROM attraction WHERE attractionID=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Attraction attraction = null;

        try {
            conn = dbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, attractionID);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                attraction = new Attraction();
                attraction.setAttractionID(rs.getInt("attractionID"));
                attraction.setAttractionName(rs.getString("attractionName"));
                attraction.setAttractionLevel(rs.getString("attractionLevel"));
                attraction.setAttractionAddress(rs.getString("attractionAddress"));
                attraction.setOpeningHours(rs.getString("openingHours"));
                attraction.setIntroduction(rs.getString("introduction"));
                attraction.setCoverImage(rs.getString("coverImage"));
            }
            return attraction;
        } finally {
            dbUtil.close(conn, pstmt, rs);
        }
    }

    // 查询所有景点
    public List<Attraction> getAllAttractions() throws SQLException {
        String sql = "SELECT * FROM attraction";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Attraction> attractions = new ArrayList<>();

        try {
            conn = dbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Attraction attraction = new Attraction();
                attraction.setAttractionID(rs.getInt("attractionID"));
                attraction.setAttractionName(rs.getString("attractionName"));
                attraction.setAttractionLevel(rs.getString("attractionLevel"));
                attraction.setAttractionAddress(rs.getString("attractionAddress"));
                attraction.setOpeningHours(rs.getString("openingHours"));
                attraction.setIntroduction(rs.getString("introduction"));
                attraction.setCoverImage(rs.getString("coverImage"));
                attractions.add(attraction);
            }
            return attractions;
        } finally {
            dbUtil.close(conn, pstmt, rs);
        }
    }

    public List<Attraction> getAttractionsBySearch(String name,String address,String level)
        throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;


        StringBuilder sql = new StringBuilder("SELECT * FROM attraction WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (StringUtils.isNotBlank(name)) {
            sql.append(" AND attractionName LIKE ?");
            params.add("%" + name + "%");
        }
        if (StringUtils.isNotBlank(address)) {
            sql.append(" AND attractionAddress LIKE ?");
            params.add("%" + address + "%");
        }
        if (StringUtils.isNotBlank(level)) {
            sql.append(" AND attractionLevel = ?");
            params.add("%"+ level+"%"); // 注意level字段不应加%通配符
        }

        try {
                conn = dbUtil.getConnection();
                pstmt = conn.prepareStatement(sql.toString());

            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }

            rs = pstmt.executeQuery();

            List<Attraction> attractions = new ArrayList<>();
            while (rs.next()) {
                Attraction attraction = new Attraction();
                attraction.setAttractionID(rs.getInt("attractionID"));
                attraction.setAttractionName(rs.getString("attractionName"));
                attraction.setAttractionLevel(rs.getString("attractionLevel"));
                attraction.setAttractionAddress(rs.getString("attractionAddress"));
                attraction.setOpeningHours(rs.getString("openingHours"));
                attraction.setIntroduction(rs.getString("introduction"));
                attraction.setCoverImage(rs.getString("coverImage"));
                attractions.add(attraction);
            }
            return attractions;
        }finally {
            dbUtil.close(conn, pstmt, rs);
        }

    }
    /*
    // 根据名称查询景点
    public List<Attraction> getAttractionsByName(String name) throws SQLException {
        String sql = "SELECT * FROM attraction WHERE attractionName LIKE ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Attraction> attractions = new ArrayList<>();

        try {
            conn = dbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + name + "%");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Attraction attraction = new Attraction();
                attraction.setAttractionID(rs.getInt("attractionID"));
                attraction.setAttractionName(rs.getString("attractionName"));
                attraction.setAttractionLevel(rs.getString("attractionLevel"));
                attraction.setAttractionAddress(rs.getString("attractionAddress"));
                attraction.setOpeningHours(rs.getString("openingHours"));
                attraction.setIntroduction(rs.getString("introduction"));
                attraction.setCoverImage(rs.getString("coverImage"));
                attractions.add(attraction);
            }
            return attractions;
        } finally {
            dbUtil.close(conn, pstmt, rs);
        }
    }

    // 根据地址查询景点
    public List<Attraction> getAttractionsByAddress(String address) throws SQLException {
        String sql = "SELECT * FROM attraction WHERE attractionAddress LIKE ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Attraction> attractions = new ArrayList<>();

        try {
            conn = dbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + address + "%");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Attraction attraction = new Attraction();
                attraction.setAttractionID(rs.getInt("attractionID"));
                attraction.setAttractionName(rs.getString("attractionName"));
                attraction.setAttractionLevel(rs.getString("attractionLevel"));
                attraction.setAttractionAddress(rs.getString("attractionAddress"));
                attraction.setOpeningHours(rs.getString("openingHours"));
                attraction.setIntroduction(rs.getString("introduction"));
                attraction.setCoverImage(rs.getString("coverImage"));
                attractions.add(attraction);
            }
            return attractions;
        } finally {
            dbUtil.close(conn, pstmt, rs);
        }
    }

    // 根据星级查询景点
    public List<Attraction> getAttractionsByLevel(String level) throws SQLException {
        String sql = "SELECT * FROM attraction WHERE attractionLevel=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Attraction> attractions = new ArrayList<>();

        try {
            conn = dbUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + level + "%");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Attraction attraction = new Attraction();
                attraction.setAttractionID(rs.getInt("attractionID"));
                attraction.setAttractionName(rs.getString("attractionName"));
                attraction.setAttractionLevel(rs.getString("attractionLevel"));
                attraction.setAttractionAddress(rs.getString("attractionAddress"));
                attraction.setOpeningHours(rs.getString("openingHours"));
                attraction.setIntroduction(rs.getString("introduction"));
                attraction.setCoverImage(rs.getString("coverImage"));
                attractions.add(attraction);
            }
            return attractions;
        } finally {
            dbUtil.close(conn, pstmt, rs);
        }
    }*/
} 