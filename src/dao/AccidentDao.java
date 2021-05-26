package dao;

import java.util.ArrayList;

import entity.Accident;

public interface AccidentDao {
	public boolean add(Accident accident);
	public boolean delete(Accident accident);
	public Accident search(int accidentNum);
	public ArrayList<Accident> findAll();
}