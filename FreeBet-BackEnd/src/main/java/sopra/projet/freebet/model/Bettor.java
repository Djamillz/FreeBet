package sopra.projet.freebet.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "Bettor")
public class Bettor {

	@Id
	@GeneratedValue
	private Long id;
	@Version
	private int version;

	@Column(name = "lastname", length = 100)
	private String nom;

	@Column(name = "firstname", length = 100)
	private String prenom;

	@Column(name = "date_birth", length = 50)
	private Date ddn;

	@Column(name = "money_balance", length = 100)
	private Float soldeCagnotte;

	@Enumerated(EnumType.STRING)
	@Column(name = "civility")
	private Civility civilite;

	@Embedded
	@Column(name = "adress")
	private Adress adress;

	@OneToOne
	private Login login;

	@OneToMany(mappedBy = "bettorr")
	List<Bet> bets;

	public Bettor() {
		super();
	}

	public Bettor(Long id, int version, String nom, String prenom, Date ddn, Float soldecagnotte, Civility civilite,
			Adress adress, Login login) {
		super();
		this.id = id;
		this.version = version;
		this.nom = nom;
		this.prenom = prenom;
		this.ddn = ddn;
		this.soldeCagnotte = soldecagnotte;
		this.civilite = civilite;
		this.adress = adress;
		this.login = login;
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

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDdn() {
		return ddn;
	}

	public void setDdn(Date ddn) {
		this.ddn = ddn;
	}

	public Float getSoldecagnotte() {
		return soldeCagnotte;
	}

	public void setSoldecagnotte(Float soldecagnotte) {
		this.soldeCagnotte = soldecagnotte;
	}

	public Civility getCivilite() {
		return civilite;
	}

	public void setCivilite(Civility civilite) {
		this.civilite = civilite;
	}

	public Adress getAdress() {
		return adress;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

}
