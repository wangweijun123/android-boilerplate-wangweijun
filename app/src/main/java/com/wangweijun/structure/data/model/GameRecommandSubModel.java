package com.wangweijun.structure.data.model;

import java.io.Serializable;
import java.util.List;

public class GameRecommandSubModel implements Serializable {
	private static final long serialVersionUID = 2523932959612718017L;
	public String code;
	public String img;
	public List<BaseModel> items;
	public String name;
	public String subname;
	public int total;
	

	
}
