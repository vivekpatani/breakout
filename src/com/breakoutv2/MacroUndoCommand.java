package com.breakoutv2;

import java.util.ArrayList;

public class MacroUndoCommand implements Command{
	private ArrayList<Command> commands;
	
	public MacroUndoCommand() {
		// TODO connect to the the button
		this.commands = new ArrayList<Command>();
	}
	
	// add a command to the macro
	public void add(Command command) {
		this.commands.add(command);
	}
	
	//saves all commands (history of moves)
	public void macroSave() {
		for(int i = 0; i < commands.size(); i++) {
			commands.get(i).save();
		}
	}
	
	// in case the command is not necessary, remove it from the list
	public void remove(Command command) {
		int i = this.commands.indexOf(command);
		if(i > 0) {
			this.commands.remove(i);
		}
	}
	@Override
	public void execute(int dx, int dy) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void undo() {
		for(int i = 0; i < commands.size(); i++) {
			commands.get(i).undo();
		}
	}

	@Override
	public void save() {
		macroSave();
	}

}
