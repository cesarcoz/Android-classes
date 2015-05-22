package com.jappanigga.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jappanigga.dto.Team;
import com.jappanigga.dto.TeamPlayer;

@Path("/tournament")
public class Tournament {

	private Team argentina;
	private Team uruguay;
	private Team brazil;
	private Team paraguay;

	@GET
	@Path("/team")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Team> getTeams() {
		List<Team> teams = new ArrayList<Team>();
		teams.add(argentina);
		teams.add(uruguay);
		teams.add(brazil);
		teams.add(paraguay);
		return teams;
	}
	
	@GET
	@Path("/team/{idTeam}")
	@Produces(MediaType.APPLICATION_JSON)
	public Team getTeam(@PathParam("idTeam") int idTeam) {
		switch (idTeam) {
		case 1:
			return argentina;
		case 2:
			return uruguay;
		case 3:
			return brazil;
		case 4:
			return paraguay;
		default:
			return null;
		}
	}

	@GET
	@Path("/player")
	@Produces(MediaType.APPLICATION_JSON)
	public TeamPlayer getPlayer() {
		TeamPlayer player = new TeamPlayer();
		player.setFirstName("");
		player.setId(1);
		player.setLastName("");
		return player;
	}

	public Tournament() {
		setArgentina();
		setUruguay();
		setBrazil();
		setParaguay();
	}
	

	private void setParaguay() {
		List<TeamPlayer> players = new ArrayList();
		players.add(new TeamPlayer(1,"Justo","Villar"));
		players.add(new TeamPlayer(2,"Ivan","Piris"));
		players.add(new TeamPlayer(3,"Marcos","Caceres"));
		players.add(new TeamPlayer(4,"Pablo","Aguilar"));
		players.add(new TeamPlayer(5,"Gustavo","Gomez"));
		players.add(new TeamPlayer(6,"Osmar","Molinas"));
		players.add(new TeamPlayer(7,"Raul","Bobadilla"));
		players.add(new TeamPlayer(8,"Lucas","Barrios"));
		players.add(new TeamPlayer(9,"Roque","Santa Cruz"));
		players.add(new TeamPlayer(10,"Oscar","Romero"));
		players.add(new TeamPlayer(11,"Edgard","Benites"));
		paraguay = new Team(players,4,"Paraguay");
	}

	private void setBrazil() {
		List<TeamPlayer> players = new ArrayList();
		players.add(new TeamPlayer(1,"Diego","Alvez"));
		players.add(new TeamPlayer(2,"Danilo",""));
		players.add(new TeamPlayer(3,"Thiago","Silva"));
		players.add(new TeamPlayer(4,"Miranda",""));
		players.add(new TeamPlayer(5,"Fernandinho",""));
		players.add(new TeamPlayer(6,"Filipe","Luis"));
		players.add(new TeamPlayer(7,"Robinho",""));
		players.add(new TeamPlayer(8,"Casemiro",""));
		players.add(new TeamPlayer(9,"Diego","Tardelli"));
		players.add(new TeamPlayer(10,"Neymar","Jr"));
		players.add(new TeamPlayer(11,"Everton","Ribeiro"));
		brazil = new Team(players,3,"Brazil");
	}

	private void setUruguay() {
		List<TeamPlayer> players = new ArrayList();
		players.add(new TeamPlayer(1,"Fernando","Muslera"));
		players.add(new TeamPlayer(2,"José María","Giménez"));
		players.add(new TeamPlayer(3,"Diego","Godín"));
		players.add(new TeamPlayer(4,"Emiliano","Velazquez"));
		players.add(new TeamPlayer(5,"Walter","Gargano"));
		players.add(new TeamPlayer(6,"Alvaro","Pereira"));
		players.add(new TeamPlayer(7,"Christian","Rodriguez"));
		players.add(new TeamPlayer(8,"Abel","Hernandez"));
		players.add(new TeamPlayer());
		players.add(new TeamPlayer(10,"Giorgian","De Arrascaeta"));
		players.add(new TeamPlayer(11,"Christian","Stuani"));
		uruguay = new Team(players,2,"Uruguay");
	}

	private void setArgentina() {
		List<TeamPlayer> playersArgentina = new ArrayList();
		playersArgentina.add(new TeamPlayer(1,"Sergio","Romero"));
		playersArgentina.add(new TeamPlayer(2,"Ezequiel","Garay"));
		playersArgentina.add(new TeamPlayer(3,"Nicolás","Otamendi"));
		playersArgentina.add(new TeamPlayer(4,"Pablo","Zabaleta"));
		playersArgentina.add(new TeamPlayer(5,"Fernando","Gago"));
		playersArgentina.add(new TeamPlayer(6,"Lucas","Biglia"));
		playersArgentina.add(new TeamPlayer(7,"Ángel","Di María"));
		playersArgentina.add(new TeamPlayer(8,"Enzo","Pérez"));
		playersArgentina.add(new TeamPlayer(9,"Gonzalo","Higuaín"));
		playersArgentina.add(new TeamPlayer(10,"Lionel","Messi"));
		playersArgentina.add(new TeamPlayer(11,"Sergio","Agüero"));
		argentina = new Team(playersArgentina,1,"Argentina");
	}

}
