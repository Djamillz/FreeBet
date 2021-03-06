package sopra.projet.freebet.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "Bet")
public class Bet {

	@Id
	@GeneratedValue
	@JsonView(Views.ViewCommon.class)
	private Long id;
	@Version
	@JsonView(Views.ViewCommon.class)
	private int version;

	@Column(name = "stake")
	@JsonView(Views.ViewCommon.class)
	private Float mise;
	@Column(name = "bet_result")
	@JsonView(Views.ViewCommon.class)
	private Boolean resultatPari;
	@Enumerated(EnumType.STRING)
	@Column(name = "type_bet")
	@JsonView(Views.ViewCommon.class)
	private TypeBet type;
	@Temporal(TemporalType.DATE)
	@Column(name = "bet_date")
	@JsonView(Views.ViewCommon.class)
	private Date betDate;
	@Column(name = "gain")
	@JsonView(Views.ViewCommon.class)
	private Float gain;

	@ManyToMany
	@JoinTable(name = "Bet_Game", joinColumns = {
			@JoinColumn(name = "BET_ID") }, inverseJoinColumns = @JoinColumn(name = "GAME_ID"))
	@JsonView(Views.ViewBet.class)
	private List<Game> games = new ArrayList<Game>();

	@ManyToOne
	@JoinColumn(name = "bettor_id")
	@JsonView(Views.ViewBet.class)
	private Bettor bettorr;

	public Bet() {
		super();
	}

	public Bet(Float mise, boolean resultatPari, TypeBet type, Date date, Float gain) {
		super();
		this.mise = mise;
		this.resultatPari = resultatPari;
		this.type = type;
		this.betDate = date;
		this.gain = gain;
	}

	public Bet(Float mise, Boolean resultatPari, TypeBet type, Date betDate, Float gain, List<Game> games) {
		super();
		this.mise = mise;
		this.resultatPari = resultatPari;
		this.type = type;
		this.betDate = betDate;
		this.gain = gain;
		this.games = games;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Float getMise() {
		return mise;
	}

	public void setMise(Float mise) {
		this.mise = mise;
	}

	public Boolean getResultatPari() {
		return resultatPari;
	}

	public void setResultatPari(Boolean resultatPari) {
		this.resultatPari = resultatPari;
	}

	public TypeBet getType() {
		return type;
	}

	public void setType(TypeBet type) {
		this.type = type;
	}

	public Date getBetDate() {
		return betDate;
	}

	public void setBetDate(Date betDate) {
		this.betDate = betDate;
	}

	public Float getGain() {
		return gain;
	}

	public void setGain(Float gain) {
		this.gain = gain;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public Bettor getBettorr() {
		return bettorr;
	}

	public void setBettorr(Bettor bettor) {
		this.bettorr = bettor;
	}

}
