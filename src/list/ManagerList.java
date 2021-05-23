package list;
import entity.Manager;
public interface ManagerList{
	public boolean add(Manager manager);
	public boolean delete(Manager manager);
	public Manager search(String managerID);
}
