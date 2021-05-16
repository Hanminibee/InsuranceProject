package list;

import java.util.ArrayList;

import entity.Accident;

public class AccidentListImpl implements AccidentList{

	private ArrayList<Accident> accidentList;
	
	public AccidentListImpl() {
		this.accidentList = new ArrayList<Accident>();
	}
	
	@Override
	public boolean add(Accident accident) {
		return accidentList.add(accident);
	}

	@Override
	public boolean delete(Accident accident) {
		return accidentList.remove(accident);
	}

	@Override
	public Accident search(int accidentNum) {
		for(Accident accident : accidentList) {
			if(accident.getAccidentNum() == accidentNum)
				return accident;
		}
		return null;
	}
	
	public ArrayList<Accident> getAccidentList(){
		return this.accidentList;
	}
}
