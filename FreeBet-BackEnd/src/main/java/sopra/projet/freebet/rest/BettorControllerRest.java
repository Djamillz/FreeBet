package sopra.projet.freebet.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import sopra.projet.freebet.exception.NotFoundException;
import sopra.projet.freebet.model.Bettor;
import sopra.projet.freebet.model.Civility;
import sopra.projet.freebet.model.Login;
import sopra.projet.freebet.model.Views;
import sopra.projet.freebet.repository.IBettorRepository;
import sopra.projet.freebet.repository.ILoginRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/bettor")

public class BettorControllerRest {

	@Autowired
	private IBettorRepository bettorRepo;

	@Autowired
	private ILoginRepository loginRepo;

	@GetMapping("")
	@JsonView(Views.ViewBettor.class)
	public List<Bettor> list() {
		List<Bettor> bettors = bettorRepo.findAll();

		return bettors;
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewBettorDetail.class)
	public Bettor find(@PathVariable Long id) {
		Optional<Bettor> opt = bettorRepo.findById(id);

		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new NotFoundException();
		}
	}

	@PostMapping("")
	@JsonView(Views.ViewBettor.class)
	public Bettor create(@RequestBody Bettor bettor) {
		Login login = loginRepo.save(bettor.getLogin());
		bettor.setLogin(login);
		bettor = bettorRepo.save(bettor);

		return bettor;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewBettor.class)
	public Bettor update(@RequestBody Bettor bettor, @PathVariable Long id) {
		Login login = loginRepo.save(bettor.getLogin());
		bettor.setLogin(login);
		bettor = bettorRepo.save(bettor);

		return bettor;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		bettorRepo.deleteById(id);
	}

	@GetMapping("/civilites")
	public Civility[] civilite() {
		return Civility.values();
	}

}