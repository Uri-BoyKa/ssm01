package sz.hh.dao;

import java.util.Map;

public interface StoreHouseDao {
/*������ʱҪʵ�ֵĲ���
 * 1.�����鼮���,��ѯ�鼮�������  
 * 2.���������һ
 * 3.��ѯ�˻����	��AccountDao���ѯ
 * 4.�����鼮��ţ���ѯ�鼮�۸�	��BookDao���ѯ
 * 5.�˻�������	��AccountDao�����
 */
	//���������һ
	public void countplusone(Integer bookid);
	
	//�����鼮��ţ���ѯ�������
	public Integer findCountByBookid(Integer bookid);
	
}
