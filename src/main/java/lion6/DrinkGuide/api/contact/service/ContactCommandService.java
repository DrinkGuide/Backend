package lion6.DrinkGuide.api.contact.service;

import lion6.DrinkGuide.api.contact.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ContactCommandService {
    private final ContactRepository contactRepository;

    public void createContact(Long memberId, String title, String content) {

    }
}
