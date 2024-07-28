package lion6.DrinkGuide.api.contact.repository;

import lion6.DrinkGuide.api.contact.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
