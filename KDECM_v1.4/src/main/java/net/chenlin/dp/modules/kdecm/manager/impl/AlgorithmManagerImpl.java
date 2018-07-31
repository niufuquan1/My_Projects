package net.chenlin.dp.modules.kdecm.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.chenlin.dp.modules.kdecm.dao.AlgorithmMapper;
import net.chenlin.dp.modules.kdecm.manager.AlgorithmManager;

@Component("AlgorithmManager")
public class AlgorithmManagerImpl implements AlgorithmManager {

	@Autowired
	private AlgorithmMapper algorithmMapper;
	@Override
	public List<String> getHerbalName() {
		// TODO Auto-generated method stub
		List<String> herbalName_list = algorithmMapper.getHerbalName();
		return herbalName_list;
	}
	
	public List<String> getPathemaName(){
		List<String> pathema_list = algorithmMapper.getPathemaName();
		return pathema_list;
	}

}
