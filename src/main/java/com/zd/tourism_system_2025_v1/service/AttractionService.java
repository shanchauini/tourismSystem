package com.zd.tourism_system_2025_v1.service;

import com.zd.tourism_system_2025_v1.dao.AttractionDao;
import com.zd.tourism_system_2025_v1.model.Attraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AttractionService {
    //处理景点业务逻辑

    private final AttractionDao attractionDao;

    @Autowired
    public AttractionService(AttractionDao attractionDao) {
        this.attractionDao = attractionDao;
    }

    //添加新景点
    public boolean addAttraction(Attraction attraction) {
        try {
            if (validateAttractionInfo(attraction) == false) {
                System.out.println("Attraction Info is not valid");
                return false;
            }
            int result = attractionDao.addAttraction(attraction);
            return result > 0; //判断添加是否成功
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //更新景点信息
    public boolean updateAttraction(Attraction attraction) {
        try {
            if (validateAttractionInfo(attraction) == false) {
                System.out.println("Attraction Info is not valid");
                return false;
            }
            int result = attractionDao.updateAttraction(attraction);
            return result > 0; //判断更新是否成功
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //删除景点
    public boolean deleteAttraction(int attractionID) {
        try {
            int result = attractionDao.deleteAttraction(attractionID);
            return result > 0; //判断删除是否成功
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //获取景点详情
    public Attraction getAttractionById(int attractionID) {
        try {
            return attractionDao.getAttractionById(attractionID);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //获取所有景点
    public List<Attraction> getAllAttractions() {
        try {
            return attractionDao.getAllAttractions();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    //搜索景点
    //这个筛选是并集而非交集
    public List<Attraction> searchAttractions(String name, String address, String level) {
        try {
            //List<Attraction> results = new ArrayList<>();
            /*
            if (name != null && !name.isEmpty()) {
                results.addAll(attractionDao.getAttractionsByName(name));
            }
            if (address != null && !address.isEmpty()) {
                results.addAll(attractionDao.getAttractionsByAddress(address));
            }
            if (level != null && !level.isEmpty()) {
                results.addAll(attractionDao.getAttractionsByLevel(level));
            }
            
            // 如果所有参数都为空，返回所有景点
            if (name == null && address == null && level == null) {
                results.addAll(attractionDao.getAllAttractions());
            }*/
            //results.addAll(attractionDao.getAttractionsBySearch(name, address, level));
            //return results;
            return attractionDao.getAttractionsBySearch(name, address, level);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    //验证景点信息
    private boolean validateAttractionInfo(Attraction attraction) {
        //判断景点信息是否有效
        if (attraction.getAttractionName() == null || attraction.getAttractionName().isEmpty()) {
            return false;
        }
        if (attraction.getAttractionAddress() == null || attraction.getAttractionAddress().isEmpty()) {
            return false;
        }
        if (attraction.getAttractionLevel() == null || attraction.getAttractionLevel().isEmpty()) {
            return false;
        }
        if (attraction.getOpeningHours() == null || attraction.getOpeningHours().isEmpty()) {
            return false;
        }
        if (attraction.getIntroduction() == null || attraction.getIntroduction().isEmpty()) {
            return false;
        }
        return true;
    }
} 