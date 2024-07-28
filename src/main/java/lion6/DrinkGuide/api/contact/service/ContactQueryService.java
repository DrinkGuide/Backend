package lion6.DrinkGuide.api.contact.service;

import lion6.DrinkGuide.api.contact.dto.response.ContactGetAllResponseDto;
import lion6.DrinkGuide.api.contact.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactQueryService {
    private final ContactRepository contactRepository;

    public List<ContactGetAllResponseDto> getContacts(){
        return null;
    }
}
