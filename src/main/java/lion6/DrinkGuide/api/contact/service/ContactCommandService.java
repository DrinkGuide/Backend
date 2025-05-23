package lion6.DrinkGuide.api.contact.service;

import lion6.DrinkGuide.api.contact.domain.Contact;
import lion6.DrinkGuide.api.contact.dto.request.ContactCreateRequestDto;
import lion6.DrinkGuide.api.contact.repository.ContactRepository;
import lion6.DrinkGuide.api.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ContactCommandService {
    private final MemberRepository memberRepository;
    private final ContactRepository contactRepository;

    public void createContact(Long memberId, ContactCreateRequestDto contactCreateRequestDto) {
        Contact contact = Contact.builder()
                .content(contactCreateRequestDto.content())
                .member(memberRepository.findMemberByIdOrThrow(memberId))
                .build();
        contactRepository.save(contact);
    }
}
