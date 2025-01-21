package blueharmel.strokehigh.service;

import blueharmel.strokehigh.domain.Court;
import blueharmel.strokehigh.repository.CourtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourtService {

    private final CourtRepository courtRepository;

    @Transactional
    public void saveCourt(Court court) {
        courtRepository.save(court);
    }

    public List<Court> findCourts(){
        return courtRepository.findAll();
    }

    public Court findOne(Long courtId) {
        return courtRepository.findOne(courtId);
    }
}
