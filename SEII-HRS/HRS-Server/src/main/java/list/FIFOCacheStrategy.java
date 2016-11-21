package list;

import java.util.Set;

public class FIFOCacheStrategy implements CacheStrategy{

	@Override
	public void replace(Set datas, Object data) {
		datas.remove(0);
		datas.add(data);
	}

}
