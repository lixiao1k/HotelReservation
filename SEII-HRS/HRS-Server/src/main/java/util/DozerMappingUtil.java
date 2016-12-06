package util;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

public class DozerMappingUtil {
	private static DozerBeanMapper mapper;
	static{
		try{
			mapper = new DozerBeanMapper();
			load();
		}catch(Throwable e){
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);	
		}
	}
	public static Mapper getInstance(){
		return mapper;
	}
	/*
	 * 初始化，加载文件
	 */
	private static void load(){
		List mappingFiles = new ArrayList();
		mappingFiles.add("dozer-mapping.xml");
		mapper.setMappingFiles(mappingFiles);
	}

	
}
