package sz.hh.dao;

import java.util.Map;

public interface StoreHouseDao {
/*购买书时要实现的步骤
 * 1.根据书籍编号,查询书籍库存数量  
 * 2.库存数量减一
 * 3.查询账户余额	在AccountDao里查询
 * 4.根据书籍编号，查询书籍价格	在BookDao里查询
 * 5.账户余额减少	在AccountDao里减少
 */
	//库存数量减一
	public void countplusone(Integer bookid);
	
	//根据书籍编号，查询库存数量
	public Integer findCountByBookid(Integer bookid);
	
}
