package net.chenlin.dp.modules.kdecm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AlgorithmMapper {
	List<String> getHerbalName();
	List<String> getPathemaName();
 }
