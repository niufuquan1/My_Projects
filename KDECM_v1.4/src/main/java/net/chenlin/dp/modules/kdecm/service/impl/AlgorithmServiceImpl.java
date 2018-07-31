package net.chenlin.dp.modules.kdecm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chenlin.dp.modules.kdecm.manager.AlgorithmManager;
import net.chenlin.dp.modules.kdecm.service.AlgorithmService;

@Service("AlgorithmService")
public class AlgorithmServiceImpl implements AlgorithmService {

	@Autowired
	private AlgorithmManager algorithmManager; 
	@Override
	public List<String> getHerbalName() {
		// TODO Auto-generated method stub
		List<String> herbalName_list = algorithmManager.getHerbalName();
		return herbalName_list;
	}
	
	public List<String> getPathemaName(){
		List<String> pathema_list = algorithmManager.getPathemaName();
		return pathema_list;
	}

}
