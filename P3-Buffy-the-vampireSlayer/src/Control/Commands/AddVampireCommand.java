package Control.Commands;

import Exception.CommandExecuteException;
import Exception.CommandParseException;
import Logic.Game;

public class AddVampireCommand extends Command{
	private String type;
	private int x;
	private int y;
	private String arg2;
	private String arg3;
	public AddVampireCommand() {
		super("V", "v", "[v]ampire","[<type>] <x> <y>. Type = {\"\"|\"D\"|\"E\"}: add a vampire in position x, y");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game)throws CommandExecuteException {
		boolean ok=false;
		try {
			if(this.type.equalsIgnoreCase("v")) {
				 game.addVampire(x, y);
				 ok=true;
			}
			else if(this.type.equalsIgnoreCase("D")) {
				game.addDracula(x, y);
				ok=true;
			}
			else if(this.type.equalsIgnoreCase("E")) {
				game.addExplosiveVampire(x, y);
				ok=true;
			}
			return ok;
			//else System.out.println("[ERROR]: Invalid type");
		
		}
		catch (CommandExecuteException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			throw new CommandExecuteException ("[ERROR]: Failed to add "+this.name );
		}
		
		
//		switch (game.getErrorAddVampire()) {		
//		case 0:
////			//System.out.println("[DEBUG] Executing: "+this.arg+" "+x+" "+y+"0");
//			System.out.println("[ERROR]: Invalid position");
//			 return ok=false;
//		case 1:
//			System.out.println("[ERROR]: Not enough coins");
//			return ok=false;
//		case 2:
//			//System.out.println("[DEBUG] Executing: "+this.arg+" "+x+" "+y+"1");
//			System.out.println("[ERROR]: Dracula is already alive");
//			 return ok=false;
//		default: return ok;
//		}
	}

	@Override
	public Command parse(String[] commandWords)throws CommandParseException {
		
		try {
		if(matchCommandName(commandWords[0])) {
			if(commandWords.length==4) {//||this.type.equalsIgnoreCase("D")||this.type.equalsIgnoreCase("E")) {
				this.type=commandWords[1];
				if(commandWords[1].equalsIgnoreCase("D")||commandWords[1].equalsIgnoreCase("E")||commandWords[1].equalsIgnoreCase("")) {
					this.arg2=commandWords[2];
					this.arg3=commandWords[3];
					if(validaNUmeros(this.arg2)&&validaNUmeros(this.arg3))
					this.type=commandWords[1];
					this.x=Integer.parseInt(commandWords[2]);
					this.y=Integer.parseInt(commandWords[3]);
					return this;
				}
				else throw new CommandParseException("[Error]: [<type>] <x> <y>. Type = {\" \"|\"D\"|\"E\"}: add a vampire in position x, y");
			}
		}
		if (matchCommandName(commandWords[0])) {
			if(commandWords.length==3){
				this.arg2=commandWords[1];
				this.arg3=commandWords[2];
				if(validaNUmeros(this.arg2)&&validaNUmeros(this.arg3)) {
					this.type=commandWords[0];
					this.x=Integer.parseInt(commandWords[1]);
					this.y=Integer.parseInt(commandWords[2]);
					return this;
				}
			}
			else throw new CommandParseException("[Error]: [<type>] <x> <y>. Type = {\" \"|\"D\"|\"E\"}: add a vampire in position x, y");
		}
		//else throw new CommandParseException("[Error]: [<type>] <x> <y>. Type = {\"\"|\"D\"|\"E\"}: add a vampire in position x, y");
		}
		catch (CommandParseException e) {
			System.out.println(e.getMessage());
			//throw new CommandParseException("[Error]: [<type>] <x> <y>. Type = {\\\"\\\"|\\\"D\\\"|\\\"E\\\"}: add a vampire in position x, y");
		}
		return null;
	}
	
	public boolean validaNUmeros(String arg) {
		return arg.matches("[0-9]");
	}

}
