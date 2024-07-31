package lion6.DrinkGuide.api.contact.service;

import lion6.DrinkGuide.api.contact.domain.Contact;
import lion6.DrinkGuide.api.contact.dto.response.ContactGetAllResponseDto;
import lion6.DrinkGuide.api.contact.repository.ContactRepository;
import lion6.DrinkGuide.api.member.domain.Member;
import lion6.DrinkGuide.api.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactQueryService {
    private final MemberRepository memberRepository;
    private final ContactRepository contactRepository;

    public List<ContactGetAllResponseDto> getContacts(Long memberId){
        memberRepository.findMemberByIdRoleTypeOrThrow(memberId);
        List<Contact> contacts = contactRepository.findAll();

        return contacts.stream()
                .map(contact -> ContactGetAllResponseDto.of(contact))
                .collect(Collectors.toList());
    }
}
