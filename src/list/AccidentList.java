package list;

import java.util.ArrayList;

import entity.Accident;

public interface AccidentList {
	public boolean add(Accident accident);
	public boolean delete(Accident accident);
	public Accident search(int accidentNum);
	public ArrayList<Accident> getAccidentList();
}